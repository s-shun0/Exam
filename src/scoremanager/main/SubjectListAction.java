package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 取得登入教師
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 取得科目列表
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.filter(teacher.getSchool());

        // 傳送給 JSP 顯示
        request.setAttribute("subject_list", subjectList);
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
