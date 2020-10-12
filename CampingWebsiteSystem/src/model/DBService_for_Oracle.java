package model;

public class DBService_for_Oracle {
	public static final String host = "127.0.0.1";

	public static final String DB_ORACLE = "ORACLE";

	public static final String DB_TYPE = DB_ORACLE;

	private static final String DBURL_Oracle = "jdbc:oracle:thin:@127.0.0.1:1521/XEPDB1";

	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/xe";

	public static int getRecordsPerPage() {
		return RECORDS_PER_PAGE;
	}

	private static final int RECORDS_PER_PAGE = 10;

	public static final String USERID_Oracle = "scott";

	public static final String PSWD_Oracle = "tiger";

	private static final String CREATE_Table_ShoppingData_SQL = "CREATE TABLE SHOPPINGDATA("
			+ "PRODUCT_ID VARCHAR(45) NOT NULL," + "PRODUCT_BRAND VARCHAR(2500),"
			+ "PRODUCT_NAME VARCHAR(2500) NOT NULL," + "PRODUCT_PRICE NUMBER NOT NULL,"
			+ "PRODUCT_FEATURE VARCHAR(2500)," + "PRODUCT_SPEC VARCHAR(2500)," + "PRODUCT_WARRING VARCHAR(2500),"
			+ "PRODUCT_STACK NUMBER," + "CATEGORY_ID NUMBER," + "CLICKNUM NUMBER," + "PRIMARY KEY (PRODUCT_ID))";

	private static final String CREATE_Table_Order_SQL = "Create Table ORDERS "
			+ "(orderNo  INT GENERATED as IDENTITY constraint ORDERS_PK primary key, "
			+ " memberId          varchar2(20), " + " totalAmount       number(11,1), "
			+ " shippingAddress   varchar2(64), " + " invoiceTitle      varchar2(72), " + " orderDate         Date"
			+ " )";

	private static final String CREATE_Table_OrderItem_SQL = "Create TABLE ORDERITEMS "
			+ "(seqno number generated as identity constraint ORDERITEMS_PK primary key, " + " orderNo          int, "
			+ " PRODUCT_ID       varchar2(72), " + " Description      varchar2(2500), " + " amount           int, "
			+ " unitPrice        number(18,1) " + " ) ";

	private static final String CREATE_Table_Admins_SQL = "Create TABLE s_admin"
			+"(id INT  GENERATED as IDENTITY constraint s_admin primary key NOT NULL,"
			+"userName varchar(255) NOT NULL, passWord varchar(255) NOT NULL, name varchar(255))";
	
	
	public static String getCreateTableAdminsSql() {
		return CREATE_Table_Admins_SQL;
	}

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
