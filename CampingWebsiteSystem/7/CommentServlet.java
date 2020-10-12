

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import comment.CommentBean;
import comment.CommentDAO;
/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);

	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
        
	    
	   if (request.getParameter("submit")!=null) {
	       gotoSubmitProcess(request, response);}
	   else if (request.getParameter("confirm")!=null) {
	     gotoConfirmProcess(request, response);}
	}
	
	
	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    String Campgroundid;
	    String Memberid;
	    String Ranking;
	    String Content;
	   
	    Campgroundid = request.getParameter("Campgroundid").trim();	    
	    Memberid = request.getParameter("Memberid").trim();
	    Ranking = request.getParameter("Ranking").trim();
	    Content = request.getParameter("Content").trim();
	    
	    CommentBean rel_Comment = new CommentBean(Campgroundid,Memberid,Ranking,Content);
	    request.getSession(true).setAttribute("rel_Comment", rel_Comment);
	    System.out.println("j");
	    request.getRequestDispatcher("./DisplayComment1.jsp").forward(request,response);
	  }
	
	
	public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    try {
	      ctxt = new InitialContext();
	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/xe");

	      conn = ds.getConnection();


	      CommentDAO commentDAO = new CommentDAO(conn);
	      CommentBean commentData = (CommentBean)request.getSession(true).getAttribute("rel_Comment");
	      if (commentDAO.insertComment(commentData))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          request.getRequestDispatcher("/Done.jsp").forward(request,response);
	        }
	    } catch (NamingException ne) {
	      System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException e) {
	      System.out.println("Database Connection Error"); 
	    } finally {
	      try {
	        if (conn != null) conn.close();
	      } catch (Exception e) {
	        System.out.println("Connection Pool Error!");
	      }
	    }
	           
	  }

}
