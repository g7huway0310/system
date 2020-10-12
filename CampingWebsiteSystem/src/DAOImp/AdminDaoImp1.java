package DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ShoppingMallDAO.AdminsDAO;
import shoppingMallBean.Admins;
import shoppingMallBean.PageBean;
import sun.security.mscapi.CKeyPairGenerator.RSA;

public class AdminDaoImp1 implements AdminsDAO {

	private DataSource dataSource;

	private Connection conn;

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
	public boolean userLogin(Admins admin) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select * from s_admin where userName=? and passWord= ?";
		try {
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
		}
		return flag;
	}

	@Override
	public long bookReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Admins> userList(PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Admins> lu = new ArrayList<>();
		String sql = "select * from s_admin  ?,?";
		String sqlString = "SELECT * FROM example_table WHERE ROWNUM < ?" + "MINUS"
				+ "SELECT * FROM example_table WHERE ROWNUM < ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, (pageBean.getCurrentPage() - 1) * pageBean.getMaxsize());
			pstmt.setInt(2, pageBean.getTotalPage());
			ResultSet rs = pstmt.executeQuery(sqlString);

			while (rs.next()) {

				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String name = rs.getString("name");
				Admins admins = new Admins(id, userName, passWord, name);

				lu.add(admins);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lu;
	}

	@Override
	public boolean userAdd(Admins admin) {

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
	public boolean userUpdate(Admins admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admins findUser(Integer id) {
		// TODO Auto-generated method stub
		Admins admins = null;
		String sql = "select * from s_admin where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userName = rs.getString("userName");
				String passWord = rs.getString("passWord");
				String name = rs.getString("name");
				admins = new Admins(id, userName, passWord, name);
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
