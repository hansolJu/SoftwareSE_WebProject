package com.wedeal.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneralSearchAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String keyword = request.getParameter("keyword");
		List</*물품*/> productList = null;
		List</*물품고유키*/> resultProductList = new ArrayList();
		
		productList = request.getAttribute("/*product*/");
		Iterator</*product*/> it = productList.iterator();
		while(it.hasNext()) {
			Product tempProduct = it.next(); //getName
			if(keyword.contains(tempProduct.getName())) //keyword와 비교
				resultProductList.add(tempProduct.getKey()); //keyword 저장
		}
		request.setAttribute("search_result", resultProductList);
		//return page;
	}

}
