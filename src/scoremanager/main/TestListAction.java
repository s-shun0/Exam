package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        //クラス一覧
        ClassNumDao cNumDao =new ClassNumDao();
        List<String> clist = cNumDao.filter(teacher.getSchool());
        //教科一覧
        SubjectDao subject = new SubjectDao();
        List<String> slist = subject.filter(teacher.getSchool());
        //入学年度
        LocalDate todaysDate = LocalDate.now(); //現在の情報を取得
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("class_list", clist);
        req.setAttribute("sbject_list", slist);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}
}