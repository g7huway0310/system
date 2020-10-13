package Admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.AdminDaoService;
import shoppingMallBean.Admin;

/**
 * Servlet implementation class Adminlogin
 */
@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
		String appContext = request.getContextPath();
		
        String mainPath="admin/index.jsp";
		
		String loginPath="login.jsp";
		
		String userName=request.getParameter("userName");
		
		String passWord=request.getParameter("passWord");
		
		Admin admin=new Admin(userName, passWord);
		
		AdminDaoService service=new AdminDaoService();
		
		List<String> list=new ArrayList<String>();
		
		if(userName==null) {
			list.add("名稱不能為空");
		}
		if(passWord==null) {
			list.add("密碼不能為空");
		}
		if(list.size()==0) {
			if(service.userLogin(admin)) {
				request.getSession().setAttribute("adminUser",admin );
				response.sendRedirect(mainPath);
				return;
			}else {
				list.add("帳號密碼錯誤");	
			}
		}
		request.setAttribute("infoList", list);
		
		System.out.println("轉");
		
//		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		
		response.sendRedirect(mainPath);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
