package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.SchoolDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

		int entYear = 0;
		String name = "";
		String id = "";
		String num = "";
		School cd ;

		entYear = Integer.parseInt(req.getParameter("f1"));//入学年度
		name = req.getParameter("name");//なまえ
		id = req.getParameter("no");//学生番号
		num = req.getParameter("f2");//クラス
		boolean attend = true;
		cd =  teacher.getSchool();
	    //ここで入学年度が未入力かをチェック

		String f1 = req.getParameter("f1");
		if (f1 == null || f1.equals("0")) {
		    req.setAttribute("error", "入学年度を選択してください。");
		    req.getRequestDispatcher("StudentCreate.action").forward(req, res);
		    return;
		}


		//dbで重複してないか確認
		StudentDao sDao = new StudentDao();
		Student count = sDao.get(id);


		//新規
		if (count == null){
			SchoolDao schoolDao = new SchoolDao();
			Student student = new Student();
			student.setNo(id);
			student.setName(name);
			student.setEntYear(entYear);
			student.setClassNum(num);
			student.setAttend(attend);
			student.setSchool(cd);

			sDao.save(student);
			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
		//重複
		} else {
		    req.setAttribute("error", "その学生番号はすでに存在しています。");
		    req.getRequestDispatcher("student_create.jsp").forward(req, res);
		    return;
		}


	}

}
