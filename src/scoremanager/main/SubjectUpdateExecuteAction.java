package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        if (!errors.isEmpty()) {
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(teacher.getSchool());

            request.setAttribute("subject", subject);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        sDao.save(subject);

        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}
