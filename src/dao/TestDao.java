package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{

	private String baseSql = "select * from test where =?";

	public Test get(Student student,Subject subject,School school) throws Exception {
		Test test = new Test();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		//受け取るデータ
		//入学年度、クラス、科目、回数
		try{
			statement = connection.prepareStatement("select * from student where ent_year=? class_num=? school_cd=?");
			statement.setInt(1,student.getEntYear());
			statement.setString(2, student.getClassNum());
			statement.setString(3, school.getCd());
			ResultSet rSet = statement.executeQuery();
			if (rSet.next()){
				test.setStudent(student);
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
			} else{
				test=null;
			}
		} catch (Exception e){
			throw e;
		}finally {
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch(SQLException e){
					throw e;
				}
			}
		}
		return test;
	}


//	public List<Test>filter(School school) throws Exception{
//
//	}

//	public boolean save(Subject subject)throws Exception{
//
//	}

//	public boolean delete(Subject subject)throws Exception{
//
//	}


}
