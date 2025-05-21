
package scoremanager.main;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        School school = teacher.getSchool();

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        HashMap<String, String> errors = new HashMap<>();

        // 檢查輸入值
        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "科目コードを入力してください");
        } else if (cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください");
        }

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        SubjectDao dao = new SubjectDao();
        if (errors.size() == 0 && dao.get(cd, school) != null) {
            errors.put("cd", "科目コードが重複しています");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("subject", subject);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        dao.save(subject);
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}
