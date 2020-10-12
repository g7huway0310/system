

import java.sql.*;

import comment.CommentBean;

public class CommentDAO {
	private Connection conn;

public CommentDAO(Connection conn) {
	this.conn = conn;
}


public boolean insertComment(CommentBean commentData) {
	try {
		String sqlString = "insert into Ranking values ('"
				+commentData.getCampgroundid()+"','"
				+commentData.getMemberid()+"','"
				+commentData.getRanking()+"','"
				+commentData.getContent()+ "')";
	
		  Statement stmt = conn.createStatement();
	      System.out.println(sqlString);
		    int updatecount = stmt.executeUpdate(sqlString);
	      stmt.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("°ÝÃD:" + e);
			  return false;
	    }
	  }




}