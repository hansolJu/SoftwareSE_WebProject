package com.wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//관리자를 구분할 때 사용
		request.setAttribute("type", new Integer(0));
		return "/mngr/managerMain.jsp";//응답페이지
	}
}