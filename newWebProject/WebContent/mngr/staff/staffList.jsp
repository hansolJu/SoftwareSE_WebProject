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
<title>Show All Users</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>스탭 역할</th><!--0:홈페이지 운영자 1:신고 관리자  -->
				<th>아이디 </th>
				<th>스탭 선정일</th>
				<th colspan=1>설정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${staffs}" var="staff">
				<tr>
					<td>
					<c:choose>
							<c:when test="${staff.author == '0'}">
           						홈페이지 운영자
       						</c:when>
							<c:when test="${staff.author == '1'}">
           						스탭
       						</c:when>
							<c:otherwise>
          						그외
       						</c:otherwise>
						</c:choose>
					</td> 
					<td><c:out value="${staff.id}" /></td>
					<td><c:out value="${staff.date}" /></td>
					<td><a href="/newWebProject/MngrStaffAction?action=delete&boardId=<c:out value="${staff.Id}"/>">스탭삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/newWebProject/MngrStaffAction?action=add">스탭 추가</a>
		<a href="/newWebProject/mngr/managerMain.jsp">뒤로 가기</a>
	</p>
</body>
</html>