package shoppingMallBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order {
	
	private int orderId;            		
    private int userId;            
    private Date orderDate;       
    private double money;			
    private int orderStatus;     	
    
    private List<OrderItem> oItem=new ArrayList<>();
    
//    private User user=new User();
    
    

	public Order() {
    }
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Order(int orderId, int userId, Date orderDate, double money, int orderStatus, List<OrderItem> oItem) {
	super();
	this.orderId = orderId;
	this.userId = userId;
	this.orderDate = orderDate;
	this.money = money;
	this.orderStatus = orderStatus;
	this.oItem = oItem;
}

	public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OrderItem> getoItem() {
		return oItem;
	}

	public void setoItem(List<OrderItem> oItem) {
		this.oItem = oItem;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	

}
