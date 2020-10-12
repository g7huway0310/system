

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PRIVATE_MEMBER;


/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private MemberDao memberDao;
  

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		memberDao=new MemberDao();
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");	
		
		String action=request.getServletPath();
		switch(action)
		{
		case "/new":
			showNewForm(request,response);
			break;		
		case "/insert":
			try {
				try {
					insertMember(request,response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "/delete":
			try {
				deleteMember(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				try {
					updateMember(request,response);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;			
			default:
			try {
				listMember(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member-form.jsp");
			dispatcher.forward(request, response);
		}
		
		private void insertMember(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			Member newMember = new Member(mobile,password,name,nickname,gender,birthday,email,address);
			memberDao.insertMember(newMember);
			//response.sendRedirect("list");
			
			request.setAttribute("smessage","註冊成功,請登入" );
			response.sendRedirect("login.jsp");
		}
		
		private void deleteMember(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String mobile = request.getParameter("mobile");
			try {
				memberDao.deleteMember(mobile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			String mobile= request.getParameter("mobile");
			Member existingMember ;
			try {
				existingMember = memberDao.selectMember(mobile);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member-forme.jsp");
			
			request.setAttribute("member", existingMember);
			dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
		
		private void updateMember(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			String name= request.getParameter("name");
			String nickname = request.getParameter("nickname");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			Member member = new Member(mobile,password,name,nickname,gender,birthday,email,address);
			
			memberDao.updateMember(member);
			response.sendRedirect("list");
		}
		
		
		private void listMember(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			try {
				
				List<Member> listMember = memberDao.selectAllMembers();
			request.setAttribute("listMember", listMember);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member-list.jsp");
			dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}

	



