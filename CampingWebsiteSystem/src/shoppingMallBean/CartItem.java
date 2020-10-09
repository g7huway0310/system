package shoppingMallBean;

public class CartItem {
	
	private ShoppingProduct product;
	
	private String productId;
	
	private String productName;
	
    private int quantity;//數量
	
    private double price;//價格小戲
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
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
