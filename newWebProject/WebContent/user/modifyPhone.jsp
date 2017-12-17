<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="wedeal.bean.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>회원 연락처 수정</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
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
		//세션 설정
		if(session.getAttribute("user_id")!=null){
			session_id = (String)session.getAttribute("user_id");
		}
		UserDBBean user = UserDBBean.getinstance();
		UserDataBean userinfo = user.getUser(session_id);
	%>
	<jsp:include page="Menubar.jsp"/>
	
	<jsp:include page="userMenubar.jsp"/>
	
	<div>
		<div style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>연락처 수정</h3>
			<br>
			<div class="form-group">
				<form name="modify" method="POST" action="/newWebProject/UserPhoneModifyAction">
					연락처 <input class="form-control" type="text" name="user_phone" value="<%=userinfo.getUser_phone() %>" size="15">
				<br>
				<input type="submit" name="Submit" value="수정">
				</form>
			</div>
		</div>
	</div>
</body>
</html>