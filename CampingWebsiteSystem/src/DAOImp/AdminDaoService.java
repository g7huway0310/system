package DAOImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ShoppingMallDAO.AdminsDAO;
import ShoppingMallDAO.ProductDAO;
import shoppingMallBean.Admin;
import shoppingMallBean.PageBean;

public class AdminDaoService implements AdminsDAO {
	
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;
	AdminsDAO dao;
	@Override
	public boolean userLogin(Admin admin) {
		// TODO Auto-generated method stub
		boolean userLoginState=false;
		
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1();
			userLoginState = dao.userLogin(admin);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

		return userLoginState;
		   
    }
	@Override
	public long bookReadCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Admin> userList(PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Admin> userList = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
			
	        userList = dao.userList(pageBean);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
        return userList;
	}
	@Override
	public boolean userAdd(Admin admin) {
		// TODO Auto-generated method stub
		boolean userAdd = false;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
	        userAdd = dao.userAdd(admin);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		 return userAdd;
	}
	@Override
	public boolean userUpdate(Admin admin) {
		// TODO Auto-generated method stub
		boolean userAdd = false;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
	        userAdd = dao.userUpdate(admin);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		 return userAdd;
	}
	@Override
	public Admin findUser(Integer id) {
		// TODO Auto-generated method stub
		Admin admin = null;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
	        admin = dao.findUser(id);
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		 return admin;
	}
	@Override
	public boolean findUser(String username) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
	        flag = dao.findUser(username);
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		return flag;
	}
	@Override
	public boolean delUser(int id) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		try {
			ctxt = new InitialContext();
			Object lookup = ctxt.lookup("java:comp/env/jdbc/xe");
			ds = (DataSource) lookup;
			connection = ds.getConnection();
			dao = new AdminDaoImp1(connection);
	        flag = dao.delUser(id);
	        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
		return flag;
	}

}
