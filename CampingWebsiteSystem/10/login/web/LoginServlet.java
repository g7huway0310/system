package login.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.bean.LoginBean;
import login.database.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");	
		
		
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		
		
		LoginBean loginBean=new LoginBean();
		loginBean.setMobile(mobile);
		loginBean.setPassword(password);
		
		LoginDao loginDao=new LoginDao();
		if(loginDao.validate(loginBean))
		{
			response.sendRedirect("list");
		}
		else {			
			request.setAttribute("message","帳號密碼有誤 請重新輸入" );
			request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}


