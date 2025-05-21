package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher loginUser = (Teacher) session.getAttribute("user");

        TeacherDao tDao = new TeacherDao();
        List<Teacher> teachers = tDao.getAll();

        req.setAttribute("teachers", teachers);
        req.getRequestDispatcher("teacher_list.jsp").forward(req, res);
    }
}
