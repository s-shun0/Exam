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

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        // 驗證
        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "科目コードを入力してください");
        } else if (cd.length() > 3) {
            errors.put("cd", "科目コードは3文字以内で入力してください");
        } else if (new SubjectDao().get(cd) != null) {
            errors.put("cd", "その科目コードは既に使用されています");
        }

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        sDao.save(subject);

        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    }
}
