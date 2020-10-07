package shoppingMallBean;

import java.util.Map;

public class OrderItem {
	
    private Integer seqno;
	private String productId;
    private String orderId;        
    private int quantity;    
    private String description;
    
    public OrderItem(Map<String, Object> map) {
    	this.setOrderId((String)map.get("orderId"));
    	this.setQuantity((int)map.get("quantity"));
    	this.setProductId((String)map.get("productID"));
    }
    
   
	public OrderItem(Integer seqno, String productId, String orderId, int quantity, ShoppingProduct product) {
		super();
		this.seqno = seqno;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
		
	}


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
    
    
    

}
