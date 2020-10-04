package shoppingMallBean;

import java.util.Map;

public class OrderItem {
	private String itemId;  
	private String productId;
    private String orderId;        
    private int quantity;       

	private ShoppingProduct product;
    
    public OrderItem(Map<String, Object> map) {
    	this.setOrderId((String)map.get("orderId"));
    	this.setQuantity((int)map.get("quantity"));
    	this.setProductId((String)map.get("productID"));
    }
    public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
