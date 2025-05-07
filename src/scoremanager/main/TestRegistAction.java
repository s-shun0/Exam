package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        int entYear=0;
        String classNum="";
        String subject="";
        int num=0;
        boolean button = false;
        Subject subject_list = new Subject();
        //検索の値
        try{
        	String tmp1 = req.getParameter("f1");
        	if (tmp1 != null ) {
        		entYear=Integer.parseInt(tmp1);
        	}

        	classNum=req.getParameter("f2");
        	SubjectDao sDao = new SubjectDao();
        	subject =req.getParameter("f3");
        	if (subject == null){
        		//学校のすべてのsubjectを参照したい

        	}else{
        		subject_list=sDao.get(subject,teacher.getSchool());
        	}



        	String tmp2 = req.getParameter("f4");
        	if (tmp2 != null ) {
        		num=Integer.parseInt(tmp2);
        	}

        	button = Boolean.parseBoolean(req.getParameter("button"));

        }catch(Exception e){
        	throw e;
        }
        //年度
        LocalDate todaysDate = LocalDate.now(); //現在の情報を取得
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
        	entYearSet.add(i);
        }

        ClassNumDao cDao = new ClassNumDao();
        List<String> clist = cDao.filter(teacher.getSchool());

        SubjectDao subDao = new SubjectDao();
        List<Subject> slist = subDao.filter(teacher.getSchool());

        TestDao tdao = new TestDao();
        if (button == false){
        	//int entYear,String ClassNum,Subject subject,int num,School school
            	List<Test> testList = tdao.filter(entYear,classNum,subject_list,num,teacher.getSchool());

            req.setAttribute("entYear",entYear);
            req.setAttribute("test", testList);
        }else{
        	List<Test> list = new ArrayList<>();
        	req.setAttribute("test",list);
        }
        req.setAttribute("subject", subject);
        req.setAttribute("class_num",classNum);
        req.setAttribute("num",num);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_list", clist);
        req.setAttribute("subject_list", slist);
     // JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}

}
