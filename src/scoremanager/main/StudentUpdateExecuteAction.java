package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action{
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");



        Student student = new Student();
		//学生インスタンスに毛策結果をセット
		student.setName(req.getParameter("name"));
		student.setEntYear(Integer.parseInt(req.getParameter("ent_year")));
		student.setClassNum(req.getParameter("class_num"));
		student.setAttend(Boolean.parseBoolean("attend"));
		student.setNo(req.getParameter("no"));



		StudentDao sDao = new StudentDao();
		boolean count = sDao.save(student);

		if (count == true){
			req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
		}else{
			req.getRequestDispatcher("../../error.jsp").forward(req, res);
		}


	}

}
