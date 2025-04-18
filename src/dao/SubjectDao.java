package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SubjectDao extends Dao{
//	public Subject get(String cd,School school)throws Exception{
//		Subject subject = new Subject();
//		Connection connection = getConnection();
//		PreparedStatement statement = null;
//
//		try{
//			statement = connection.prepareStatement(
//					"select * from subject where ");
//
//
//		}catch(Exception e){
//
//		}
//
//	}

	public List<String> filter(School school)throws Exception{
		List<String> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try{
			statement =connection.prepareStatement(
					"select name from subject where school_cd=?");
			statement.setString(1,school.getCd());
			ResultSet rSet =statement.executeQuery();

			while (rSet.next()){
				list.add(rSet.getString("name"));
			}
		} catch(Exception e){
			throw e;
		}finally{
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

//	public boolean save(Subject subject)throws Exception{
//
//	}

//	public boolean delete(Subject subject)throws Exception{
//
//	}
}
