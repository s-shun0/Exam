package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        String entYearStr = ""; // 入力された入学年度
        String classNum = "";
        String isAttendStr = "";
        int entYear = 0;
        boolean isAttend = false; // 在学フラグ
        List<Student> students = null; // 学生リスト
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
        Map<String, String> errors = new HashMap<>();

        // リクエストパラメーターの取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        isAttendStr = req.getParameter("f3");

        // ビジネスロック4
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr);
        }

        // リストの初期化
        List<Integer> entYearSet = new ArrayList<>();

        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        List<String> list = cNumDao.filter(teacher.getSchool());

        if (entYear != 0 && classNum.equals("0")) {
            // 入学年度とクラス番号を指定
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
            // 指定なしの場合
            // 全学年情報取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度の指定もしてください");
            req.setAttribute("errors", errors);
            // 全学年情報を取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // レスポンス値をセット
        // リクエストに入学年度をセット
        req.setAttribute("f1", entYear);
        // リクエストにクラス番号をセット
        req.setAttribute("f2", classNum);
        // 在学フラグが送信された場合
        if (isAttendStr != null) {
            isAttend = true;
            req.setAttribute("f3", isAttendStr);
        }
        // リクエストに学生データをセット
        req.setAttribute("students", students);
        // リクエストにデータをセット
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // JSPへフォワード
        req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}
