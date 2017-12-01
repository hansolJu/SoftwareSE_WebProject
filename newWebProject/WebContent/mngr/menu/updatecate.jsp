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
	<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<form method="post" action="/newWebProject/MngrMenuAction">
						<h3 style="text-align: center;">카테고리 수정</h3>
						<div class="form-group">
							<select class="form-control" name="cate_parent">
							<option value="">없음</option>
							<% ArrayList<CateDataBean> list = CateDBBean.getinstance().getList();
								for(int i = 0; i <  list.size(); i++){%>
								<option value="<%=list.get(i).getCate_num()%>"><%=list.get(i).getCate_name()%></option>
							<%} %>
							</select>
							<input type="text" class="form-control" name="cate_name" value="${cate_name}">
						</div>
							<input type="hidden" name="action" value="update_comp">
							<inpit type="hideen" name="cate_num" value="${cate_num}">
							<input type="submit" class="btn btn-primary form-control" value="수정">
					</form>
			</div>
		<div class="col-lg-4"></div>
	</div>
	</div>
</body>
</html>