package Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import DAOImp.BusinessServiceImp;
import ShoppingMallDAO.CartDAO;
import shoppingMallBean.Cart;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
           
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
