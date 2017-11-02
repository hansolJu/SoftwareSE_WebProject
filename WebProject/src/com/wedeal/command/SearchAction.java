package com.wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class SearchAction {
	List</*물품*/> productList = null;
	List</*물품고유키*/> resultProductList = null;
}
