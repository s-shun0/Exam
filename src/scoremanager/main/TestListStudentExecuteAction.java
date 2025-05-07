package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

			String studentNo = req.getParameter("student_no");

			StudentDao sDao = new StudentDao();
			Student student = sDao.get(studentNo);
			TestListStudentDao testDao = new TestListStudentDao();
			List<TestListStudent> list = new ArrayList<>();
			list= testDao.filter(student);
			req.setAttribute("student_no",studentNo);
			req.setAttribute("student_name", student.getName());
			req.setAttribute("list", list);


			//クラス一覧
	        ClassNumDao cNumDao =new ClassNumDao();
	        List<String> clist = cNumDao.filter(teacher.getSchool());
	        //教科一覧
	        SubjectDao subject = new SubjectDao();
	        List<Subject> slist = subject.filter(teacher.getSchool());
	        //入学年度
	        LocalDate todaysDate = LocalDate.now(); //現在の情報を取得
	        int year = todaysDate.getYear();
	        List<Integer> entYearSet = new ArrayList<>();
	        for (int i = year - 10; i < year + 1; i++) {
	            entYearSet.add(i);
	        }
	        req.setAttribute("class_list", clist);
	        req.setAttribute("subject_list", slist);
	        req.setAttribute("ent_year_set", entYearSet);
	        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
