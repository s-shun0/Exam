package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher loginUser = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        // 新しい Teacher オブジェクトを作成
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setPassword(password);
        teacher.setName(name);
        teacher.setSchool(loginUser.getSchool()); // ログインユーザーの所属校に紐づけ

        // データベースに登録
        TeacherDao tDao = new TeacherDao();
        tDao.insert(teacher);

        // 完了画面へ
        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("teacher_create_done.jsp").forward(req, res);
    }
}
