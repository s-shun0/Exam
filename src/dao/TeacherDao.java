package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao {
	/**
	 * getメソッド 教員IDを指定して教員インスタンスを1件取得する
	 *
	 * @param id:String
	 *            教員ID
	 * @return 教員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Teacher get(String id) throws Exception {
		// 教員インスタンスを初期化
		Teacher teacher = new Teacher();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from teacher where id=?");
			// プリペアードステートメントに教員IDをバインド
			statement.setString(1, id);
			// プリペアードステートメントを実行
			ResultSet resultSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (resultSet.next()) {
				// リザルトセットが存在する場合
				// 教員インスタンスに検索結果をセット
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				// 学校フィールドには学校コードで検索した学校インスタンスをセット
				teacher.setSchool(schoolDao.get(resultSet.getString("school_cd")));
			} else {
				// リザルトセットが存在しない場合
				// 教員インスタンスにnullをセット
				teacher = null;
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

		return teacher;
	}

	/**
	 * loginメソッド 教員IDとパスワードで認証する
	 *
	 * @param id:String
	 *            教員ID
	 * @param password:String
	 *            パスワード
	 * @return 認証成功:教員クラスのインスタンス, 認証失敗:null
	 * @throws Exception
	 */
	public Teacher login(String id, String password) throws Exception {
		// 教員クラスのインスタンスを取得
		Teacher teacher = get(id);
		// 教員がnullまたはパスワードが一致しない場合
		if (teacher == null || !teacher.getPassword().equals(password)) {
			return null;
		}
		return teacher;
	}
	// 全件取得（一覧表示用）
	public List<Teacher> getAll() throws Exception {
		List<Teacher> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "SELECT * FROM teacher ORDER BY id";
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getString("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setSchool(schoolDao.get(rs.getString("school_cd")));

				list.add(teacher);
			}
		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		return list;
	}

	// 教師情報の新規登録
	public void insert(Teacher teacher) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;

	    try {
	        String sql = "INSERT INTO teacher (id, password, name, school_cd) VALUES (?, ?, ?, ?)";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, teacher.getId());
	        statement.setString(2, teacher.getPassword());
	        statement.setString(3, teacher.getName());
	        statement.setString(4, teacher.getSchool().getCd());

	        statement.executeUpdate();
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
	}

	// 更新
    public void update(Teacher teacher) throws Exception {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE teacher SET password = ?, name = ?, school_cd = ? WHERE id = ?"
        );

        stmt.setString(1, teacher.getPassword());
        stmt.setString(2, teacher.getName());
        stmt.setString(3, teacher.getSchool().getCd());
        stmt.setString(4, teacher.getId());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
