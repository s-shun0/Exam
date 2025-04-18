package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        int entYear=0;
        String no="";
        String name="";
        String class_num="";
        School school = null;
        boolean attend;



        no = req.getParameter("no");

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no);

        ClassNumDao cNumDao = new ClassNumDao();
        school = student.getSchool();
        List<String> list = cNumDao.filter(school);



        entYear = student.getEntYear();
        name=student.getName();
        class_num = student.getClassNum();
        attend = student.isAttend();


        req.setAttribute("ent_year", entYear);
        req.setAttribute("no",no);
        req.setAttribute("name", name);
        req.setAttribute("class_num", class_num);
        req.setAttribute("list", list);
        req.setAttribute("is_attend", attend);


        req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}
