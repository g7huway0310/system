package Admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingMallBean.PageBean;

/**
 * Servlet implementation class AdminManageServlet
 */
@WebServlet("/AdminManageServlet")
public class AdminManageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ADMINLIST_PATH="adminManage/adminList.jsp";
	private static final String ADMINADD_PATH="adminManage/adminAdd.jsp";
	private static final String ADMINEDIT_PATH="adminManage/adminEdit.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		switch(action){
		case "list":
			adminList(request,response);
			break;
		case "add":
			adminAdd(request,response);
			break;
		case "update":
			adminUpdate(request,response);
			break;
		case "edit":
			adminEdit(request,response);
			break;
		case "del":
			adminDel(request,response);
			break;
		case "batDel":
			adminBatDel(request,response);
			break;
		case "find":
			adminFind(request,response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//查詢用戶
		private void adminList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			AdminDao ad=new AdminDaoImpl();
			int curPage=1;
			String page=request.getParameter("page");
			if(page!=null){
				curPage=Integer.parseInt(page);
			}
			
			int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
			
			PageBean pageBean=new PageBean(curPage,maxSize,ad.bookReadCount());
			
			request.setAttribute("adminList", ad.userList(pageBean));
			request.setAttribute("pageBean", pageBean);
			
			request.getRequestDispatcher(AdminManageServlet.ADMINLIST_PATH).forward(request, response);
			
		}
		

}
