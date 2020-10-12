package article;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ArticleDao {
	private Connection conn;
	

public ArticleDao(Connection conn) {
	this.conn = conn;
}	
	
	
public boolean insertArticle(ArticleBean articleData) {
	try {
		String sqlString = "insert into Article values ('"
				+articleData.getTitle()+"','"	
				+articleData.getText()+"','"
				+articleData.getMemberid()+"','"
				+articleData.getAriticleid()+"')";
	
		  Statement stmt = conn.createStatement();
	      System.out.println(sqlString);
		    int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("新增文章失敗:" + e);
			  return false;
	    }
	  }

public boolean updateArticle(ArticleBean articleData) {	
	try {
		String sqlString = "update Article set title=?,text=? where articleid=?";
	
		  Statement stmt = conn.createStatement();
		  
		  PreparedStatement psmt=conn.prepareStatement(sqlString);
		  
		  psmt.setString(1, articleData.getTitle());
		  psmt.setString(2, articleData.getText());
		  psmt.setString(3, articleData.getAriticleid());

		  
	      System.out.println(sqlString);
		    int updatecount = stmt.executeUpdate(sqlString);
		    
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("修改文章失敗:" + e);
			  return false;
	    }
}

public boolean deleteArticle(String title) {
	try {
		String sqlString = "delete from Article where title='"+title+"'";
	
		  Statement stmt = conn.createStatement();
	      System.out.println(sqlString);
		    int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("刪除文章失敗:" + e);
			  return false;
	    }
}

public List<ArticleBean> getAllArticle(){
	List<ArticleBean> list = new ArrayList<ArticleBean>();
	try {
		String sqlString ="select * from Article";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			ArticleBean articleBean= new ArticleBean(); 
			articleBean.setMemberid(rs.getString("memberid"));
			articleBean.setText(rs.getString("text"));
			articleBean.setTitle(rs.getString("title"));
			list.add(articleBean);
		}
		System.out.println(list.size());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return list;
	
}


public List<ArticleBean> getArticleSearch() {
	List<ArticleBean> atlsrh = new ArrayList<ArticleBean>();	
	try {
		String sqlString ="select * from Article where title like '%%' order by articleid";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			ArticleBean articleBean= new ArticleBean(); 
			articleBean.setMemberid(rs.getString("memberid"));
			articleBean.setText(rs.getString("text"));
			articleBean.setTitle(rs.getString("title"));
			atlsrh.add(articleBean);
		}
		System.out.println(atlsrh.size());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return atlsrh;
	
}





}