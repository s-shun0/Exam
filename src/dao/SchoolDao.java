	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {
	/**
	 * getメソッド 学校コードを指定して学校インスタンスを１件取得する
	 *
	 * @param cd:String
	 *            学校コード
	 * @return 学校クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public School get(String cd) throws Exception {
		// 学校インスタンスを初期化
		School school = new School();
		// データベースへのコネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from school where cd = ?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, cd);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 学校インスタンスに学校コードと学校名をセット
				school.setCd(rSet.getString("cd"));
				school.setName(rSet.getString("name"));
			} else {
				// 存在しない場合
				// 学校インスタンスにnullをセット
				school = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return school;
	}

	/**
	 * 全ての学校情報を取得する
	 *
	 * @return List<School> 学校リスト
	 * @throws Exception
	 */
	public List<School> getAll() throws Exception {
	    List<School> schools = new ArrayList<>();

	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        String sql = "SELECT * FROM school ORDER BY cd";
	        statement = connection.prepareStatement(sql);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            School school = new School();
	            school.setCd(resultSet.getString("cd"));
	            school.setName(resultSet.getString("name"));
	            schools.add(school);
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }

	    return schools;
	}


}
