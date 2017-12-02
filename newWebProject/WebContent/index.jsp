<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menutag" %>
<!-- 
	main페이지
	로그인이 되어있지 않은 경우 접속하기를 통해 로그인, 회원가입이 가능. 로그인은 login.jsp로, 회원가입은 join.jsp로 이동.
	로그인이 되어있는 경우 마이 페이지를 통해 로그아웃 가능. UserLogoutServlet을 통해 session이 invalidate()됨.
	최종 수정: 2017/11/05
 -->
 
<!DOCTYPE html >
<html>
<head>
	<title>메인</title>
	<script type="text/javascript"> 
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: '/newWebProject/LogoutAction',
			})
		}
	</script>
</head>
<body>
	
	<!-- 상단바 -->
	<jsp:include page="user/Menubar.jsp"/>
	
	<!-- 메뉴 생성 부분 -->
	<menutag:menu/>
	
	<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
	};
	</style>
	<!-- 추천부분 -->
	<showtag:show/>
</body>
</html>