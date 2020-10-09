package DAOImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ShoppingMallDAO.CartDAO;
import ShoppingMallDAO.ProductDAO;
import shoppingMallBean.Cart;
import shoppingMallBean.CartItem;
import shoppingMallBean.ShoppingProduct;

public class BusinessServiceImp implements CartDAO {
	
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;
	ProductDAO dao=null;
    
	@Override
	public List<ShoppingProduct> getProducts() {
		// TODO Auto-generated method stub
		List<ShoppingProduct> result = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection=ds.getConnection();
			dao= new ShoppingDAOImp(connection);
			result = dao.getAll();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		
		return result;
		
	}

	@Override
	public ShoppingProduct findprProduct(String id) {
		// TODO Auto-generated method stub
		ShoppingProduct product = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection=ds.getConnection();
			dao= new ShoppingDAOImp(connection);
			product = dao.findProduct(id);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		return product;
	}

	@Override
	public void deleteCartItem(String sid, Cart cart) {
		// TODO Auto-generated method stub
		 cart.getMap().remove(sid);
	}

	@Override
	public void clearCart(Cart cart) {
		// TODO Auto-generated method stub
		cart.getMap().clear();  
		
	}

	@Override
	public void changeQuantity(String sid, String quantity, Cart cart) {
		// TODO Auto-generated method stub
		 CartItem item=cart.getMap().get(sid);  
	     item.setQuantity(Integer.parseInt(quantity));  
		
	}

	@Override
	public List<ShoppingProduct> SearchBrandItem(String keyWord) {
		// TODO Auto-generated method stub
		List<ShoppingProduct> result = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection=ds.getConnection();
			dao= new ShoppingDAOImp(connection);
			result=dao.SearchBrandItem(keyWord);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	
		return result;
	}

	@Override
	public List<ShoppingProduct> searchtype(int selectWhich) {
		// TODO Auto-generated method stub
		List<ShoppingProduct> result = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection=ds.getConnection();
			dao= new ShoppingDAOImp(connection);
			result=dao.searchtype(selectWhich);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		return result;
	}
    
	
	


}
