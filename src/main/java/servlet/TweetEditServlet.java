package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TweetDAO;
import model.entity.TweetBean;

/**
 * Servlet implementation class TweetEditServlet
 */
@WebServlet("/tweet-edit")
public class TweetEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int id = Integer.parseInt(request.getParameter("id"));

		// tweetデータを格納する変数
		TweetBean tweet = null;
		
		// TweetDAOクラスのインスタンス生成
		TweetDAO dao = new TweetDAO();

		try {
			// TweetDAOクラスのgetTweetOneメソッド呼び出し、tweetデータ取得
			tweet = dao.getTweetOne(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// リクエストスコープにtweetデータをセット
		request.setAttribute("tweet", tweet);

		// 転送
		RequestDispatcher rd = request.getRequestDispatcher("tweet-edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int id = Integer.parseInt(request.getParameter("id")); // tweetID
		String message = request.getParameter("message"); // tweetメッセージ

		// TweetDAOクラスのインスタンス生成
		TweetDAO dao = new TweetDAO();

		try {
			// TweetDAOクラスのeditTweetメソッド呼び出し、tweetデータ更新
			dao.editTweet(id, message);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 転送
		RequestDispatcher rd = request.getRequestDispatcher("tweet-list");
		rd.forward(request, response);
	}

}

