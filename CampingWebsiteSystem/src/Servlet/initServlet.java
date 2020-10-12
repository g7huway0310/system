package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.BusinessServiceImp;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class initServlet
 */
@WebServlet(name = "/initServlet", urlPatterns = { "/initServlet" }, loadOnStartup = 0)
public class initServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public initServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 第一個類別分頁初始化
		BusinessServiceImp bs = new BusinessServiceImp();
		List<ShoppingProduct> clist = bs.searchtype(0);
		config.getServletContext().setAttribute("clist", clist);
		System.out.println("初始化完成");
	}
}
