<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="/WebProject/user/login.js"></script>

<c:if test="${empty sessionScope.id }">
	<div id="lStatus">
		<ul>
			<li><label for="user_id">아이디</label>
				<input type="text" id="user_id" placeholder="아이디" name="user_id" maxlength="10">
				<label for="user_pw">비밀번호</label>
				<input type="password" id="user_pw" onkeyup="passwordCheckFunction();" placeholder="비밀번호" name="user_pw" maxlength="20">
				<button id = "uLogin">로그인</button>
				<button id = "uRes">회원가입</button>
		</ul>
	</div>
</c:if>
<c:if test="${!empty sessionScope.id}">
	<div id="lStatus">
		<ul>
			<li>${sessionScope.id}님이 로그인 하셨습니다.
				<div id="info">
					<table>
						<tr height="10">
							<td><button id="uLogout">로그아웃</button>
					</table>
				</div>
		</ul>
	</div>
</c:if>
	