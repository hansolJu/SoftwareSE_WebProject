package com.wedeal.command;
/*
 * CommandAction 는 쇼핑몰에서 명령어 처리 클래스의 진입점으로, 명령어 처리 클래스를 관리한다.
 * CommandAction separates and manages shopping as a career point in the command class.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public String requestPro(
		HttpServletRequest request, HttpServletResponse response)
		throws Throwable;
}