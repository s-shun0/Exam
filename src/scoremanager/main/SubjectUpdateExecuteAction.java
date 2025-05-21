package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 編碼設定
		req.setCharacterEncoding("UTF-8");

		// 取得表單資料
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		// 取得登入中使用者
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");

		// 設定科目物件
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		// 更新處理
		SubjectDao dao = new SubjectDao();
		dao.save(subject);  // save 方法會根據是否存在自動執行更新

		// 成功畫面轉導
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}
