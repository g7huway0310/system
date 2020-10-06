package shoppingMallBean;

import java.util.ArrayList;
import java.util.List;

import model.initTable;

public class ShoppingProduct {
	
	 private String productId;
	 private String productBrand;
	 private String productName;
	 private int productPrice;
	 private int categortId;
	 private String productSpec;
	 private int productStack;
	 private String productWarring;
	 private String productfeature;
	 private int click;
	 
	 public ShoppingProduct(String productId, String productBrand, String productName, int productPrice, int categortId,
			String productSpec, int productStack, String productWarring, String productfeature, int click) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productName = productName;
		this.productPrice = productPrice;
		this.categortId = categortId;
		this.productSpec = productSpec;
		this.productStack = productStack;
		this.productWarring = productWarring;
		this.productfeature = productfeature;
		this.click = click;
	}

	public String getProductfeature() {
		return productfeature;
	}

	public void setProductfeature(String productfeature) {
		this.productfeature = productfeature;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	private List<Object> list = new ArrayList<Object>();
	 
	 public int getCategortId() {
		return categortId;
	}

	public void setCategortId(int categortId) {
		this.categortId = categortId;
	}

	public void setChild(Object object) {
	        list.add(object);
	    }

	 public List<Object> getList() {
	        return list;
	    }

	public ShoppingProduct(String productId, String productBrand, String productName, int productPrice,
			String productSpec, int productStack, String productWarring) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productSpec = productSpec;
		this.productStack = productStack;
		this.productWarring = productWarring;
	}

	public ShoppingProduct() {
		
	}

	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public int getProductStack() {
		return productStack;
	}

	public void setProductStack(int productStack) {
		this.productStack = productStack;
	}

	public String getProductWarring() {
		return productWarring;
	}

	public void setProductWarring(String productWarring) {
		this.productWarring = productWarring;
	}

	

	
}
