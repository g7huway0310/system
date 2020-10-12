package ShoppingMallDAO;

import java.util.List;

import shoppingMallBean.ShoppingProduct;

public interface ProductDAO {

	public List<ShoppingProduct> getAll();

	// 根据id取product
	public ShoppingProduct findProduct(String id);

	// 依照品牌搜尋
	public List<ShoppingProduct> SearchBrandItem(String keyWord);

	// 依照類別
	public List<ShoppingProduct> searchtype(int selectWhich);

	// 更新庫存
	public int updateData(ShoppingProduct shoppingProduct, int updateAmount);

	// 新增產品
	public void inserData(ShoppingProduct shoppingProduct);

	public void delete(ShoppingProduct shoppingProduct);

	public List<ShoppingProduct> PriceSearch(List<ShoppingProduct> products, int maxprice, int lowprice);
	
	public void updateCount(String product_id);

}
