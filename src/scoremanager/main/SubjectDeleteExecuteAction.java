package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String cd = req.getParameter("cd");

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(teacher.getSchool());

        SubjectDao dao = new SubjectDao();
        dao.delete(subject);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}
