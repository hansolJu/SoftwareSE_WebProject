<!-- 
활동 정지 회원관리 페이지
작성자 : 정은진
수정자:
최종수정일 : 17.11.21
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/newWebProject/js/jquery-1.11.0.min.js"></script>
<script src="/newWebProject/mngr/member/fullMemberManage.js"></script>


<div id="userList">
		<ul>
			<li>활동 정지 회원수 : ${count}</li>
		</ul>
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입 날짜</th>
				<th>정지 해제</th>
			</tr>
			<c:forEach var="user" items="${bannedUserList}">
				<tr>
					<td>${user.getUser_id()}</td>
					<td>${user.getUser_name()}</td>
					<td>${user.getUser_date()}</td>
					<td><td><a href="/newWebProject/MngrUserStartAction?user_id=${user.getUser_id()}">정지 해제</a></td>
				</tr>
			</c:forEach>
		</table>
</div>
<hr><button onclick = "../managerMain.jsp">뒤로 가기</button>