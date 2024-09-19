package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.TweetBean;

public class TweetDAO {
	/**
	 * tweetリスト取得する
	 * @return tweetリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TweetBean> getTweetList() throws ClassNotFoundException, SQLException {
		// リストの初期化
		List<TweetBean> tweetList = new ArrayList<>();

		// SQL文
		String sql = "SELECT id, name, message, update_date FROM tweet";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// SQL実行し、実行結果の表と現在の行を指しているカーソルを取得
			ResultSet res = pstmt.executeQuery();

			// 実行結果の表から順番に値を取得
			// nextでカーソルを1行ずつ移動させる
			while (res.next()) {
				int id = res.getInt("id"); // tweetテーブルidカラムの値
				String name = res.getString("name"); // tweetテーブルnameカラムの値
				String message = res.getString("message"); // tweetテーブルmessageカラムの値
				Date updateDate = res.getDate("update_date"); // tweetテーブルupdate_dateカラムの値

				// DBから取得した値を初期値として、TweetBeanのインスタンス生成
				TweetBean todo = new TweetBean(id, name, message, updateDate);

				// tweetListにインスタンスを追加
				tweetList.add(todo);
			}
		}
		return tweetList;
	}
	public int registerTweet(String name, String message) throws ClassNotFoundException, SQLException {
		int count = 0; // 登録件数を格納する変数

		// SQL文（プレースホルダー2つ）
		String sql = "INSERT INTO tweet(name, message) VALUES(?, ?)";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 1つ目のプレースホルダーに引数nameの値をセット
			pstmt.setString(1, name);
			// 2つ目のプレースホルダーに引数messageの値をセット
			pstmt.setString(2, message);

			// SQL実行し、登録件数をcountに代入
			count = pstmt.executeUpdate();
		}
		return count;
	}
	public TweetBean getTweetOne(int id) throws ClassNotFoundException, SQLException {
		// tweet情報を格納する変数
		TweetBean tweet = null;

		// SQL文（プレースホルダー1つ）
		String sql = "SELECT id, name, message, update_date FROM tweet WHERE id = ?";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 1つ目のプレースホルダーに引数idの値をセット
			pstmt.setInt(1, id);

			// SQL実行し、実行結果の表と現在の行を指しているカーソルを取得
			ResultSet res = pstmt.executeQuery();

			// 実行結果の表からtweetテーブルの値を取得
			if (res.next()) {
				String name = res.getString("name"); // 名前
				String message = res.getString("message"); // メッセージ
				Date updateTime = res.getDate("update_date"); // 更新日

				// DBから取得した値を初期値として、TweetBeanのインスタンス生成
				tweet = new TweetBean(id, name, message, updateTime);
			}
		}
		return tweet;
	}
	public int editTweet(int id, String message) throws ClassNotFoundException, SQLException {
		int count = 0; // 更新件数を格納する変数

		// SQL文（プレースホルダー2つ）
		String sql = "UPDATE tweet SET message = ? WHERE id = ?";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 1つ目のプレースホルダーに引数messageの値をセット
			pstmt.setString(1, message);
				
			// 2つ目のプレースホルダーに引数idの値をセット
			pstmt.setInt(2, id);

			// SQL実行し、更新件数をcountに代入
			count = pstmt.executeUpdate();
		}
		return count;
	}
	public int deleteTweet(int id) throws ClassNotFoundException, SQLException {
		int count = 0; // 削除件数を格納する変数

		// SQL文（プレースホルダー1つ）
		String sql = "DELETE FROM tweet WHERE id = ?";

		// データベース接続
		// PreparedStatementでSQL実行の準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 1つ目のプレースホルダーに引数idの値をセット
			pstmt.setInt(1, id);

			// SQL実行し、削除件数をcountに代入
			count = pstmt.executeUpdate();
		}
		return count;
	}
}

