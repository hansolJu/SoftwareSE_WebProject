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
			url : './../UserLogoutServlet',
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
	<jsp:include page="../Menubar.jsp" />
	<!-- 회원 메뉴 -->
	<div id="menu" style="display: inline-block; border-right: 1px solid; float: left; height: 400px; width: 15%; padding: 10px;">
		<ul style="list-style: none;">
			<li><a href="#">내 게시글</a></li>
			<li><a href="#">내 댓글</a></li>
		</ul>
	</div>

	<!-- 회원 탈퇴 -->
	<div id="confirm" style="display: inline-block; border: 1px; height: 200px; width: 500px; padding-left: 50px; padding-right: 50px;">
		<h3>회원 탈퇴</h3>
		<hr>
		<div>
			<form name="withdraw" method="post" action="./../UserWithDrawAction" onSubmit="return checkIt()" >
				비밀번호 <input type="password" name="user_pw" size="20">
				<hr>
				<input type="submit" name="Submit" value="탈퇴" size="15">
			</form>
		</div>
	</div>
</body>
</html>