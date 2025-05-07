package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{
	private String baseSql = "select * from test";

	public List<TestListSubject> postFilter(ResultSet rSet){
		List<TestListSubject> list = new ArrayList<>();

		try{
			while (rSet.next()){
				TestListSubject subject = new TestListSubject();

				subject.setEntYear(rSet.getInt("student.ent_year"));
				subject.setStudentNo(rSet.getString("test.student_no"));
				subject.setStudentName(rSet.getString("student.name"));
				subject.setClassNum(rSet.getString("student.class_num"));
				subject.putPoint(rSet.getInt("test.no"),rSet.getInt("test.point"));
				list.add(subject);
			}
		}catch(Exception  e){
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListSubject> filter(int entYear,String classNum,Subject subject,School school)throws Exception{
		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		// SQL文のテーブル連結
		String join = " join student on test.student_no = student.no";
		// SQL文の条件
		String condition = " where test.subject_cd=? and test.school_cd=? and student.ent_year=?";
		// SQL文のソート
		String order = " order by test.student_no asc, test.no asc";




		try{
			statement = connection.prepareStatement(baseSql + join + condition + order);
			statement.setString(1,subject.getCd());
			statement.setString(2, school.getCd());
			statement.setInt(3, entYear);
			rSet=statement.executeQuery();

			list = postFilter(rSet);
		}catch (Exception e){
			throw e;
		} finally {
			if (statement != null) {
				try{
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try{
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}
}
