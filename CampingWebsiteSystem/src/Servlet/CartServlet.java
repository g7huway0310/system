package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.BusinessServiceImp;
import shoppingMallBean.Cart;
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
		String action=(String)request.getSession().getAttribute("action");
		String id = request.getParameter("id");
		System.out.println(action);
		System.out.println(id);
		
		switch(action) {
		case "add":
			addTOCart(request,response);
			break;
		case "changeIn":
			changeIn(request,response);//更改购物车商品数量
			break;
		case "delItem":
			delete(request,response);
			break;
		case "delAll":
			delAll(request,response);
		}
	}
	private void addTOCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 
		BusinessServiceImp service=new BusinessServiceImp();
		   
         String productId = request.getParameter("id");
         
         ShoppingProduct product = service.findprProduct(productId);
         
         //獲取購物車如果沒有就建立購物車
         Cart cart=(Cart)request.getSession(true).getAttribute("cart"); 
         if (cart==null) {
		   cart=new Cart();
		   request.getSession(true).setAttribute("cart", cart);
		   
         }
		   cart.add(product);
		   response.sendRedirect("./ListServlet");
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
		BusinessServiceImp service=new BusinessServiceImp();
		
		String id = request.getParameter("id");
		
		Cart cart= (Cart)request.getSession(true).getAttribute("cart");
		
		service.deleteCartItem(id,cart);
		
		request.getRequestDispatcher("listCartPage.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("listcart.jsp").forward(request, response);
	}
	private void delAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BusinessServiceImp service=new BusinessServiceImp();
		Cart cart=(Cart)request.getSession(true).getAttribute("cart");
		service.clearCart(cart);
		request.getRequestDispatcher("listcart.jsp").forward(request, response);
	}
	

}
