package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");

        StudentDao sDao = new StudentDao();
        sDao.delete(no);

        request.getRequestDispatcher("student_delete_done.jsp").forward(request, response);
    }
}
