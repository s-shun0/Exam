package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        School school = teacher.getSchool();

        req.setAttribute("subject", new Subject());
        req.setAttribute("errors", new java.util.HashMap<String, String>());

        req.getRequestDispatcher("subject_create.jsp").forward(req, res);
    }
}
