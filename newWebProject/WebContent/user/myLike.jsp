<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<div class='container'>
		<div class='row'>
			<table class="table table-striped" style="border: 1px solid #dddddd; float:left; width:100%; ">
				<thead>
					<tr>
						<th colspan="5" style="background-color: #eeeeee; text-align: center; height: 30px;">내 좋아요</th>
					</tr>
					<tr>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">번호</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">카테고리</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">게시글</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">내용</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="comment" items="${usercommentlist}" varStatus="status" >
							<tr>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${status.count}</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${comment.getCate_num() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${comment.getBoard_num() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${comment.getComment_content() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${comment.getComment_date() }</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>