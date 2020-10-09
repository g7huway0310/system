package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOImp.OrderServiceDAOImp;
import ShoppingMallDAO.OrderServiceDAO;
import shoppingMallBean.Cart;
import shoppingMallBean.CartItem;
import shoppingMallBean.Order;
import shoppingMallBean.OrderItem;
/**
 * Servlet implementation class ProcessOrderServlet
 */
@WebServlet("/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		request.setCharacterEncoding("UTF-8");
		System.out.println("Test");
		String finalDecision = request.getParameter("finalDecision");

		HttpSession session = request.getSession(false);

		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		// 如果使用者取消訂單
		if (finalDecision.equals("CANCEL")) {
			session.removeAttribute("cart");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
			return; // 一定要記得 return
		}
		double orderPrice = cart.getPrice();// 取出訂單總金額

		String shippingAddress = request.getParameter("SHIPPINGADDRESS"); // 出貨地址

		String pay = request.getParameter("Pay");

		String invoiceTitle = request.getParameter("invoiceTitle");

		Date today = new Date();// 訂單新增時間
		
        Order aOrder=new Order(0, 0, orderPrice, today, shippingAddress, 0, invoiceTitle);
		
		// 新建一個存放訂單明細的Set物件: items
		List<OrderItem> oItem = new ArrayList<OrderItem>();// 訂單明細

		Map<String, CartItem> map = cart.getMap();

		Set<String> keySet = map.keySet();

	
		for (String key : keySet) {
			
			CartItem cartItem = map.get(key);

			String productName = cartItem.getProductName();
			
			double price = cartItem.getPrice();
			
			//取得細項id
			String productId = cartItem.getProduct().getProductId();
			
			//取得細項描述(商品名稱)
			
			String description = cartItem.getProduct().getProductName();
			
			System.out.println(productId);
			
			Double quantity =new Double(cartItem.getQuantity());
		
			OrderItem aItem = new OrderItem(null, productId, 10.0, price, quantity, description);
			                     
			oItem.add(aItem);

		}
		aOrder.setoItem(oItem);

		try {

			OrderServiceDAO service = new OrderServiceDAOImp();

			service.orderAdd(aOrder);
		
			session.removeAttribute("cart");
			
			response.sendRedirect(response.encodeRedirectURL("OrderSuccess.jsp"));
			
			return;

		} catch (RuntimeException ex) {
			String message = ex.getMessage();
			String shortMsg = "";
			shortMsg = message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			session.setAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg + "，請調正訂單內容");
			// System.out.println("處理訂單時發生異常: " + message);
//			response.sendRedirect(response.encodeRedirectURL("../_04_ShoppingCart/ShowCartContent.jsp"));
			return;
		}

	}

}
