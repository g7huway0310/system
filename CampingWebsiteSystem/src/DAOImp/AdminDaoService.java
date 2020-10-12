package DAOImp;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import ShoppingMallDAO.ProductDAO;

public class AdminDaoService implements{
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection connection = null;
	ProductDAO dao = null;

}
