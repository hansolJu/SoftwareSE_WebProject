package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedeal.bean.UserDBBean;
import com.wedeal.bean.UserDataBean;

public class RegisterProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		UserDataBean member = new UserDataBean();
		member.setUser_name(request.getParameter("user_name"));
		member.setUser_age(Integer.parseInt(request.getParameter("user_age")));
		member.setUser_phone(request.getParameter("user_phone"));
		member.setUser_id(request.getParameter("user_phone"));
		member.setUser_pw(request.getParameter("user_pw"));
		member.setUser_hope(request.getParameter("user_hope"));
		
		UserDBBean dbPro = UserDBBean.getinstance();
		dbPro.register(member);
		
		return "/user/registerPro.jsp";
	}
}

