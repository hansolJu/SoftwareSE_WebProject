<!-- 
	작성자 : 채지훈
	작성일 : 2017.11.26
	수정자 : 채지훈
	수정일 : 2017.11.26
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="wedeal.bean.UserDBBean, wedeal.bean.UserDataBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.css">
<title>Withdraw user</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script type="text/javascript">
	function logout() {
		var user_id = $('#user_id').val();
		$.ajax({
			type : 'POST',
			url : '/newWebProject/UserLogoutServlet',
		})
	}

	function begin() {
		document.withdraw.user_pw.focus();
	}

	function checkIt() {
		if (!document.withdraw.user_pw.value) {
			alert("비밀번호를 입력하지 않으셨습니다.");
			document.withdraw.user_pw.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<jsp:include page="Menubar.jsp" />
	<!-- 회원 메뉴 -->
	<jsp:include page="userMenubar.jsp"/>

	<!-- 회원 탈퇴 -->
	<div>
		<div style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>회원 탈퇴</h3>
			<br>
			<div class="form-group">
				<form name="withdraw" method="post" action="/newWebProject/UserWithDrawAction" onSubmit="return checkIt()" >
					비밀번호 <input type="password" class="form-control" name="user_pw" size="20">
					<hr>
					<input type="submit" name="Submit" value="탈퇴" size="15">
				</form>
			</div>
		</div>
	</div>
</body>
</html>