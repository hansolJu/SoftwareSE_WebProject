<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
				url: './UserLogoutServlet',
			})
		}
	</script>
</head>

<body>
	<jsp:include page="../Menubar.jsp" />
	<!-- 회원 메뉴 -->
	<div id="menu" style="display:inline-block; border-right:1px solid; float:left; height:400px; width:15%; padding:10px;">
		<ul style="list-style:none;">
			<li><a href="#">내 게시글</a></li>
			<li><a href="#">내 댓글</a></li>
			<li><a href="#">내 찜</a></li>
		</ul>
	</div>
	
	<!-- 회원 연락처 수정 -->
	<div>
		<div style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>연락처 수정</h3>
			<br>
			<div>
				<form name="modify" method="POST" action="./../UserPhoneModifyAction">
				<div class="form-group">
					<h5>연락처</h5>
					<input type="text" class="form-control" id="user_phone" placeholder="연락처" name="user_phone" maxlength="20">
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary form-control" value="확인">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>