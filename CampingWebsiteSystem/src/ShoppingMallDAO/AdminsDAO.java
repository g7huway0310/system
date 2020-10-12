package ShoppingMallDAO;

import java.util.List;

import shoppingMallBean.Admin;
import shoppingMallBean.PageBean;

public interface AdminsDAO {
		//登入
		boolean userLogin(Admin admin);
		//獲取總共紀錄
		long bookReadCount();
		//獲取用戶(分頁顯示)
		List<Admin> userList(PageBean pageBean);
		//增加用戶
		boolean userAdd(Admin admin);
		//更新用户
		boolean userUpdate(Admin admin);
		//取得使用者資訊
		Admin findUser(Integer id);
		//使用者是否存在
		boolean findUser(String username);
		//根据id删除一个用户
		boolean delUser(int id);
		

}
