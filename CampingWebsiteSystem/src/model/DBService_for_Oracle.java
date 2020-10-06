package model;

public class DBService_for_Oracle {
	
	public static final String host = "127.0.0.1";
	
	public static final String DB_ORACLE = "ORACLE";
	
	public static final String DB_TYPE = DB_ORACLE;
	
	private static final String DBURL_Oracle = "jdbc:oracle:thin:@127.0.0.1:1521/XEPDB1";
	
	public static final String USERID_Oracle = "scott";
	
	public static final String PSWD_Oracle = "tiger";
	
	private static final String CREATE_Table_ProductData_SQL="CREATE TABLE PRODUCTDATA("
            + "PRODUCTID VARCHAR(45) NOT NULL,"
            + "PRODUCTBRAND VARCHAR(2500) NOT NULL,"
            + "PRODUCTNAME VARCHAR(2500) NOT NULL,"
            + "PRODUCTPRICE NUMBER NOT NULL,"
            + "PRODUCTFEATURE VARCHAR(2500),"
            + "PRODUCTSPEC VARCHAR(2500),"
            + "PRODUCTWARRING VARCHAR(2500),"
            + "PRODUCTSTACK NUMBER,"
            + "CATEGORYID NUMBER,"
            + "ClICKS NUMBER,"
            + "PRIMARY KEY (PRODUCTID))";
	
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

	public static String getCreateTableProductdataSql() {
		return CREATE_Table_ProductData_SQL;
	}
	
	
	 


}
