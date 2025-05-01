package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");

        SubjectDao sDao = new SubjectDao();
        sDao.delete(cd);

        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}
