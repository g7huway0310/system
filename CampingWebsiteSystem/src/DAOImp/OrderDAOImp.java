package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import ShoppingMallDAO.OrderDAO;
import model.DBService_for_Oracle;
import shoppingMallBean.Order;
import shoppingMallBean.OrderItem;

public class OrderDAOImp implements OrderDAO {

	private String memberId = null;

	private Connection con;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	int orderNo = 0;

	@Override
	public void addOrder(Order order) {

		// TODO Auto-generated method stub
		String sqlOrder = "insert into ORDERS" + " (MEMBERID, TOTALAMOUNT, SHIPPINGADDRESS,"
				+ " INVOICETITLE,ORDERSTATUS,ORDERDATE) " + " values(?,?,?,?,?,?)";
		String sqlOrderitems = "Insert Into OrderItems (orderNo, PRODUCT_ID," + " description, amount, unitPrice) "
				+ " values(?, ?, ?, ?, ?) ";
		System.out.println("break");
		ResultSet generatedKeys = null;// 自動產生主鍵跟著insert

		String generatedColumns[] = { "ORDERNO" };//

		try (PreparedStatement psmt = con.prepareStatement(sqlOrder, generatedColumns)) {

			psmt.setDouble(1, order.getMemberId());
			psmt.setDouble(2, order.getTotalAmount());
			psmt.setString(3, order.getShippingAddress());
			System.out.println(order.getShippingAddress());
			psmt.setString(4, order.getInvoiceTitle());
			psmt.setDouble(5, order.getOrderStatus());
			Timestamp ts = new Timestamp(order.getOrderDate().getTime());
			psmt.setTimestamp(6, ts);

			psmt.executeUpdate();

			int id = 0;

			generatedKeys = psmt.getGeneratedKeys();// 自動產生主鑑

			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new RuntimeException("OrderDaoImpl類別#insertOrder()無法取得新增之orders表格的主鍵");
			}
			List<OrderItem> orderitems = order.getoItem();

			try (PreparedStatement psmt2 = con.prepareStatement(sqlOrderitems)) {
				for (OrderItem item : orderitems) {
					psmt2.setDouble(1, id);// 將獲取到的主鍵加入
					psmt2.setString(2, item.getProductId());
					psmt2.setString(3, item.getDescription());
					psmt2.setDouble(4, item.getQuantity());
					psmt2.setDouble(5, item.getUnitPrice());
					psmt2.executeUpdate();
					psmt2.clearParameters();
					System.out.println("訂單新增成功");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public Order findOrder(int orderid) {
		Order ob = null;
		DataSource ds = null;
		Set<OrderItem> set = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
			Connection connection = ds.getConnection();
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
			ps.setInt(1, orderNo);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					Integer no = rs.getInt("orderNo");
					String invoiceTitle = rs.getString("invoiceTitle");
					int id = rs.getInt("memberId");
					Timestamp orderDate = rs.getTimestamp("orderDate");
					String shippingAddress = rs.getString("shippingAddress");
					double totalAmount = rs.getDouble("totalAmount");
					
				    ob=new Order(no, id, totalAmount, orderDate, shippingAddress, invoiceTitle, null);
				}
			}
			ps1.setInt(1, orderNo);
			try (
				ResultSet rs = ps1.executeQuery();
			) {
				ArrayList list=new ArrayList<OrderItem>();
				
				while (rs.next()) {
					int seqno = rs.getInt("seqNo");
					Double orderNo = rs.getDouble("orderNo");
					String productId = rs.getString("bookId");
					String description = rs.getString("description");
					Double quantity = rs.getDouble("amount");
					Double unitPrice = rs.getDouble("unitPrice");
					OrderItem oi = new OrderItem(seqno, productId, orderNo, unitPrice, quantity, description);
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
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
			Connection connection = ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT OrderNo FROM Orders";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				Integer no = rs.getInt(1);
				list.add(findOrder(no));
			}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;
	}

	@Override
	public List<Order> getMemberOrders(String memberId) {
		// TODO Auto-generated method stub
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
			Connection connection = ds.getConnection();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<Order> list = new ArrayList<Order>();
		String sql = "SELECT OrderNo FROM Orders where memberId = ? Order by orderDate desc ";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, memberId);
				try (
					ResultSet rs = ps.executeQuery();
				) {
					while (rs.next()) {
						Integer no = rs.getInt(1);
						list.add(findOrder(orderNo));
					}
				}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;
	}

}
