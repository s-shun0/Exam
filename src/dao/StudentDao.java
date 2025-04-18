package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao{

	private String baseSql = "select * from student where school_cd=?";

	public Student get(String no) throws Exception {
		Student student = new Student();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try{
			//プリペアードスタートメントにSQL文をセット
			statement = connection.prepareStatement("select * from student where no=?");
			//学生番号をバインド
			statement.setString(1,no);
			//実行
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()){
				//学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				student.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else{
				student = null;
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
		return student;
	}

	private List<Student> postFilter(ResultSet rSet,School school)throws Exception{
		//リストの初期化
		List<Student> list = new ArrayList<>();
		try{
			//リザルトセットを全権
			while (rSet.next()){
				//学生インスタンスを初期化
				Student student = new Student();
				//学生インスタンスに毛策結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);
				//リストに追加
				list.add(student);
			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Student>filter(School school,int entYear,String classNum,boolean isAttend)throws Exception{
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		//プリペアードスタートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String condition = "and ent_year=? and class_num=?";
		//SQL文のソート
		String order = " order by no asc";

		String conditionIsAttend = "";

		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}

		try{
			//プリペアードスタートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			//プリペアードスタートメントに学校コードをバインド
			statement.setString(1,school.getCd());
			statement.setInt(2,entYear);
			statement.setString(3,classNum);
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet,school);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	public List<Student>filter(School school,int entYear,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String condition = "and ent_year=? ";
		String order = " order by no asc";
		String conditionIsAttend = "";

		if (isAttend){
			conditionIsAttend = "and is_attend=true";
		}
		try {
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			statement.setString(1,school.getCd());
			statement.setInt(2, entYear);
			rSet = statement.executeQuery();
			list = postFilter(rSet,school);
		} catch (Exception e){
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
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	public List<Student> filter(School school,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement =null;
		ResultSet rSet = null;
		String order =" order by no asc";
		String conditionIsAttend ="";
		if (isAttend) {
			conditionIsAttend ="and is_attend=true";
		}
		try {
			statement =connection.prepareStatement(baseSql + conditionIsAttend + order);
			statement.setString(1,school.getCd());
			rSet = statement.executeQuery();
			list = postFilter(rSet,school);
		} catch (Exception e){
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

	public boolean save(Student student)throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try{
			Student old = get(student.getNo());
			if (old == null){
				//学生が存在しない場合
				//プリペアードスタートメントにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into student (no,name,ent_year,class_num,is_attend,school_cd) values(?,?,?,?,?,?)");
				//プリペアードスタートメントに値をバインド
				statement.setString(1, student.getNo());
				statement.setString(2,student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4,student.getClassNum());
				statement.setBoolean(5,student.isAttend());
				statement.setString(6,student.getSchool().getCd());
			} else {
				//存在した場合
				statement = connection.prepareStatement(
						"update student set name=?,ent_year=?,class_num=?,is_attend=? where no =?");
				//プリペアードスタートメントに値をバインド
				statement.setString(1,student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3,student.getClassNum());
				statement.setBoolean(4,student.isAttend());
				statement.setString(5, student.getNo());
			}
			count = statement.executeUpdate();
		} catch (Exception e){
			throw e;
		} finally {
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
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}

		if (count > 0){
			return true;
		} else{
			return false;
	}
	}
}



