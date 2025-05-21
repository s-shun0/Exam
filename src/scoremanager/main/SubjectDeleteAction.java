package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String cd = req.getParameter("cd");
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, teacher.getSchool());

        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}
