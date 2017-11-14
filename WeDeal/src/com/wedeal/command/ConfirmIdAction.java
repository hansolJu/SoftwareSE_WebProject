package com.wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedeal.bean.LogonDBBean;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		System.out.println("?��?��~");
		//주어�? id?�� 중복?���?�? 체크?�� 값을 반환.
		LogonDBBean manager = LogonDBBean.getInstance();
		int check= manager.confirmId(id);
		
		request.setAttribute("check", new Integer(check));
		return "/member/confirmId.jsp";
	}
}