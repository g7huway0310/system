package Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_ShoppingCart.model.OrderItemBean;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String finalDecision = request.getParameter("finalDecision");	
		
		HttpSession session = request.getSession(false);
		
		Cart cart = (Cart)session.getAttribute("cart");
		
		
		
		if (cart == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		// 如果使用者取消訂單
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			return;  			// 一定要記得 return 
		}
		double orderPrice = cart.getPrice();
		String shippingAddress = request.getParameter("ShippingAddress");  // 出貨地址
		
		Date today = new Date(); 
		
	    Order aOrder=new Order(0, 0, today, orderPrice, 0, null);
	    
	    Set<OrderItem> items = new HashSet<OrderItem>();//訂單明細
	    
	    Map<String, OrderItem> orders = cart.getOrderitem();
	    
	    Set<String> keySet = orders.keySet();
	    
	    for(String key:keySet) {
	    	OrderItem orderItem = orders.get(key);
	    	String orderId = orderItem.getOrderId();
	    	String productId = orderItem.getProductId();
	    	int quantity = orderItem.getQuantity();
	    	
	    	
	    	
	    }
	    
	    
	    
	   
	    
	    
	}

}
