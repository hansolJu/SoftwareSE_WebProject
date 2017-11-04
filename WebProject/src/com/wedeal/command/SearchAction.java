package com.wedeal.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedeal.dao.ProductDAO;
import com.wedeal.model.ProductDataBean;

/**
 * 2017.11.05
 * @author eunjin
 * 검색기능을 하는 Action클래스
 * TO-DO : 상세검색
 */
public class SearchAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String searchName = null;  //검색하려는 키워드
		ProductDAO searchService = ProductDAO.getInstance();
		ArrayList<ProductDataBean> list;  //검색 결과를 가져올 list
		try {
			searchName = request.getParameter("searchName");
		} catch(NullPointerException e) {  //키워드의 내용이 없을 경우
			e.printStackTrace();
			request.setAttribute("error_message", e.getMessage() +"값을 입력해주세요");  //에러 메시지 저장
			return "error";
		}
		try {
			list = searchService.selectProductName(searchName);  //물품의 이름으로 검색하는 메소드 실행
			request.setAttribute("searchNameList", list);  //검색 결과 리스트 attribute에 저장
			request.setAttribute("search_value", searchName);  //키워드 저장
		} catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("error_message",  e.getMessage());
			return "error";
		}
		return "success"; //반환형이 string형이기 때문에 일단 해놈. 상의 ㄱㄱ
	}
}
