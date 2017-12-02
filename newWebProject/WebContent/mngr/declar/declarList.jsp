<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<jsp:include page="/mngr/managerMain.jsp" />
<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
<title>신고글 관리</title>
</head>
<body>
  <div style="float:left; padding-left:30px; width:90%">
	<table class='table table-striped' style="border: 1px solid #dddddd; height=100;">
		<thead>
			<tr>
				<th>게시판 번호 </th><!--0:홈페이지 운영자 1:신고 관리자  -->
				<th>신고된 아이디 </th>
				<th>신고 내용</th>
				<th>신고일자</th>
				<th colspan=3>설정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${declarList}" var="declar">
				<tr>
					<td>${declar.board_num}</td>
					<td>${declar.user_id}</td>
					<td>${declar.declaration_content}</td>
					<td>${declar.declaration_date}</td>
					<td><a href="/newWebProject/MngrDeclarAction?action=boardDelete&board_num=${declar.board_num}">글삭제</a></td>
					<td><a href="/newWebProject/MngrDeclarAction?action=ban&user_id=${declar.user_id}">게시자 정지</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  </div>
</body>
</html>