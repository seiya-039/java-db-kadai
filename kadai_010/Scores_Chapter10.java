package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//1,データベースに接続する
		Connection con = null;
		Statement statement = null;

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"othello039");

			System.out.println("データベース接続成功" + con );

			// SQLクエリを準備
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = '95' , score_english = '80' WHERE id = 5;";

			// データ追加のSQLクエリを実行（DBMSに送信）
			System.out.println("レコード更新を実行します");
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");

			//並び替え
			String ordersql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			ResultSet result = statement.executeQuery(ordersql);

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String score_math = result.getString("score_math");
				String score_english = result.getString("score_english");
				System.out.println(result.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "/数学=" + score_math + "/英語＝"
						+ score_english);
			}
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}

			}
		}
	}
}