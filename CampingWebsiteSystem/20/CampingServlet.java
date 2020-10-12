
import javax.servlet.*;
import java.io.*;

import java.sql.*;
//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CampingServlet")
public class CampingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    
	    response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
		   response.setHeader("Pragma","no-cache"); // HTTP 1.0
		   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
		   

	   if (request.getParameter("submit")!=null)
	     gotoSubmitProcess(request, response);

	}
	
	 public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		String campingname;
		String campingdate;
		String campingcontent;
		String address;
		String people;
	   
		campingname = request.getParameter("campingname").trim();
		campingdate = request.getParameter("campingdate").trim();
		campingcontent = request.getParameter("campingcontent").trim();
		address = request.getParameter("address").trim();
		people = request.getParameter("people").trim();
	    CampingBean reg_camping =  new CampingBean(campingname, campingdate, campingcontent, address, people);
	    request.getSession(true).setAttribute("reg_camping",reg_camping);

	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
		     
		      //�إ�Context Object,�s��JNDI Server
		      ctxt = new InitialContext();

		      //�ϥ�JNDI API���DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/xe");
		     //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //�VDataSource�nConnection
		      conn = ds.getConnection();

		      //�إ�Database Access Object,�t�dTable��Access
		      CampingDAO campingDAO = new CampingDAO(conn);
		      CampingBean campingData = (CampingBean)request.getSession(true).getAttribute("reg_camping");
		      if (campingDAO.insertCamping(campingData))
		        {
		          System.out.println("done!");
		          request.getSession(true).invalidate();
		          request.getRequestDispatcher("/ATThanks.jsp").forward(request,response);
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
