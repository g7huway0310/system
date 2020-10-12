package DAOImp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.org.apache.xpath.internal.operations.And;

import ShoppingMallDAO.ProductPageDAO;
import model.DBService_for_Oracle;
import shoppingMallBean.ShoppingProduct;

public class ProductPageDAOImp {

	DataSource ds = null;

	private int bookId = 0; // 查詢單筆商品會用到此代號

	private int pageNo = 0; // 存放目前顯示之頁面的編號

	private int recordsPerPage = DBService_for_Oracle.getRecordsPerPage(); // 預設值：每頁三筆

	private int totalPages = -1;

	private int searchPageNo = 0;

	public int getSearchPageNo() {
		return searchPageNo;
	}

	public void setSearchPageNo(int searchPageNo) {
		this.searchPageNo = searchPageNo;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public ProductPageDAOImp() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService_for_Oracle.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 總頁數
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	// 搜尋結果總頁數
	public int getSearchTotalPage(String keyword) {

		long re = getSearchRecordCounts(keyword);

		System.out.println("搜尋比數" + re);

		if (re < 0) {
			System.out.println("沒找到符合的項目");
		} else {

			if (re < recordsPerPage) {

				totalPages = 1;

			} else {
				System.out.println(recordsPerPage);
				totalPages = (int) (Math.ceil(re / (double) recordsPerPage));
			}
		}
		System.out.println("搜尋總頁數" + totalPages);

		return totalPages;
	}

	public int getPriceSearchTotalPage(int minPrice, int maxPrice, int typeid) {

		long re = getPriceSearchCount(minPrice, maxPrice, typeid);

		System.out.println("搜尋比數" + re);

		if (re < 0) {
			System.out.println("沒找到符合的項目");
		} else {

			if (re < recordsPerPage) {

				totalPages = 1;

			} else {
				System.out.println(recordsPerPage);
				totalPages = (int) (Math.ceil(re / (double) recordsPerPage));
			}
		}
		System.out.println("搜尋總頁數" + totalPages);

		return totalPages;
	}

	// 取得資料總比數
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(1) FROM shoppingdata";
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
		}

		return count;
	}

	public long getSearchRecordCounts(String keyword) {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(1) FROM shoppingdata where PRODUCT_BRAND like ? OR PRODUCT_NAME like ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);

		) {
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
				System.out.println(count);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
		}
		System.out.println("資料的size=" + count);
		return count;
	}

	// 取得價錢資料總比數
	public long getPriceSearchCount(int minPrice, int maxPrice, int categortId) {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(1) FROM shoppingdata where PRODUCT_PRICE BETWEEN ? AND ? AND (CATEGORY_ID = ?)";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setDouble(1, minPrice);
			ps.setDouble(2, maxPrice);
			ps.setInt(3, categortId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getLong(1);
				System.out.println(count);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
		}
		System.out.println("資料的size=" + count);
		return count;
	}

	public List<ShoppingProduct> getPageProducts() {

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sql0 = "select *" + "from(" + "select rownum as rn, SHOPPINGDATA.* " + "from SHOPPINGDATA  "
				+ "ORDER BY SHOPPINGDATA.CATEGORY_ID)" + "WHERE rn >= ? AND rn <= ?";
		String sql = sql0;
		System.out.println(sql);
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;

		int endRecordNo = (pageNo) * recordsPerPage;

		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)
//		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		int endRecordNo = recordsPerPage;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setBigDecimal(1, new BigDecimal(startRecordNo));
			ps.setBigDecimal(2, new BigDecimal(endRecordNo));
			try (ResultSet rs = ps.executeQuery();) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					String id = rs.getString("PRODUCT_ID");
					String name = rs.getString("PRODUCT_NAME");
					String warring = rs.getString("PRODUCT_WARRING");
					String brands = rs.getString("PRODUCT_BRAND");
					String spec = rs.getString("PRODUCT_SPEC");
					int price = rs.getInt("PRODUCT_PRICE");
					int stack = rs.getInt("PRODUCT_STOCK");
					String feature = rs.getString("PRODUCT_FEATURE");
					int categortId = rs.getInt("CATEGORY_ID");
					int click = rs.getInt("CLICKNUM");
					ShoppingProduct product = new ShoppingProduct(id, brands, name, price, categortId, spec, stack,
							warring, feature, click);

					list.add(product);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#getPageBooks()發生例外: " + ex.getMessage());
		}
		return list;
	}

	public List<ShoppingProduct> SearchBrandItem(String keyWord) {

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sql0 = "select *" + "from(" + "select rownum as rn, SHOPPINGDATA.* " + "from SHOPPINGDATA "
				+ "WHERE PRODUCT_BRAND like ? OR PRODUCT_NAME like ?" + "ORDER BY SHOPPINGDATA.CATEGORY_ID)"
				+ "WHERE rn >= ? AND rn <= ?";

		String sql = sql0;

		System.out.println(sql);
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (searchPageNo - 1) * recordsPerPage + 1;

		int endRecordNo = (searchPageNo) * recordsPerPage;

		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)
