package Admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImp.AdminDaoImp1;
import DAOImp.AdminDaoService;
import ShoppingMallDAO.AdminsDAO;
import shoppingMallBean.Admin;
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
			
			AdminsDAO service=new AdminDaoService();
			
			int curPage=1;
			String page=request.getParameter("page");
			if(page!=null){
				curPage=Integer.parseInt(page);
			}
			
			int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
			
			PageBean pageBean=new PageBean(curPage,maxSize,service.bookReadCount());
			
			request.setAttribute("adminList", service.userList(pageBean));
			
			request.setAttribute("pageBean", pageBean);
			
			request.getRequestDispatcher(AdminManageServlet.ADMINLIST_PATH).forward(request, response);
			
		}
    
    
    private void adminAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
    	
    	
    	AdminsDAO service=new AdminDaoService();
    	
    	Admin admin=new Admin(request.getParameter("userName"),request.getParameter("passWord"),
				request.getParameter("name"));
		
		if(new AdminDaoService().findUser(admin.getUserName())){
			request.setAttribute("adminMessage", "用戶名稱已經存在");
			request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
		}else{
			
			if(service.userAdd(admin)){
				request.setAttribute("adminMessage", "新增成功");
				adminList(request, response);
			}else{
				request.setAttribute("adminMessage", "新增失敗");
				request.getRequestDispatcher(AdminManageServlet.ADMINADD_PATH).forward(request, response);
			}
		}
    	
    	
		
	}
    private void adminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	Admin admin=new Admin(Integer.parseInt(request.getParameter("id")),
				request.getParameter("passWord"),
				request.getParameter("name")
				);
		
    	AdminsDAO service=new AdminDaoService();
    	
		if(service.userUpdate(admin)) {
			request.setAttribute("adminMessage", "用户更新成功");
			adminList(request, response);//通過servlet跳到listuser
		}else {
			//更新失敗跳轉修改
			request.setAttribute("adminMessage", "用户更新失败");
			request.setAttribute("adminInfo", service.findUser(Integer.valueOf(admin.getId())));//这里回去是Admin对象
			request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
		}
		
	}
    private void adminEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		AdminsDAO service=new AdminDaoService();
		request.setAttribute("adminInfo",service.findUser(Integer.valueOf(id)));//
		request.getRequestDispatcher(AdminManageServlet.ADMINEDIT_PATH).forward(request, response);
	}
    
    private void adminDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id=Integer.parseInt(request.getParameter("id"));
		
		AdminsDAO service=new AdminDaoService();
		if(service.delUser(id)) {
			request.setAttribute("adminMessage", "用户已删除");
		}else {
			request.setAttribute("adminMessage", "用户删除失败");
		}
		//showlist
		adminList(request, response);
	}
    
    
		

}
