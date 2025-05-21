package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 取得參數
		String cd = req.getParameter("cd");

		// 取得登入中使用者
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		// 取得該科目資料
		SubjectDao dao = new SubjectDao();
		Subject subject = dao.get(cd, teacher.getSchool());

		// 設定至畫面
		req.setAttribute("subject", subject);

		// 前往更新畫面
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
