package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ShoppingMallDAO.AdminsDAO;
import shoppingMallBean.Admin;
import shoppingMallBean.PageBean;
import sun.security.mscapi.CKeyPairGenerator.RSA;

public class AdminDaoImp1 implements AdminsDAO {

	private DataSource dataSource;

	private Connection conn;
	
	public AdminDaoImp1() {
		
	}

	public AdminDaoImp1(Connection connection) {
		// TODO Auto-generated constructor stub
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean userLogin(Admin admin) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select * from s_admin where userName=? and passWord= ?";
		try {
			InitialContext ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			DataSource ds = (DataSource) lookup;
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getPassWord());
			ResultSet executeQuery = pstmt.executeQuery();
			if (executeQuery != null) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public long bookReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Admin> userList(PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Admin> lu = new ArrayList<>();
		
		String sqlString = "SELECT * " + 
				"FROM   sometable" + 
				"ORDER BY name" + 
				"OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		try {
			InitialContext ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			DataSource ds = (DataSource) lookup;
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, (pageBean.getCurrentPage() - 1) * pageBean.getMaxsize());
			pstmt.setInt(2, pageBean.getTotalPage());
			ResultSet rs = pstmt.executeQuery(sqlString);

			while (rs.next()) {

				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String name = rs.getString("name");
				Admin admins = new Admin(id, userName, passWord, name);
				lu.add(admins);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lu;
	}

	@Override
	public boolean userAdd(Admin admin) {

		boolean flag = false;

		String sql = "insert into s_admin(userName,password,name) values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPassWord());
			pstmt.setString(3, admin.getName());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				flag = true;
				System.out.println("新增成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean userUpdate(Admin admin) {
		// TODO Auto-generated method stub
		//String sql="update s_admin set password=? , name=? where id =?";
		boolean flag = false;

		String sql = "insert into s_admin(userName,password,name) values(?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPassWord());
			pstmt.setString(3, admin.getName());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				flag = true;
				System.out.println("新增成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Admin findUser(Integer id) {
		// TODO Auto-generated method stub
		Admin admins = null;
		String sql = "select * from s_admin where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String name = rs.getString("name");
				admins = new Admin(id, userName, passWord, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return admins;
	}

	// 是否有相同管理員名稱
	@Override
	public boolean findUser(String username) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String sql = "select * from s_admin where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next() == true) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	@Override
	public boolean delUser(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String sql="delete from s_admin where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == true) {
				flag = true;
				System.out.println("刪除成功");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

}
