<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" %>
<%@ tag import="wedeal.bean.*" %>
<%@ tag import="java.util.*" %>


<%
	CateDBBean cate = CateDBBean.getinstance();
	ArrayList<CateDataBean> out_cate = cate.getList();
	ArrayList<CateDataBean> in_cate = cate.in_getList();
	out.println("<div id='catelist' class='list-group' style='float:left'><a href='board.jsp' class='list-group-item'>전체게시판</a>");
	for(int i = 0; i < out_cate.size(); i++){
		out.println("<a href='board.jsp?cate_num="+out_cate.get(i).getCate_num()+"'class='list-group-item' >"+out_cate.get(i).getCate_name()+"</a>");
	
		for(int j = 0; j < in_cate.size(); j++){
			if(in_cate.get(j).getCate_parent() == out_cate.get(i).getCate_num()){
				out.println("<a href='board.jsp?cate_num="+in_cate.get(j).getCate_num()+"' class='list-group-item'>"+in_cate.get(j).getCate_name()+"</a>");
			}
		}
	}
	out.println("</div>");
%>
