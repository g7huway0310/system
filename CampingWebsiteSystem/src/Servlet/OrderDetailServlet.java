package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.OrderDAOImp;
import DAOImp.OrderServiceDAOImp;
import shoppingMallBean.Order;

/**
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		OrderDAOImp orderService = new OrderDAOImp();
		
        String orderNo = request.getParameter("orderNo");
		
		int no = Integer.parseInt(orderNo.trim());

		Order ob = orderService.findOrder(no);
		
		request.setAttribute("OrderBean", ob);   // 將OrderBean物件暫存到請求物件內
		
		RequestDispatcher rd = request.getRequestDispatcher("Orderdetail.jsp");
		
		rd.forward(request, response);
		
		rd.forward(request, response);
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
