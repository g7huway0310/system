package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.jmx.snmp.Timestamp;

import ShoppingMallDAO.OrderDAO;
import ShoppingMallDAO.OrderItemDAO;
import ShoppingMallDAO.OrderServiceDAO;
import model.DBService_for_Oracle;
import shoppingMallBean.Order;
import shoppingMallBean.OrderItem;

public class OrderServiceDAOImp<E> implements OrderServiceDAO {

	private DataSource ds;
//	private OrderItemDao oidao;
	private OrderDAOImp oDao;

	private OrderItemDAO orderItemDAO;
//	private MemberDao mdao;

	public OrderServiceDAOImp() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
			oDao = new OrderDAOImp();
			orderItemDAO = new OrderItemDAOImp();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());

		}
	}

	@Override
	public void orderAdd(Order order) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 判斷開始
		try {
			System.out.println("ddd");

			con.setAutoCommit(false);

			// 檢查庫存 更新庫存
			checkStock(order, con);

			oDao.setConnection(con);

			// 添加訂單
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
		Order ob = null;
		DataSource ds = null;
		Set<OrderItem> set = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}

		String sql = "SELECT * FROM Orders WHERE orderno = ? ";
		String sql1 = "SELECT * FROM OrderItems WHERE orderno = ? ";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
		) {
			ps.setInt(1, orderId);
			
			try (
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					Integer no = rs.getInt("orderNo");
					//String cancel = rs.getString("cancelTag");
					String bno = rs.getString("bno");
					String invoiceTitle = rs.getString("invoiceTitle");
					int memberId = rs.getInt("memberId");
					java.sql.Timestamp orderDate = rs.getTimestamp("orderDate");
					String shippingAddress = rs.getString("shippingAddress");
					Date shipDate = rs.getDate("shippingDate");
					double totalAmount = rs.getDouble("totalAmount");
					ob =new Order(no, memberId, totalAmount, orderDate, shippingAddress, invoiceTitle, null);
				}
			}
			ps1.setInt(1, orderId);
			try (
				ResultSet rs = ps1.executeQuery();
			) {
				ArrayList list=new ArrayList<E>();
				
				while (rs.next()) {
					int seqNo = rs.getInt("seqNo");
					Double orderNo = rs.getDouble("orderNo");
					String productId = rs.getString("Product_ID");
					String description = rs.getString("description");
					Double amount = rs.getDouble("amount");
					Double unitPrice = rs.getDouble("unitPrice");
				    OrderItem oi=new OrderItem(seqNo, productId, orderNo, unitPrice, amount, description);
					list.add(oi);
				}
				ob.setoItem(list);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-2發生例外: " + ex.getMessage());
		}
		return ob;
	}

	@Override
	public boolean orderStatus(int orderId, int status) {
		// TODO Auto-generated method stub
		return false;
	}

}
