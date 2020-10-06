package shoppingMallBean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//使用linkMap保留插入順序，有序的HashMap。
	private Map<String,CartItem> map=new LinkedHashMap<String,CartItem>();
	
	private double price;//總價
	
	public void add(ShoppingProduct product,int num){
		CartItem item=map.get(product.getProductId());//獲取購物車商品訊息
		if(item!=null){  
            item.setQuantity(item.getQuantity()+num);  
        }else{  
            item=new CartItem();  
            item.setProduct(product); 
            item.setQuantity(num);  
            //把新的購物項目加到map中
            map.put(product.getProductId(),item);
        }  
	}
	//取得所有在購物車內
	public Map<String, CartItem> getMap() {  
	   return map;  
	}  
	//設定購物車
	public void setMap(Map<String, CartItem> map) {  
        this.map = map;  
    } 
	public double getPrice() {  
        double totalprice=0;  
        //map.entrySet整個購物車內 取出各項使用foreach
        for(Map.Entry<String, CartItem> me:map.entrySet()){  
            CartItem item=me.getValue();  
            totalprice+=item.getPrice();  
        }  
        this.price=totalprice;  
        return price;  
    }  
    public void setPrice(double price) {  
        this.price = price;  
    }  

}
