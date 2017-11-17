<!-- 
전체회원관리 페이지
작성자 : 정은진
수정자:
최종수정일 : 17.11.16
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<script src="/wedeal/js/jquery-1.11.0.min.js"></script>
<script src="/wedeal/mngr/member/fullMemberManage.js"></script>


<div class="list">
<div id="button">
	<button id="bb">제발ㅠ</button>
</div>
	<c:if test="${empty count}">
		<ul>
			<li>사용자가 없습니다.
		</ul>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th>사용자 이름</th>
				<th>가입 날짜</th>
				<th>사용자 아이디</th>
				<th>사용자 아이디</th>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.getUser_name()}</td>
					<td>${user.getUser_date()}</td>
					<td>${user.getUser_id()}</td>
					<td>${user.getUser_id()}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

