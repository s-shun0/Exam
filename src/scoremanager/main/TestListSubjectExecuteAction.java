package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        //受け取る内容
        //入学年度,クラス,科目
        int entYear=0;
        String classNum="";
        String subject="";

        entYear = Integer.parseInt(req.getParameter("ent_year"));
        classNum = req.getParameter("class_num");
        subject = req.getParameter("subject");
        //現在の状態の表示
        req.setAttribute("now_ent_year", entYear);
        req.setAttribute("now_classNum", classNum);
        req.setAttribute("now_subject", subject);

        SubjectDao subDao = new SubjectDao();
        Subject sublist=subDao.get(subject,teacher.getSchool());

        List<TestListSubject> tmp_list = new ArrayList<>();
        TestListSubjectDao testDao = new TestListSubjectDao();
        tmp_list = testDao.filter(entYear, classNum, sublist, teacher.getSchool());
        //検索結果を送信
        int count=0; //テスト回数の判定用
        List<List<String>> list = new ArrayList<>();
        List<String> box = new ArrayList<>();; //add用
        for (TestListSubject list_subject : tmp_list){
        	Map<Integer,Integer> points = new HashMap<>();
        	points = list_subject.getPoints();
        	for (int num :points.keySet()){
        		if (num == 2){
        			box.add(points.get(2)+"");
        			list.add(box);
        			box = new ArrayList<>();;
        			count=0;
        		}else if (count == 1){
        			box.add("-");
        			list.add(box);
        			box = new ArrayList<>();;

        			box.add(list_subject.getEntYear()+"");
        			box.add(list_subject.getClassNum());
        			box.add(list_subject.getStudentNo());
        			box.add(list_subject.getStudentName());
        			box.add(list_subject.getPoint(1)+"");

        		}else{
        			box.add(list_subject.getEntYear()+"");
        			box.add(list_subject.getClassNum());
        			box.add(list_subject.getStudentNo());
        			box.add(list_subject.getStudentName());
        			box.add(list_subject.getPoint(1)+"");
        			count=1;
        		}
        	}
        }
        if (count==1){
        	box.add("-");
			list.add(box);
        }


        req.setAttribute("list",list);


        //クラス一覧
        ClassNumDao cNumDao =new ClassNumDao();
        List<String> clist = cNumDao.filter(teacher.getSchool());
        //教科一覧
        SubjectDao SUBject = new SubjectDao();
        List<Subject> slist = SUBject.filter(teacher.getSchool());
        //入学年度
        LocalDate todaysDate = LocalDate.now(); //現在の情報を取得
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }
        req.setAttribute("class_list", clist);
        req.setAttribute("subject_list", slist);
        req.setAttribute("ent_year_set", entYearSet);



        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);


	}
}

