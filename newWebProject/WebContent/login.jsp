<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	로그인 페이지
	ID/PW를 입력했을떄 DB에 정보가 존재할 경우 로그인 성공
	ID/PW중 하나라도 틀리거나, 적지 않은 경우에는 UserLoginServlet에서 session에 경고메세지를 담아 보내고, login.jsp에서 경고 메세지 출력
	로그인 성공할 경우 main페이지로 이동(로그인 한 상태에서)
	로그인한 회원의 정보는 session으로 유지
	최종 수정: 2017/11/05
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>로그인 화면</title>
</head>
<body>
	<!-- 상단바 -->
	<jsp:include page="Menubar.jsp"/>
	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="./LoginAction">
					<h3 style="text-align: center;">로그인</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="user_id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="user_pw" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="로그인">
				</form>
		</div>
		<div class="col-lg-4"></div>
	</div>
	</div>
	
</body>
</html>