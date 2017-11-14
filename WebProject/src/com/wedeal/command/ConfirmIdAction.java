package com.wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedeal.bean.UserDBBean;

public class ConfirmIdAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		System.out.println("여기까지~");
		
		UserDBBean manager = UserDBBean.getinstance();
		int check = manager.registerCheck(user_id);
		
		request.setAttribute("check", new Integer(check));
		return "/user/confirmId.jsp";
	}
	
}
