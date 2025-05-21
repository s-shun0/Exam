package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		SubjectDao dao = new SubjectDao();
		List<Subject> list = dao.filter(teacher.getSchool());

		req.setAttribute("subjects", list);

		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}
