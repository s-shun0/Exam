package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no);

        request.setAttribute("student", student);
        request.getRequestDispatcher("student_delete.jsp").forward(request, response);
    }
}
