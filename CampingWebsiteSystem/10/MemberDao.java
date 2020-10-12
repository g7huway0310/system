             

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MemberDao {
	
	private String url="jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
	private String name="project2";
	private String password="project2";
	private String driver="oracle.jdbc.OracleDriver";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO member" + "(mobile,password,name,nickname,gender,birthday,email,address) VALUES"
			+ " ( ? , ? , ? , ? , ? , ? , ? , ? )";
//	private static final String SELECT_USER_BY_ID = "select mobile,password,name,nickname,gender,birthday,email,address from member where mobile =?";
	private static final String SELECT_USER_BY_ID = "select * from member where mobile =?";
	private static final String SELECT_ALL_USERS = "select * from member";
	private static final String DELETE_USERS_SQL = "delete from member where mobile = ?";
	private static final String UPDATE_USERS_SQL = "update member set password=?,name=?,nickname=?,gender=?,email=?,address=? where mobile = ?";
	public MemberDao() {
		
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {			

			Class.forName(driver);
			connection = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public void insertMember(Member member) throws SQLException, ParseException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_USERS_SQL)) {
			pstmt.setString(1, member.getMobile());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(member.getBirthday());
			pstmt.setDate(6, new java.sql.Date(date.getTime()));
			System.out.println(date);
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getAddress());
			System.out.println(pstmt);	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	public Member selectMember(String mobile) {
		
		Member member = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_ID);) {
			
			pstmt.setString(1, mobile);
			System.out.println(pstmt);
			// Step 3: Execute the query or update query
			ResultSet rs = pstmt.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
//				String mobile = rs.getString("mobile");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String nickname = rs.getString("nickname");
				String gender = rs.getString("gender");
				
// 				String birthday = rs.getString("birthday");
				Date birDate = rs.getDate("birthday");
				String birthday = Integer.toString(birDate.getYear()+1900)+"-"+Integer.toString(birDate.getMonth()+1)+"-"+Integer.toString(birDate.getDate());

				String email = rs.getString("email");
				String address = rs.getString("address");
				
				member = new Member(mobile,password,name,nickname,gender,birthday,email,address);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println("select");
		return member;
	}

	public List<Member> selectAllMembers(){
	
	List<Member> members = new ArrayList<>() ;
	
	
	// Step 1: Establishing a Connection
			try (Connection con = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_USERS);) {
				
				System.out.println(pstmt);
				// Step 3: Execute the query or update query
				ResultSet rs = pstmt.executeQuery();
				
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String mobile = rs.getString("mobile");
					String password = rs.getString("password");
					String name = rs.getString("name");
					String nickname = rs.getString("nickname");
					String gender = rs.getString("gender");
					Date birDate = rs.getDate("birthday");
					String birthday = Integer.toString(birDate.getYear()+1900)+"-"+Integer.toString(birDate.getMonth()+1)+"-"+Integer.toString(birDate.getDate());
					String email = rs.getString("email");
					String address = rs.getString("address");
					members.add(new Member(mobile,password,name,nickname,gender,birthday,email,address));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return members;
	}
	public boolean updateMember(Member member) throws SQLException, ParseException {

		boolean rowUpdated;
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE_USERS_SQL);) {
			
//			pstmt.setString(1, member.getMobile());
//			pstmt.setString(2, member.getPassword());
//			pstmt.setString(3, member.getName());
//			pstmt.setString(4, member.getNickname());
//			pstmt.setString(5, member.getGender());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse(member.getBirthday());
//			pstmt.setDate(6, new java.sql.Date(date.getTime()));
//			pstmt.setString(7, member.getEmail());
//			pstmt.setString(8, member.getAddress());

			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getNickname());
			pstmt.setString(4, member.getGender());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
//			Date date = sdf.parse(member.getBirthday());
//			pstmt.setDate(5, new java.sql.Date(date.getTime()));
			
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getMobile());
			System.out.println(member.getMobile());
			
			rowUpdated = pstmt.executeUpdate() > 0;
			
		}
		System.out.println("123456");
		return rowUpdated;
		
	}
	
	public boolean deleteMember(String mobile) throws SQLException {
		boolean rowDeleted;
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETE_USERS_SQL);) {

			pstmt.setString(1, mobile);
			rowDeleted = pstmt.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}



}