<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ page import="wedeal.bean.*" %> 
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>회원 비밀번호 수정</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script type="text/javascript">
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './../UserLogoutServlet',
			})
		}
		function passwordCheckFunction(){
			var user_pw = $('#user_pw').val();
			var check_passwd = $('#check_passwd').val();
			if(user_pw != check_passwd){
				$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
			}else{
				$('#passwordCheckMessage').html('');
			}
		}
	</script>
</head>

<body>
	<jsp:include page="../Menubar.jsp"></jsp:include>
	
	<!-- 회원 메뉴 -->
	<div id="menu" style="display:inline-block; border-right:1px solid; float:left; height:400px; width:15%; padding:10px;">
		<ul style="list-style:none;">
			<li><a href="#">내 게시글</a></li>
			<li><a href="#">내 댓글</a></li>
			<li><a href="#">내 찜</a></li>
		</ul>
	</div>
	<div>
		<div style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>비밀번호 수정</h3>
			<br>
				<form name="modify" method="post"  action="./../UserPassWdModifyAction">
				<div class="form-group">
					<h5>비밀번호</h5>
					<input type="password" class="form-control" id="user_pw" onkeyup="passwordCheckFunction();" placeholder="비밀번호" name="user_pw" maxlength="20">
				</div>
				<div class="form-group">
					<h5>비밀번호 확인</h5>
					<input type="password" class="form-control" id="check_passwd" onkeyup="passwordCheckFunction();" placeholder="비밀번호 확인" name="check_passwd" maxlength="20">
				</div>
				<div class="form-group">
						<h5 align="center" style="color: red;" id="passwordCheckMessage"></h5>
						<input type="submit" class="btn btn-primary form-control" value="확인">
				</div>
				</form>
			</div>
		</div>
</body>
</html>