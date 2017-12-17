<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.UserDataBean"%>
<!-- 데이터 접근 함수 -->
<%@ page import="wedeal.bean.UserDBBean"%>
<!-- 빈즈 객체 -->

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
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
	<%
		String session_id = null;
		
		if(session.getAttribute("user_id")!=null){
			session_id = (String)session.getAttribute("user_id");
		}
		
		UserDBBean user = UserDBBean.getinstance();
		UserDataBean userinfo = user.getUser(session_id);
	%>
	<!-- 상단 메뉴 -->
	<jsp:include page="Menubar.jsp"/>
	<!-- 회원 메뉴 -->
	<jsp:include page="userMenubar.jsp" />
	
 	<!-- 회원 정보 조회 및 수정-->
	<div class="userinfo">
		<div id="profile" style="display:inline-block; border:1px #dddddd; float:left;height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>WeDEAL 프로필</h3>
			<hr>
			<div>
				<table class="table table-striped">
					<tr>
						<td width="60">이름 : </td>
						<td width="60"><%=userinfo.getUser_name() %></td>
					</tr>
					<tr>
						<td width="60">연령대 : </td>
						<td width="60"><%=userinfo.getUser_age() %></td>
					</tr>
				</table>
				<br>
			</div>
		</div>
		
		<div id="contact" style="display:inline-block; border:1px #dddddd; float:left;height:200px; width:500px;padding-left:50px; padding-right:50px;">
			<h3>연락처</h3>
			<hr>
			<div>
				<table class="table table-striped">
					<tr>
						<td>휴대전화 : </td>
						<td><%=userinfo.getUser_phone() %></td>
					</tr>
				</table>
				<br>
				<a href="http://localhost:8080/newWebProject/user/modifyPhone.jsp">수정</a>
			</div>
		</div>
		<br>
		<div id="secret" style="display:inline-block; border:1px #dddddd; float:left;height:200px; width:500px; padding-left:50px; padding-right:50px; ">
			<h3>비밀번호</h3>
			<hr>
			<div>
				<table class="table table-striped">
					<tr>
						<td><a href="http://localhost:8080/newWebProject/user/modifyPasswd.jsp">수정</a></td>
					</tr>	
				</table>
			</div>
		</div>
		
		<div id="withdraw" style="display:inline-block; border:1px #dddddd; float:left;height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>회원 탈퇴</h3>
			<hr>
			<div>
				<table class="table table-striped">
					<tr>
						<td><a href="http://localhost:8080/newWebProject/user/deleteUser.jsp">탈퇴</a>
					<tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>