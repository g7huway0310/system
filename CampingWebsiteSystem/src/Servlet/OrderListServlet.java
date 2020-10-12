package Servlet;

import java.awt.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOImp.OrderDAOImp;
import shoppingMallBean.Order;
import shoppingMallBean.OrderItem;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
//		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		String memberID = request.getParameter("memberID");
		
		OrderDAOImp orderService = new OrderDAOImp();
		
		java.util.List<Order> memberOrders = orderService.getMemberOrders(memberID);
		
		request.setAttribute("memberOrders", memberOrders);
		
        RequestDispatcher rd = request.getRequestDispatcher("OrderList.jsp");
		
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
	
	public void displayOrderBean(Order ob) {
		System.out.println("ob.getOrderNo()=" + ob.getOrderNo());
		System.out.println("ob.getMemberId()=" + ob.getMemberId());
		System.out.println("ob.getOrderDate=" + ob.getOrderDate());
		System.out.println("ob.getTotalAmount=" + ob.getTotalAmount());
		System.out.println("ob.getInvoiceTitle=" + ob.getInvoiceTitle());
		System.out.println("ob.getShippingAddress=" + ob.getShippingAddress());	
		System.out.println("==============訂單明細=================");
		java.util.List<OrderItem> items = ob.getoItem();
		for (OrderItem oib : items) {
			System.out.println("---------------一筆明細---------------");
			System.out.println("   oib.getSeqno()=" + oib.getSeqno());
			System.out.println("   oib.getOrderNo()=" + oib.getOrderNo());
			System.out.println("   oib.getBookId()=" + oib.getProductId());
			System.out.println("   oib.getDescription()=" + oib.getDescription());
			System.out.println("   oib.getQuantity()=" + oib.getQuantity());
			System.out.println("   oib.getUnitPrice()=" + oib.getUnitPrice());
			
		}
	}

}
