import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.naming.java.javaURLContextFactory;

public class RegisterDao {
	
	private String url="jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private String name="project2";
	private String password="project2";
	private String driver="oracle.jdbc.OracleDriver";

	public void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public String insert(Member member) throws ParseException {
		
		loadDriver(driver);
		Connection con=getConnection();
		String result="Data entered successfully";
		String sql="insert into member values(?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, member.getMobile());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(member.getBirthday());
			pstmt.setDate(6, new java.sql.Date(date.getTime()) );			
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getAddress());
			pstmt.setInt(9, member.getOrders());
			pstmt.setInt(10, member.getCanceltag());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="Data not entered";
		}
	
		return result;
	}
}
 