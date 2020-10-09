package ShoppingMallDAO;


import java.sql.Connection;
import java.util.List;

import shoppingMallBean.Order;

public interface OrderDAO {
	
	public void addOrder(Order order);//新增訂單
	
	public void delOrder(Order order);//修改
	
	public void findOrder(int orderid);//搜尋訂單
	
	public void update(Order order);

	public List<Order> getOrder(int memberid);//依照id取得用戶訂單 (查詢)
	
	void setConnection(Connection con);

}
