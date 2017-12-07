<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ tag import="wedeal.bean.*" %>
<%@ tag import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://localhost:8080/newWebProject/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="http://localhost:8080/newWebProject/js/bootstrap.js"></script>
<style>
  /* 사이드바 래퍼 스타일 */
  
  #page-wrapper {
    padding-left: 250px;
  }
  
  #sidebar-wrapper {
    position: fixed;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #000;
    overflow-x: hidden;
    overflow-y: auto;
  }
  
  #page-content-wrapper {
    width: 100%;
    padding: 20px;
  }
  /* 사이드바 스타일 */
  
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
  }
  
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em;
  }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
  }
  
  .sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255, 255, 255, 0.2);
  }
  
  .sidebar-nav > .sidebar-brand {
    font-size: 1.3em;
    line-height: 3em;
  }

</style>


<%
	CateDBBean cate = CateDBBean.getinstance();
	ArrayList<CateDataBean> out_cate = cate.getList();
	ArrayList<CateDataBean> in_cate = cate.in_getList();
	out.println("<div id='page-wrapper' class='list-group' style='float:left'><div id='sidebar-wrapper'><ul class='sidebar-nav'><li class='sidebar-brand'><a href='board.jsp' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>전체게시판</a></li>");
	for(int i = 0; i < out_cate.size(); i++){
		out.println("<li class='sidebar-brand dropdown'><a href='board.jsp?cate_num="+out_cate.get(i).getCate_num()+">"+out_cate.get(i).getCate_name()+"</a></li>");
	
		for(int j = 0; j < in_cate.size(); j++){
			if(in_cate.get(j).getCate_parent() == out_cate.get(i).getCate_num()){
				out.println("<li><a href='board.jsp?cate_num="+in_cate.get(j).getCate_num()+"' class='list-group-item'>"+in_cate.get(j).getCate_name()+"</a></li>");
			}
		}
	}
	out.println("</ul></div><div>");
%>
