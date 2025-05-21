package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");

        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.get(id);

        SchoolDao sDao = new SchoolDao();
        req.setAttribute("schools", sDao.getAll());
        req.setAttribute("teacher", teacher);

        req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
    }
}
