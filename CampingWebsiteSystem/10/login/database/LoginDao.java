package login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.bean.LoginBean;

public class LoginDao {
	
	private String url="jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private String name="project2";
	private String password="project2";
	private String driver="oracle.jdbc.OracleDriver";
	
	
	public void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean validate(LoginBean loginBean) {
		
		loadDriver(driver);
		Connection con=getConnection();
		boolean status = false;
		String sql="select * from member where mobile=? and password=?";
		PreparedStatement pstmt;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, loginBean.getMobile());
			pstmt.setString(2, loginBean.getPassword());
		ResultSet rs=pstmt.executeQuery();
		status=rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return status;
	}
	
	
	
}
