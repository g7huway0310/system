package article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;



public class Artinupdeq {
	
	private DataSource datasource;
	
	public DataSource getDataSource() {
		if (datasource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("project2");
			ds.setPassword("project2");
			ds.setMaxTotal(50);
			ds.setMaxIdle(50);

			datasource = ds;
		}
		return datasource;

	}
	
	
	
	public List<Article> listData(){
		List<Article> list = new ArrayList<>();
		try (Connection connection = getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from ARTICLE");) {
			while (rs.next()) {

				Article article = new Article();
				int Memberid = rs.getInt("Memberid");
				article.setMemberid(Memberid);
				String Title = rs.getString("Title");
				article.setTitle(Title);
				String Text = rs.getString("Text");
				article.setText(Text);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public void insertTitle(Article data) {
		try {
			Connection connection = getDataSource().getConnection();
			{

				PreparedStatement pstmt = connection.prepareStatement(
						"insert into ARTICLE (Title,Text,Memberid) values(?,?,?)");
				pstmt.setString(1, data.getTitle());
				pstmt.setString(2, data.getText());
				pstmt.setInt(3, data.getMemberid());

				int i = pstmt.executeUpdate();
				if (i >= 1) {
					System.out.println("新增成功");
				} else {
					System.out.println("新增失敗");
				}
				pstmt.clearParameters();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void insertText(Article data) {
		try {
			Connection connection = getDataSource().getConnection();
			{

				PreparedStatement pstmt = connection.prepareStatement(
						"insert into ARTICLE (Title,Text,Memberid) values(?,?,?)");
				pstmt.setString(1, data.getTitle());
				pstmt.setString(2, data.getText());
				pstmt.setInt(3, data.getMemberid());

				int i = pstmt.executeUpdate();
				if (i >= 1) {
					System.out.println("新增成功");
				} else {
					System.out.println("新增失敗");
				}
				pstmt.clearParameters();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateTitle(Article data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement(
					"update Title set Title=?, Text=?, Memberid=? where Text=? and Memberid=? ");
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getText());
			pstmt.setInt(3, data.getMemberid());
			
			int i = pstmt.executeUpdate();
			if (i >= 1) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失敗");
			}
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void updateText(Article data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement(
					"update Title set Title=?, Text=?, Memberid=? where Title=? and Memberid=? ");
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getText());
			pstmt.setInt(3, data.getMemberid());
			
			int i = pstmt.executeUpdate();
			if (i >= 1) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失敗");
			}
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
