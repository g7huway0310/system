package DAOImp;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import ShoppingMallDAO.OrderDAO;
import ShoppingMallDAO.ProductDAO;
import shoppingMallBean.Order;

public class OrderServiceImp implements OrderDAO {

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;
	ProductDAO dao=null;
	
	@Override
	public boolean orderAdd(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int findOrderIdByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long orderReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long orderReadCount(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long orderReadCountByStatus(int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order findOrderByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean orderStatus(int orderId, int status) {
		// TODO Auto-generated method stub
		return false;
	}

}
