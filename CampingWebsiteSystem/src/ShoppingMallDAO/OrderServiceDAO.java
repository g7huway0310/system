package ShoppingMallDAO;


import shoppingMallBean.Order;



public interface OrderServiceDAO {
	    
		void orderAdd(Order order);
		
		int findOrderIdByOrderNum(String orderNum);
		
		long orderReadCount();
		
		long orderReadCount(int userId);
		
		long orderReadCountByStatus(int status);
	
		Order findOrderByOrderId(int orderId);
		
		boolean orderStatus(int orderId,int status);

}
