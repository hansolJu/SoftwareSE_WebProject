<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>

<title>카테고리 수정</title>
</head>
<body>
	
	<div class="row">
		<form method="post" action="#">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">카테고리 번호</th>
						<th style="background-color: #eeeeee; text-align: center;">카테고리 이름</th>
						<th style="background-color: #eeeeee; text-align: center;">상위 카테고리 이름</th>
						<th style="background-color: #eeeeee; text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<%	CateDBBean cate = CateDBBean.getinstance();
						ArrayList<CateDataBean> list = cate.List();
						for(int i = 0; i < list.size(); i++){
					%>
					<tr>
						<td><%=list.get(i).getCate_num() %></td>
						<td><%=list.get(i).getCate_name() %></td>
					<% if(list.get(i).getCate_parent()>0){ %>
						<td><%=cate.getcate(list.get(i).getCate_parent()).getCate_name() %>
					<%} else{%>	
						<td></td>
					<%} %>
						<td>
						<a href="update.jsp?cate_num=<%=list.get(i).getCate_num()%>"><input type=button class="btn btn-primary" value="수정"></a>
						<a onclick="return confirm('정말로 삭제하시겠습니까?')"  href="./DeleteCateAction" class="btn btn-primary">삭제</a>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
			</form>
		</div>
		<a href="addcate.jsp"><input type=button class='btn btn-primary pull-right' value="추가"></a>
</body>
</html>