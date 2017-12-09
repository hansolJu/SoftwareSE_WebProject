<!--
스탭 리스트 페이지
스태프를 관리하는 페이지
작성자: 주한솔
최종수정일: 17.11.28
  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
<jsp:include page="/mngr/managerMain.jsp" />
<div id="staffList" style="float:left; padding-left:30px; width:90%">
		<ul>
			<li>활동 정지 회원수 : ${count}</li>
		</ul>
	<table class='table table-striped' style="border: 1px solid #dddddd; height=100;">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입 날짜</th>
				<th>활동 상태</th>
				<th>스탭 해임</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="staff" items="${staffList}">
				<tr>
					<td>${staff.user_id}</td>
					<td>${staff.user_name}</td>
					<td>${staff.user_date}</td>
					<c:choose>
						<c:when test="${staff.user_available == '1'}">
							<td>활동</td>
						</c:when>
						<c:when test="${staff.user_available == '2'}">
							<td>관리자</td>
						</c:when>
						<c:when test="${staff.user_available >= '3'}">
							<td>활동(스탭)</td>
						</c:when>
						<c:otherwise>
							<td>정지</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${staff.user_available != '2'}">
							<c:if test="${my_available == '2'}">
								<td><a href="/newWebProject/MngrStaffAction?mngrAction=dismissStaff&user_id=${staff.user_id}">스탭 해임</a></td>
							</c:if>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>