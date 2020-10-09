package DAOImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ShoppingMallDAO.OrderDAO;
import ShoppingMallDAO.OrderItemDAO;
import ShoppingMallDAO.OrderServiceDAO;
import model.DBService_for_Oracle;
import shoppingMallBean.Order;
import shoppingMallBean.OrderItem;

public class OrderServiceDAOImp implements OrderServiceDAO {

	private DataSource ds;
//	private OrderItemDao oidao;
	private OrderDAOImp oDao;
	
	private OrderItemDAO orderItemDAO;
//	private MemberDao mdao;
	
	public OrderServiceDAOImp() {
		try {
			Context ctx = new InitialContext();
			ds 	  = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
			oDao=new OrderDAOImp();
			orderItemDAO=new OrderItemDAOImp();
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
			
		}
	}
	
	
	@Override
	public void orderAdd(Order order) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判斷開始
		try {
			System.out.println("ddd");
			
			con.setAutoCommit(false);
		
			//檢查庫存 更新庫存
			checkStock(order, con);
			
			oDao.setConnection(con);
			
			//添加訂單
			oDao.addOrder(order);
			
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
	}
	
	public void checkStock(Order ob, Connection con) {
		 
		
		List<OrderItem> oItems = ob.getoItem();
		
		orderItemDAO.setConnection(con);
		
		for (OrderItem oib : oItems) {
		
			orderItemDAO.updateProductStock(oib);
		 }
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
