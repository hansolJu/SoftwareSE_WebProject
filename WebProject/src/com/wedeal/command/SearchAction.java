package com.wedeal.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String searchName = null;
		SearchService searchService = SearchService.getInstance();
		ArrayList<ProductDTO> list;
		try {
			searchName = request.getParameter("searchName");
		} catch(NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("error_message", e.getMessage() +"값을 입력해주세요");
			return "error";
		}
		try {
			list = searchService.selectProductName(searchName);
			request.setAttribute("searchNameList", list);
			request.setAttribute("search_value", searchName);
		} catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("error_message",  e.getMessage());
		}
	}
}
