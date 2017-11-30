<!--
스탭 리스트 페이지
스태프를 관리하는 페이지
작성자: 주한솔
최종수정일: 17.11.28
  -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>신고글 관리</title>
</head>
<body>
<h1>신고글 리스트</h1>
	<table border=1>
		<thead>
			<tr>
				<th>게시판이름 </th><!--0:홈페이지 운영자 1:신고 관리자  -->
				<th>신고된 아이디 </th>
				<th>신고 내용</th>
				<th>신고일자</th>
				<th>신고자 아이디</th>
				<th colspan=3>설정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${declars}" var="declar">
				<tr>
					<td><c:out value="${declar.boardName}" /></td>
					<td><c:out value="${declar.id}" /></td>
					<td><c:out value="${declar.content}" /></td>
					<td><c:out value="${declar.date}" /></td>
					<td><c:out value="${declar.reportId}" /></td>
					<td><a href="/newWebProject/MngrDeclarAction?action=delete&boardId=<c:out value="${declar.Id}"/>">글삭제</a></td>
					<td><a href="/newWebProject/MngrUserBanAction?user_id=${declar.getUserId()}">게시자 정지</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/newWebProject/mngr/managerMain.jsp">메인으로</a>
	</p>
</body>
</html>