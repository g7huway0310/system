package ShoppingMallDAO;

import java.util.List;

import shoppingMallBean.Cart;
import shoppingMallBean.ShoppingProduct;

public interface CartDAO {

	public List<ShoppingProduct> getProducts();

	public ShoppingProduct findprProduct(String id);

	public void deleteCartItem(String sid, Cart cart);

	public void clearCart(Cart cart);

	public void changeQuantity(String sid, String quantity, Cart cart);

	public List<ShoppingProduct> SearchBrandItem(String keyWord);

	public List<ShoppingProduct> searchtype(int selectWhich);

}
