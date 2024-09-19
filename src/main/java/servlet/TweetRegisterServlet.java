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

/**
 * Servlet implementation class TodoRegisterServlet
 */
@WebServlet("/tweet-register")
public class TweetRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher("tweet-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("utf-8");

		// リクエストパラメータの取得
		String name = request.getParameter("name"); // 名前
		String message = request.getParameter("message"); // メッセージ

		// TweetDAOクラスのインスタンス生成
		TweetDAO dao = new TweetDAO();

		try {
			// TweetDAOクラスregisterTweetメソッドにname、messageを渡しデータベース登録
			dao.registerTweet(name, message);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 転送
		RequestDispatcher rd = request.getRequestDispatcher("tweet-list");
		rd.forward(request, response);
	}
}

