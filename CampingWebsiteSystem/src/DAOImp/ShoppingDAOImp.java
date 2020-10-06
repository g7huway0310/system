package DAOImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import ShoppingMallDAO.ProductDAO;
import shoppingMallBean.ShoppingProduct;

public class ShoppingDAOImp implements ProductDAO {

	private DataSource dataSource;

	private Connection conn;

	public ShoppingDAOImp(Connection connection) {
		this.conn = connection;
	}

	public ShoppingDAOImp() {

	}

	// id找尋商品
	public ShoppingProduct findProduct(String producID) {
		String sqlString = "select * from shoppingdata where PRODUCT_ID=?";
		ShoppingProduct findedProduct = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, producID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("PRODUCT_NAME");
				String id = rs.getString("PRODUCT_ID");
				String warring = rs.getString("PRODUCT_WARRING");
				String brands = rs.getString("PRODUCT_BRAND");
				String spec = rs.getString("PRODUCT_SPEC");
				int price = rs.getInt("PRODUCT_PRICE");
				int stack = rs.getInt("PRODUCT_STACK");
				
				String feature=rs.getString("PRODUCT_FEATURE");
				
				int categortId = rs.getInt("CATEGORY_ID");
				
				int click = rs.getInt("CLICKNUM");
				
				findedProduct=new ShoppingProduct(id, brands, name, price, categortId, spec, stack, warring, feature, click);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findedProduct;

	}

	// 依照品牌搜尋全部商品
	public List<ShoppingProduct> SearchBrandItem(String keyWord) {

		StringBuffer searchResult = new StringBuffer();

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sqlString = "select * from shoppingdata where PRODUCT_BRAND like ? ORDER BY PRODUCT_ID ";

		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);) {
			pstmt.setString(1, "%" + keyWord + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("PRODUCT_NAME");
				String id = rs.getString("PRODUCT_ID");
				String warring = rs.getString("PRODUCT_WARRING");
				String brands = rs.getString("PRODUCT_BRAND");
				String spec = rs.getString("PRODUCT_SPEC");
				int price = rs.getInt("PRODUCT_PRICE");
				int stack = rs.getInt("PRODUCT_STACK");

				ShoppingProduct product = new ShoppingProduct(id, brands, name, price, spec, stack, warring);
//	      searchResult.append("商品品牌: "+brands+"\n"+
//	    		              "商品名稱: "+name+"\n"+	    		             
//	    		              "商品價格: "+String.valueOf(price)+"\n"+
//	    		              "商品庫存: "+feature+String.valueOf(stack)+"\n");
				list.add(product);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 商品類型
	public List<ShoppingProduct> searchtype(int selectWhich) {

		String[] typeArray = { "tent","sky","bedding", "cartpet","furniture","furniture", "lamp","cook", "tableware", "ice","tool"};

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sqlString = "select * from shoppingdata where PRODUCT_id like ? ORDER BY PRODUCT_ID ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);)

		{

			pstmt.setString(1, "%" + typeArray[selectWhich] + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("PRODUCT_ID");
				String brand = rs.getString("PRODUCT_BRAND");
				String name = rs.getString("PRODUCT_NAME");
				int price = rs.getInt("PRODUCT_PRICE");
				String spec = rs.getString("PRODUCT_SPEC");
				int stack = rs.getInt("PRODUCT_STACK");
				String warring = rs.getString("PRODUCT_WARRING");

				ShoppingProduct product = new ShoppingProduct(id, brand, name, price, spec, stack, warring);
				
				list.add(product);

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String creatOrder(String productID) {

		return null;

	}

	// 更新庫存
	public int updateData(ShoppingProduct shoppingProduct, int updateAmount) {

		String productId = shoppingProduct.getProductId();
		int productStack = shoppingProduct.getProductStack();

		if (productStack < updateAmount) {
			System.out.println("庫存不足");
		} else {
			try (PreparedStatement stmt = conn
					.prepareStatement("update shoppingdata set product_stack=? where product_id=? ORDER BY PRODUCT_ID ")) {
				productStack = productStack - updateAmount;
				stmt.setInt(1, productStack);
				stmt.setString(2, productId);
				int result = stmt.executeUpdate();
				if (result == 0) {
					throw new RuntimeException("update fail");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return productStack;
	}

	// 新增產品
	public void inserData(ShoppingProduct shoppingProduct) {

		String sqlString = "INSERT INTO shoppingdata VALUES(?,?,?,?,?,?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sqlString)) {

			shoppingProduct.getProductName();
			shoppingProduct.getProductPrice();
			shoppingProduct.getProductStack();

			stmt.setString(1, shoppingProduct.getProductId() + 1);
			stmt.setString(2, shoppingProduct.getProductBrand());
			stmt.setString(3, shoppingProduct.getProductName());
			stmt.setInt(4, shoppingProduct.getProductPrice());
			stmt.setString(5, shoppingProduct.getProductSpec());
			stmt.setInt(6, shoppingProduct.getProductStack());
			stmt.setString(7, shoppingProduct.getProductWarring());
			

			int result = stmt.executeUpdate();

			if (result == 0) {
				throw new RuntimeException("create fail");
			} else {
				System.out.println("成功新增");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(ShoppingProduct shoppingProduct) {

		String sqlString = "DELETE FROM shoppingdata where product_id=? ";
		try (PreparedStatement stmt = conn.prepareStatement(sqlString)) {
			stmt.setString(1, shoppingProduct.getProductId());

			int result = stmt.executeUpdate();

			if (result == 0) {
				throw new RuntimeException("create fail");
			} else {
				System.out.println("成功新增");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createOrder(ShoppingProduct shoppingProduct) {
		String sqlString = "INSERT INTO shoppingdata VALUES(?,?,?,?,?,?,?,?)";
		

	}

	@Override
	public List<ShoppingProduct> getAll() {
		// TODO Auto-generated method stub
		
		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();
		String sqlString = "select * from shoppingdata ";
		try (PreparedStatement pstmt = conn.prepareStatement(sqlString);)

		{ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("PRODUCT_ID");
			String brand = rs.getString("PRODUCT_BRAND");
			String name = rs.getString("PRODUCT_NAME");
			int price = rs.getInt("PRODUCT_PRICE");
			String spec = rs.getString("PRODUCT_SPEC");
			int stack = rs.getInt("PRODUCT_STACK");
			String warring = rs.getString("PRODUCT_WARRING");

			ShoppingProduct product = new ShoppingProduct(id, brand, name, price, spec, stack, warring);
			list.add(product);

		}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	public List<ShoppingProduct> PriceSearch(List<ShoppingProduct> products,int maxprice,int lowprice){
		
	   List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();	 
		
	   for (ShoppingProduct shoppingProduct : products) {
		   
	   int productPrice = shoppingProduct.getProductPrice();
	
	   if (productPrice>lowprice&&productPrice<maxprice) {
	      list.add(shoppingProduct);
	   }  
	   
	   return list;
	
   }
		
		
		
		
		return products;	
	}

}