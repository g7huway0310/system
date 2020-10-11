package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOImp.ProductPageDAOImp;
import shoppingMallBean.ShoppingProduct;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	int pageNo = 1;
	
	int searchPageNo = 1;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
		String opt = request.getParameter("searchType");
		
        System.out.println(opt);
		
		switch(opt)
		
		{
		case "keyWord":
			String keyword = request.getParameter("q");
			SearchBrand(request,response);
			break;
		case "price":
			String highPriceString=request.getParameter("maxPrice");
			System.out.println(highPriceString);
			SearchPrice(request,response);
			break;
		}
	}
	
    private void SearchBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String keyword;
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("keyWords")==null) {
			 keyword = request.getParameter("keyWord");
			 session.setAttribute("keyWords", keyword);
			 System.out.println(keyword);
		}else {
			 keyword=(String) session.getAttribute("keyWords");
		}
		
		String pageNoStr = request.getParameter("searchPageNo");
		
		System.out.println("目前頁面是"+pageNoStr);
		
		if (pageNoStr == null) {  
			searchPageNo = 1;
			// 讀取瀏覽器送來的所有 Cookies
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("searchPageNo")) {
						try {
							pageNo = Integer.parseInt(c.getValue().trim());
						} catch (NumberFormatException e) {
							;
						}
						break;
					}
				}
			}
		} else {
			try {
				searchPageNo = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) {
				searchPageNo = 1;
			}
		}
		
		ProductPageDAOImp service=new ProductPageDAOImp();
		
		service.setSearchPageNo(searchPageNo);
		
		request.setAttribute("baBean", service);
		
		
		System.out.println("關鍵字"+keyword);
		
		List<ShoppingProduct> pageProducts = service.SearchBrandItem(keyword);
		
		int totalPages = service.getSearchTotalPage(keyword);
		
		session.setAttribute("searchPageNo", String.valueOf(searchPageNo));
		
		request.setAttribute("searchtotalPages", totalPages);
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		
		request.setAttribute("searchpageProducts", pageProducts);
		
		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
				// -----------------------
				Cookie pnCookie = new Cookie("SearchpageNo", String.valueOf(searchPageNo));
				Cookie keyCookie=new Cookie("Searchkey", keyword);
//			    // 設定Cookie的存活期為30天
				pnCookie.setMaxAge(30 * 24 * 60 * 60);
//			    // 設定Cookie的路徑為 Context Path		
				pnCookie.setPath(request.getContextPath());
//				// 將Cookie加入回應物件內
				response.addCookie(pnCookie);
				response.addCookie(keyCookie);
				// -----------------------
		
	    RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
		
		rd.forward(request, response);
		
		return;
		
	}
    private void SearchPrice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	
    request.removeAttribute("searchpageProducts"); 	
    	
	String keyword;
	
	int minPrice =Integer.valueOf(request.getParameter("minPrice"));
	int maxPrice =Integer.valueOf(request.getParameter("maxPrice"));
	int category_id =Integer.valueOf(request.getParameter("category_id"));
	
	System.out.println(category_id);
	
	HttpSession session = request.getSession(false);
	
	if (session.getAttribute("keyWords")==null) {
		 keyword = request.getParameter("keyWord");
		 session.setAttribute("keyWords", keyword);
		 System.out.println(keyword);
	}else {
		 keyword=(String) session.getAttribute("keyWords");
	}
	
	String pageNoStr = request.getParameter("searchPageNo");
	
	System.out.println("目前頁面是"+pageNoStr);
	
	if (pageNoStr == null) {  
		searchPageNo = 1;
		// 讀取瀏覽器送來的所有 Cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("searchPageNo")) {
					try {
						pageNo = Integer.parseInt(c.getValue().trim());
					} catch (NumberFormatException e) {
						;
					}
					break;
				}
			}
		}
	} else {
		try {
			searchPageNo = Integer.parseInt(pageNoStr.trim());
		} catch (NumberFormatException e) {
			searchPageNo = 1;
		}
	}
	
	ProductPageDAOImp service=new ProductPageDAOImp();
	
	service.setSearchPageNo(searchPageNo);
	
	request.setAttribute("baBean", service);
	
	List<ShoppingProduct> pageProducts = service.SearchPrice(minPrice, maxPrice,category_id);
	
	
	
	int totalPages = service.getPriceSearchTotalPage(minPrice, maxPrice, category_id);
	
	
	
	session.setAttribute("searchPageNo", String.valueOf(searchPageNo));
	
	request.setAttribute("searchtotalPages", totalPages);
	// 將讀到的一頁資料放入request物件內，成為它的屬性物件
	request.setAttribute("searchpageProducts", pageProducts);
	
	
	
	
	
	// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
			// -----------------------
			Cookie pnCookie = new Cookie("SearchpageNo", String.valueOf(searchPageNo));
			Cookie keyCookie=new Cookie("Searchkey", keyword);
//		    // 設定Cookie的存活期為30天
			pnCookie.setMaxAge(30 * 24 * 60 * 60);
//		    // 設定Cookie的路徑為 Context Path		
			pnCookie.setPath(request.getContextPath());
//			// 將Cookie加入回應物件內
			response.addCookie(pnCookie);
			response.addCookie(keyCookie);
			// -----------------------
	
    RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
	
	rd.forward(request, response);
	
	return;
	
}


}
