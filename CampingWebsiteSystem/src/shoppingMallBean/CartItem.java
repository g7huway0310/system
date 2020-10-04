package shoppingMallBean;

public class CartItem {
	private ShoppingProduct product;
	private int quantity;//數量
    private double price;//商品類型小計
    
	public ShoppingProduct getProduct() {
		return product;
	}
	public void setProduct(ShoppingProduct product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	 public void setQuantity(int quantity) {  
	        this.quantity = quantity;  
	        this.price=this.product.getProductPrice()*this.quantity;  
    }  
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
