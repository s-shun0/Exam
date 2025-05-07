package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

		String studentNo=req.getParameter("test.studentNo");
        String classNum=req.getParameter("class_num");
        String subject=req.getParameter("subject");
        int num=Integer.parseInt(req.getParameter("num"));

        int point = Integer.parseInt(req.getParameter("point"));

		TestDao  tDao = new TestDao();
		StudentDao sDao = new StudentDao();
		SubjectDao subDao = new SubjectDao();

		List<Test> list = new ArrayList<>();
		Test test = new Test();
		test.setStudent(sDao.get(studentNo));
		test.setSubject(subDao.get(subject, teacher.getSchool()));
		test.setSchool(teacher.getSchool());
		test.setNo(num);
		test.setPoint(point);
		test.setClassNum(classNum);

		list.add(test);
		//変更の仕方が分からん,skip
		boolean count = tDao.save(list);
		if (count){
			req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		}else{
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}

}