//		int startRecordNo = (searchPageNo - 1) * recordsPerPage;
//		
//		int endRecordNo = recordsPerPage;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");
			ps.setBigDecimal(3, new BigDecimal(startRecordNo));
			ps.setBigDecimal(4, new BigDecimal(endRecordNo));

			try (ResultSet rs = ps.executeQuery();) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					String id = rs.getString("PRODUCT_ID");
					String name = rs.getString("PRODUCT_NAME");
					String warring = rs.getString("PRODUCT_WARRING");
					String brands = rs.getString("PRODUCT_BRAND");
					String spec = rs.getString("PRODUCT_SPEC");
					int price = rs.getInt("PRODUCT_PRICE");
					int stack = rs.getInt("PRODUCT_STOCK");
					String feature = rs.getString("PRODUCT_FEATURE");
					int categortId = rs.getInt("CATEGORY_ID");
					int click = rs.getInt("CLICKNUM");
					ShoppingProduct product = new ShoppingProduct(id, brands, name, price, categortId, spec, stack,
							warring, feature, click);

					list.add(product);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#getPageBooks()發生例外: " + ex.getMessage());
		}

		return list;
	}

	public List<ShoppingProduct> SearchBrandSorted(String keyWord) {

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sql0 = "select *" + "from(" + "select rownum as rn, SHOPPINGDATA.* " + "from SHOPPINGDATA "
				+ "WHERE PRODUCT_BRAND like ? OR PRODUCT_NAME like ?" + "ORDER BY SHOPPINGDATA.PRODUCT_PRICE)"
				+ "WHERE rn >= ? AND rn <= ?";
		String sql = sql0;

		System.out.println(sql);
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (searchPageNo - 1) * recordsPerPage + 1;

		int endRecordNo = (searchPageNo) * recordsPerPage;

		System.out.println(sql0);

		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)
//		int startRecordNo = (searchPageNo - 1) * recordsPerPage;
//		
//		int endRecordNo = recordsPerPage;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");
			ps.setBigDecimal(3, new BigDecimal(startRecordNo));
			ps.setBigDecimal(4, new BigDecimal(endRecordNo));

			try (ResultSet rs = ps.executeQuery();) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					String id = rs.getString("PRODUCT_ID");
					String name = rs.getString("PRODUCT_NAME");
					String warring = rs.getString("PRODUCT_WARRING");
					String brands = rs.getString("PRODUCT_BRAND");
					String spec = rs.getString("PRODUCT_SPEC");
					int price = rs.getInt("PRODUCT_PRICE");
					int stack = rs.getInt("PRODUCT_STOCK");
					String feature = rs.getString("PRODUCT_FEATURE");
					int categortId = rs.getInt("CATEGORY_ID");
					int click = rs.getInt("CLICKNUM");
					ShoppingProduct product = new ShoppingProduct(id, brands, name, price, categortId, spec, stack,
							warring, feature, click);

					list.add(product);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#getPageBooks()發生例外: " + ex.getMessage());
		}

		return list;
	}

	public List<ShoppingProduct> SearchPrice(int minPrice, int maxPrice, int typeid) {

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sql0 = "select *" + "from(" + "select rownum as rn, SHOPPINGDATA.* " + "from SHOPPINGDATA "
				+ "WHERE (PRODUCT_PRICE BETWEEN ? AND ?) AND CATEGORY_ID = ? " + "ORDER BY SHOPPINGDATA.CATEGORY_ID)"
				+ "WHERE rn >= ? AND rn <= ?";

		String sql = sql0;

		System.out.println(sql);
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (searchPageNo - 1) * recordsPerPage + 1;

		int endRecordNo = (searchPageNo) * recordsPerPage;

		System.out.println(minPrice + " " + maxPrice + " " + typeid + startRecordNo + " " + endRecordNo);

		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)
//		int startRecordNo = (searchPageNo - 1) * recordsPerPage;
//		
//		int endRecordNo = recordsPerPage;
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql0);) {
			ps.setInt(1, minPrice);
			ps.setInt(2, maxPrice);
			ps.setInt(3, typeid);
			ps.setBigDecimal(4, new BigDecimal(startRecordNo));
			ps.setBigDecimal(5, new BigDecimal(endRecordNo));

			try (ResultSet rs = ps.executeQuery();) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					String id = rs.getString("PRODUCT_ID");
					String name = rs.getString("PRODUCT_NAME");
					String warring = rs.getString("PRODUCT_WARRING");
					String brands = rs.getString("PRODUCT_BRAND");
					String spec = rs.getString("PRODUCT_SPEC");
					int price = rs.getInt("PRODUCT_PRICE");
					int stack = rs.getInt("PRODUCT_STOCK");
					String feature = rs.getString("PRODUCT_FEATURE");
					int categortId = rs.getInt("CATEGORY_ID");
					int click = rs.getInt("CLICKNUM");
					ShoppingProduct product = new ShoppingProduct(id, brands, name, price, categortId, spec, stack,
							warring, feature, click);

					list.add(product);
				}
			} finally {

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#getPageBooks()發生例外: " + ex.getMessage());
		}

		System.out.println("真實資料 " + list);

		return list;

	}

}
