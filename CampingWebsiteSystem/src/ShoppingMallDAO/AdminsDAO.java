package ShoppingMallDAO;

import java.util.List;

import shoppingMallBean.Admins;
import shoppingMallBean.PageBean;

public interface AdminsDAO {
		//登入
		boolean userLogin(Admins admin);
		//獲取總共紀錄
		long bookReadCount();
		//獲取用戶(分頁顯示)
		List<Admins> userList(PageBean pageBean);
		//增加用戶
		boolean userAdd(Admins admin);
		//更新用户
		boolean userUpdate(Admins admin);
		//取得使用者資訊
		Admins findUser(Integer id);
		//使用者是否存在
		boolean findUser(String username);
		//根据id删除一个用户
		boolean delUser(int id);
		

}
