package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");

        Teacher teacher = new Teacher();
        teacher.setId(req.getParameter("id"));
        teacher.setPassword(req.getParameter("password"));
        teacher.setName(req.getParameter("name"));

        School school = new School();
        school.setCd(req.getParameter("school_cd"));
        teacher.setSchool(school);

        TeacherDao tDao = new TeacherDao();
        tDao.update(teacher);

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("teacher_update_done.jsp").forward(req, res);
    }
}
