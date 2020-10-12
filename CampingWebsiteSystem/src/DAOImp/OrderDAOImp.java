package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import ShoppingMallDAO.OrderDAO;
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
	public List<Order> getOrder(int memberid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findOrder(int orderid) {

		Order aOrder = null;
		DataSource ds = null;
		Set<OrderItem> set = null;

		String sqlOrder = "select* from orders where orderno=?";
		String sql1 = "SELECT * FROM OrderItem";
		try (PreparedStatement psmt = con.prepareStatement(sqlOrder);) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

}
