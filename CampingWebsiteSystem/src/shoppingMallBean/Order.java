package shoppingMallBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order {

	private int orderNo;
	private int memberId;
	private Double totalAmount;
	private Date orderDate;
	private String shippingAddress;
	private int orderStatus;
	private String invoiceTitle;// 統一編號

	private List<OrderItem> oItem = new ArrayList<>();

	public Order(int orderNo, int memberId, Double totalAmount, Date orderDate, String shippingAddress, int orderStatus,
			String invoiceTitle) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.orderStatus = orderStatus;
		this.invoiceTitle = invoiceTitle;
	}
	
	

	public Order(int orderNo, int memberId, Double totalAmount, Date orderDate, String shippingAddress,
			String invoiceTitle, List<OrderItem> oItem) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.invoiceTitle = invoiceTitle;
		this.oItem = oItem;
	}



	public List<OrderItem> getoItem() {
		return oItem;
	}

	public void setoItem(List<OrderItem> oItem) {
		this.oItem = oItem;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

}
