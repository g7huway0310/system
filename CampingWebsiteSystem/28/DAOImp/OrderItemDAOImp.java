package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ShoppingMallDAO.OrderItemDAO;
import shoppingMallBean.OrderItem;

public class OrderItemDAOImp implements OrderItemDAO {

	Connection conn;

	public OrderItemDAOImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double findItemAmount(OrderItem oib) {
		// TODO Auto-generated method stub
		double subtotal = oib.getQuantity() * oib.getUnitPrice();
		return subtotal;
	}

	@Override
	public int updateProductStock(OrderItem ob) {
		// TODO Auto-generated method stub

		int n = 0;

		int PRODUCT_STOCK = 0;

		String sql1 = "SELECT PRODUCT_STOCK FROM SHOPPINGDATA WHERE PRODUCT_ID= ?";

		String sql2 = "UPDATE SHOPPINGDATA SET PRODUCT_STOCK = PRODUCT_STOCK-? WHERE PRODUCT_ID= ?";

		try (PreparedStatement ps = conn.prepareStatement(sql1);) {

			ps.setString(1, ob.getProductId());

//			System.out.println(ob.getProductId());

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {

					PRODUCT_STOCK = rs.getInt(1);

//					System.out.println(PRODUCT_STOCK);

					if (PRODUCT_STOCK < ob.getQuantity()) {
						throw new CustomException.ProductStockException("庫存數量不足: productId: " + ob.getProductId()
								+ ", 在庫量: " + PRODUCT_STOCK + ", 訂購量: " + ob.getQuantity());

					} else {
						;
					}
					try (PreparedStatement ps1 = conn.prepareStatement(sql2);) {
						ps1.setDouble(1, ob.getQuantity());
						ob.getProductId();
						ps1.setString(2, ob.getProductId());
						n = ps1.executeUpdate();
					}
				}
			} catch (CustomException.ProductStockException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return n;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
