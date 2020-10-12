package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createTable {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String createTableProductdataSql = DBService_for_Oracle.getCreateTableShoppingdataSql();
		String createTableOrderitemSql = DBService_for_Oracle.getCreateTableOrderitemSql();
		String createTableOrderSql = DBService_for_Oracle.getCreateTableOrderSql();
		String createTableAdminsSql = DBService_for_Oracle.getCreateTableAdminsSql();

		try (Connection conn = DriverManager.getConnection(DBService_for_Oracle.getDburlOracle(),
				DBService_for_Oracle.getUseridOracle(), DBService_for_Oracle.getPswdOracle());
				PreparedStatement pstmt = conn.prepareStatement(createTableProductdataSql);
				PreparedStatement psmt2 = conn.prepareStatement(createTableOrderitemSql);
				PreparedStatement psmt3 = conn.prepareStatement(createTableOrderSql);
				PreparedStatement psmt4=conn.prepareStatement(createTableAdminsSql);

		) {

			System.out.println(createTableAdminsSql);

//      	pstmt.execute();
//			psmt2.execute();
//			psmt3.execute();
//			psmt4.execute();
			
			System.out.println("query finished");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
