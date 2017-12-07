<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="wedeal.bean.BoardDBBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
<title></title>
</head>
<body>
	<!-- 메뉴 바 -->
	<jsp:include page="Menubar.jsp" />

	<!-- 회원 메뉴 -->
	<jsp:include page="userMenubar.jsp" />
	<!-- 좋아요 리스트 -->
	<c:set var="maxRow" value="4"/>
	<c:set var="board" value="<%=BoardDBBean.getinstance() %>" />
	<div class='container'>
		<div class='row'>
			<table class="table table-striped" style="border: 1px solid #dddddd; float:left; width:100%; ">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center; height: 30px;">내 좋아요</th>
					</tr>
					<tr>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">번호</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">게시글</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">일자</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="like" items="${userlikelist}" varStatus="status" >
							<tr>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${status.count}</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${like.getCate_num() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getBoard(like.getBoard_num()).getBoard_title() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${like.getLike_date() }</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>