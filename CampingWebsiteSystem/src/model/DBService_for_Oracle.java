package model;

public class DBService_for_Oracle {
public static final String host = "127.0.0.1";
	
	public static final String DB_ORACLE = "ORACLE";
	
	public static final String DB_TYPE = DB_ORACLE;
	
	private static final String DBURL_Oracle = "jdbc:oracle:thin:@127.0.0.1:1521/XEPDB1";
	
	public static final String USERID_Oracle = "scott";
	
	public static final String PSWD_Oracle = "tiger";
	
	private static final String CREATE_Table_ShoppingData_SQL="CREATE TABLE SHOPPINGDATA("
            + "PRODUCT_ID VARCHAR(45) NOT NULL,"
            + "PRODUCT_BRAND VARCHAR(2500),"
            + "PRODUCT_NAME VARCHAR(2500) NOT NULL,"
            + "PRODUCT_PRICE NUMBER NOT NULL,"
            + "PRODUCT_FEATURE VARCHAR(2500),"
            + "PRODUCT_SPEC VARCHAR(2500),"
            + "PRODUCT_WARRING VARCHAR(2500),"
            + "PRODUCT_STACK NUMBER,"
            + "CATEGORY_ID NUMBER,"
            + "CLICKNUM NUMBER,"
            + "PRIMARY KEY (PRODUCT_ID))";
	
	private static final String CREATE_Table_Order_SQL="CREATE TABLE ORDERS("
           	+ "ORDERNO NUMBER NOT NULL,"
            + "MEMBERID VARCHAR(2500),"
            + "TOTALAMOUNT NUMBER,"
            + "INVOICETITLE VARCHAR(70),"
            + "ORDERDATE DATE,"
            + "SHIPPINGDATE DATE,"
            + "CANCELTAG VARCHAR(2500),"
            + "PRIMARY KEY (ORDERNO))";
	
	
	
	private static final String CREATE_Table_OrderItem_SQL="CREATE TABLE ORDERITEM("
            + "SEQNO NUMBER NOT NULL,"
            + "ORDERNO NUMBER,"
            + "AMOUNT NUMBER,"
            + "DESCRIPTION VARCHAR(70),"
            + "PRODUCTID VARCHAR(45),"
            + "INVOICETITLE VARCHAR(45),"
            + "UNITPRICE NUMBER,"
            + "DISCOUNT NUMBER,"
            + "PRIMARY KEY (SEQNO))";
	
	
	

	public static String getHost() {
		return host;
	}

	public static String getDbOracle() {
		return DB_ORACLE;
	}

	public static String getDbType() {
		return DB_TYPE;
	}

	public static String getDburlOracle() {
		return DBURL_Oracle;
	}

	public static String getUseridOracle() {
		return USERID_Oracle;
	}

	public static String getPswdOracle() {
		return PSWD_Oracle;
	}
	public static String getCreateTableOrderSql() {
		return CREATE_Table_Order_SQL;
	}

	public static String getCreateTableOrderitemSql() {
		return CREATE_Table_OrderItem_SQL;
	}

	public static String getCreateTableShoppingdataSql() {
		return CREATE_Table_ShoppingData_SQL;
	}

	
	
	

}
