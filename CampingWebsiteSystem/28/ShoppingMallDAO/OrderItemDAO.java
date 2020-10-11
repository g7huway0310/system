package ShoppingMallDAO;

import java.sql.Connection;

import shoppingMallBean.OrderItem;

public interface OrderItemDAO {

	double findItemAmount(OrderItem oib);

	int updateProductStock(OrderItem ob);

	void setConnection(Connection conn);

}
