package Servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.webkit.ContextMenu.ShowContext;
import DAOImp.BusinessServiceImp;
import shoppingMallBean.Cart;
import shoppingMallBean.CartItem;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		String opt = request.getParameter("opt");
		
		System.out.println(opt);
		
		switch(opt) 
		{
		case "add":
			addTOCart(request,response);
			break;
		case "changeIn":
			changeIn(request,response);
			break;
		case "delItem":
			delete(request,response);
			break;
		case "delAll":
			delAll(request,response);
			break;
		case "show":
			show(request,response);
		}
		
	}
	private void addTOCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 
		 BusinessServiceImp service=new BusinessServiceImp();
		   
         String productId = request.getParameter("id");
         
         String strNum = request.getParameter("num");
 	    
         int num = Integer.parseInt(strNum);
         
         ShoppingProduct product = service.findprProduct(productId);
         
         //獲取購物車如果沒有就建立購物車
         Cart cart=(Cart)request.getSession(true).getAttribute("cart"); 
         
         
         if (cart==null) {
		   cart=new Cart();
		   request.getSession(true).setAttribute("cart", cart);
         }
		   cart.add(product,num);
		   
		   response.sendRedirect("addcart.jsp");
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
		BusinessServiceImp service=new BusinessServiceImp();
		
		String id = request.getParameter("id");
		
		Cart cart= (Cart)request.getSession(true).getAttribute("cart");
		
		service.deleteCartItem(id,cart);
		
		show(request, response);
	}
	private void changeIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BusinessServiceImp service=new BusinessServiceImp();
		 
		 //取得商品id
        String productId = request.getParameter("id");
        //想要更改數量
        String quantity = request.getParameter("quantity");
        //取得購物車
        Cart cart=(Cart)request.getSession(true).getAttribute("cart");
        
        service.changeQuantity(productId, quantity, cart);
        
        show(request, response);
	}
	private void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BusinessServiceImp service=new BusinessServiceImp();
		Cart cart=(Cart)request.getSession(true).getAttribute("cart");
		service.clearCart(cart);
		show(request, response);
	}
	private void show(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Cart cart = (Cart) request.getSession(true).getAttribute("cart");
		if(cart!=null){
			Map<String, CartItem> map = cart.getMap();
			
			request.getSession().setAttribute("silist", map);
			
			cart.getPrice();
			
		}else{
			cart = new Cart();
		}
		request.getSession().setAttribute("cart", cart);
		
//		Users user = (Users) request.getSession().getAttribute("user");
//		if(user!=null){
//			//頁面跳轉
//			response.sendRedirect("user/usercart.jsp");
//		}else{
			//跳轉
	    response.sendRedirect("showcart.jsp");
//		}
	}

}
