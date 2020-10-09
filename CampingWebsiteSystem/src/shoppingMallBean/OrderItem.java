package shoppingMallBean;

import java.util.Map;

public class OrderItem {
	
	private Integer seqno;//排序
    private String productId;//商品id
    private Double orderNo; //歸類在哪個訂單       
    private Double unitPrice; //價格 
    private Double quantity; //數量
    private String description;//商品描述
    
    
	public OrderItem(Integer seqno, String productId, Double orderNo, Double unitPrice, Double quantity,
			String description) {
		super();
		this.seqno = seqno;
		this.productId = productId;
		this.orderNo = orderNo;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Double getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Double orderNo) {
		this.orderNo = orderNo;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
    
    
    
}
