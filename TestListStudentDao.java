package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql="select * from test";

	public List<TestListStudent> postFilter(ResultSet rSet){
		List<TestListStudent> list = new ArrayList<>();
		SchoolDao sDao = new SchoolDao();
		SubjectDao subDao = new SubjectDao();
		try{
			while (rSet.next()){
				School school = sDao.get(rSet.getString("test.school_cd"));

				Subject subject = subDao.get(rSet.getString("test.subject_cd"),school);


				TestListStudent student = new TestListStudent();
				student.setSubjectName(subject.getName());
				student.setSubjectCd(subject.getCd());
				student.setNum(rSet.getInt("test.no"));
				student.setPoint(rSet.getInt("test.point"));
				list.add(student);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;

	}

	public List<TestListStudent> filter(Student student)throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement= null;
		ResultSet rSet=null;
		//join
		String join = " join student on test.student_no = student.no ";

		String condition = " where student.no=? and test.class_num=? and test.school_cd=?";
		String order = " order by test.student_no asc, test.no asc";
		try{
			statement=connection.prepareStatement(baseSql+join+condition+order);
			statement.setString(1, student.getNo());
			statement.setString(2, student.getClassNum());
			statement.setString(3, student.getSchool().getCd());
			rSet=statement.executeQuery();

			list=postFilter(rSet);
		}catch (Exception e){
			throw e;
		}finally {
			if (statement != null){
				try {
					statement.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}
