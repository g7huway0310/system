

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");			
		
		String mobile=request.getParameter("mobile");
		String password =request.getParameter("password");
		String name=request.getParameter("name");
		String nickname=request.getParameter("nickname");
		String gender=request.getParameter("gender");
		String birthday=request.getParameter("birthday");		
		String email=request.getParameter("email");
		String address=request.getParameter("address");
	
		
		Member member=new Member(mobile,password,name,nickname,gender,birthday,email,address);
		RegisterDao rDao=new RegisterDao();
		try {
			String result=rDao.insert(member);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("smessage","註冊成功,請登入" );
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
