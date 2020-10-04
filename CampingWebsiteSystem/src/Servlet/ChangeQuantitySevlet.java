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
 * Servlet implementation class ChangeQuantitySevlet
 */
@WebServlet("/ChangeQuantitySevlet")
public class ChangeQuantitySevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeQuantitySevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
