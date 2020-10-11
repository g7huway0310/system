package ShoppingMallDAO;

import java.util.List;

import shoppingMallBean.ShoppingProduct;

public interface ProductPageDAO {

	public List<ShoppingProduct> getpageProduct();

	public int getTotalPage();// 取得總頁數

	public long getRecordCounts(); // 取得總共資料筆數

	public List<ShoppingProduct> listAllOf(int startIndex, int offset);

	public int counts();

}
