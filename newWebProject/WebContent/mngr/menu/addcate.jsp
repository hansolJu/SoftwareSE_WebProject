<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
<jsp:include page="/mngr/managerMain.jsp" />
<title>카테고리 추가</title>
</head>
<body>
	<div style="float:left; padding-left:30px; width:90%">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<form method="post" action="/newWebProject/MngrMenuAction">
						<h3 style="text-align: center;">카테고리 생성</h3>
						<div class="form-group">
							<select class="form-control" name="cate_parent">
							<option value="">없음</option>
							<% ArrayList<CateDataBean> list = CateDBBean.getinstance().getList();
								for(int i = 0; i <  list.size(); i++){%>
								<option value="<%=list.get(i).getCate_num()%>"><%=list.get(i).getCate_name()%></option>
							<%} %>
							</select>
							<input type="text" class="form-control" name="cate_name" placeholder="카테고리 이름">
						</div>
							<input type="hidden" name="action" value="add_comp">
							<input type="submit" class="btn btn-primary form-control" value="생성">
					</form>
			</div>
		<div class="col-lg-4"></div>
	</div>
	</div>
</body>
</html>