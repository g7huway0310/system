
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
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

import DAOImp.ShoppingDAOImp;
import oracle.net.aso.c;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	/**
	 * Default constructor.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		System.out.println(request.getParameter("key"));
		if (request.getParameter("brand") != null) {
			BrandSearchProcess(request, response);
		} else if (request.getParameter("type") != null) {
			TypeSearchProcess(request, response);
		}

	}

	public void BrandSearchProcess(HttpServletRequest request, HttpServletResponse response) {

		Connection connection = null;
		DataSource ds = null;
		InitialContext ctxt = null;
		try {

			String key = request.getParameter("key").trim();

			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;

			connection = ds.getConnection();

			ShoppingDAOImp jdbc = new ShoppingDAOImp(connection);

			List<ShoppingProduct> searchBrandItem = jdbc.SearchBrandItem(key);

			request.getSession(true).setAttribute("brandSearch", searchBrandItem);

			request.getRequestDispatcher("searchResult.jsp").forward(request, response);

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public void TypeSearchProcess(HttpServletRequest request, HttpServletResponse response) {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection connection = null;
		try {

			String key = request.getParameter("typekey").trim();

			int chooseNumber = Integer.parseInt(key);

			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;

			connection = ds.getConnection();

			ShoppingDAOImp jdbc = new ShoppingDAOImp(connection);

			List<ShoppingProduct> searchBrandItem = jdbc.searchtype(chooseNumber);

			request.getSession(true).setAttribute("typeSearch", searchBrandItem);

			request.getRequestDispatcher("searchResult.jsp").forward(request, response);

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

}
