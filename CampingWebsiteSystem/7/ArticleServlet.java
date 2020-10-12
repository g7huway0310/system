
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.org.apache.bcel.internal.generic.NEW;

import article.ArticleBean;
import article.ArticleDao;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		String submit = request.getParameter("submit");
		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		System.out.println(request.getParameter("submit"));
		if (submit.equals("submit")) {
			gotoSubmitProcess(request, response);
		} else if (submit.equals("confirm")) {
			gotoConfirmProcess(request, response);
		} else if (submit.equals("delete")) {
			gotoDelectProcess(request, response);
		} else if (submit.equals("update")) {
			gotoUpdateProcess(request, response);
		} else if (submit.equals("red_list")) {
			gotoList(request, response);
		} else if (submit.equals("brsearch")) {
			gotoSearch(request, response);
		} else if (submit.equals("selectId")) {
			gotoArtIdCheck(request, response);
		}

	}

	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title;
		String text;
		String ariticleid;
		String memberid;

		title = request.getParameter("title").trim();
		text = request.getParameter("text").trim();
		ariticleid = request.getParameter("articleid").trim();
		memberid = request.getParameter("memberid").trim();

		ArticleBean rel_Article = new ArticleBean(title, text, ariticleid, memberid);
		request.getSession(true).setAttribute("rel_Article", rel_Article);
		request.getRequestDispatcher("./DisplayArticle.jsp").forward(request, response);
	}

	public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			ArticleDao articleDao = new ArticleDao(conn);
			ArticleBean articleData = (ArticleBean) request.getSession(true).getAttribute("rel_Article");
			if (articleDao.insertArticle(articleData)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/DoneArticle.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoDelectProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title;
		title = request.getParameter("title").trim();
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			ArticleDao articleDao = new ArticleDao(conn);
//			ArticleBean articleData = (ArticleBean) request.getSession(true).getAttribute("rel_Article");
//			System.out.println(articleData);
			if (articleDao.deleteArticle(title)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/DeleteArticle.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoArtIdCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Article");
			String artId = request.getParameter("ariticleid");
			while (rs.next()) {
				List list = new ArrayList<>();
				String title = rs.getString("title");
				String text = rs.getString("text");
				String memberid = rs.getString("memberid");
				String articleid = rs.getString("articleid");

				list.add(new ArticleBean(title, text, memberid, articleid));
				if (artId.equals(articleid)) {
					request.getSession(true).setAttribute("list", list);
					request.getRequestDispatcher("/UpdateContent.jsp").forward(request, response);
				}
			}
			return;
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
			ne.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public void gotoUpdateProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title;
		String text;
		String memberid;
		String articleid;

		memberid = request.getParameter("memberid").trim();
		title = request.getParameter("title").trim();
		text = request.getParameter("text").trim();
		articleid = request.getParameter("articleid").trim();

		ArticleBean articleData = new ArticleBean(title, text, articleid, memberid);

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			ArticleDao articleDao = new ArticleDao(conn);

			if (articleDao.updateArticle(articleData)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/UpdateArticle.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
			conn = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArticleDao articleDao = new ArticleDao(conn);
		List<ArticleBean> list = articleDao.getAllArticle();

		request.setAttribute("alist", list);
		request.getRequestDispatcher("/ListArt.jsp").forward(request, response);
	}

	private void gotoSearch(HttpServletRequest request, HttpServletResponse response) {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
			conn = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		////////////////
		ArticleDao articleDao = new ArticleDao(conn);
		List<ArticleBean> atlsrh = articleDao.getArticleSearch();

		request.setAttribute("atlsrh", atlsrh);
		try {
			request.getRequestDispatcher("/DisplaySearch.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
