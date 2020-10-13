package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.BusinessServiceImp;
import javafx.scene.chart.PieChart.Data;
import shoppingMallBean.PageBean;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if (opt.equals("byTitle")) {
//			queryByTitle(request, response);
		} else if (opt.equals("byIsbn")) {
//			queryByIsbn(request, response);
		} else if (opt.equals("byCategory")) {
			queryByCategory(request, response);
		} else if (opt.equals("buyProduct")) {
			buyProduct(request, response);
		} else if (opt.equals("search")) {
			search(request, response);
		} else if (opt.equals("byCategoryName")) {
//			queryByCategoryName(request, response);
		}

		else {
			response.sendRedirect("index.jsp");
		}
	}

	protected void queryByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int cId = Integer.parseInt(request.getParameter("cid"));
		BusinessServiceImp dao = new BusinessServiceImp();
		List<ShoppingProduct> searchList = dao.searchtype(cId);
		Integer page = 1;
		String strPage = request.getParameter("page");
		if (strPage == null || "".equals(strPage)) {
		} else {
			page = Integer.parseInt(strPage);
		}
		PageBean<ShoppingProduct> Pager = new PageBean<ShoppingProduct>(page, 5, searchList);

		request.getSession().setAttribute("searchProducts", Pager);
		
		response.sendRedirect("search.jsp");

	}

	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageBean<ShoppingProduct> pageBean = (PageBean<ShoppingProduct>) request.getSession()
				.getAttribute("searchProducts");

		if (pageBean != null) {

			List<ShoppingProduct> searchBList = pageBean.getSourceList();

			if (searchBList != null) {

				Integer page = Integer.parseInt(request.getParameter("page"));

				if (page == null || page < 1)
					page = 1;

				PageBean<ShoppingProduct> Pager = new PageBean<ShoppingProduct>(page, 5, searchBList);

				request.getSession().setAttribute("searchProducts", Pager);

				response.sendRedirect("search.jsp");

				return;
			}
		}
		response.sendRedirect("index.jsp");

	}

	protected void buyProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productId = request.getParameter("pid");
		System.out.println(productId);
		BusinessServiceImp dao = new BusinessServiceImp();
		ShoppingProduct Product = dao.findprProduct(productId);
		request.getSession().setAttribute("buyProduct", Product);
		response.sendRedirect("buyItem.jsp");

	}
}
