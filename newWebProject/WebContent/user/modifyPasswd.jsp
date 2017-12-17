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
				url: '../UserLogoutServlet',
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
	<!-- 회원 메뉴 -->
	<jsp:include page="userMenubar.jsp"/>
	<div>
		<div style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>비밀번호 수정</h3>
			<br>
			<div class="form-group">
				<form name="modify" method="post"  action="/newWebProject/UserPassWdModifyAction">
				비밀번호 : <input type="password" class="form-control" onkeyup="passwordCheckFunction();"  name="user_pw" size="20" />
				<br>
				비밀번호 확인 : <input type="password" class="form-control" onkeyup="passwordCheckFunction();"  name="check_passwd" size="20" />
				<hr>
				<h5 align="center" style="color: red;" id="passwordCheckMessage"></h5>
				<hr>
				<input type="submit" name="Submit" value="수정" >
				</form>
			</div>
		</div>
	</div>
	<%
	//------------------------UserRegisterServlet에서 보낸 경고 메세지 읽는 부분 -------------------------------------------
		String messageContent = null;
		if(session.getAttribute("messageContent") !=null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if(session.getAttribute("messageType") !=null) {
			messageType = (String) session.getAttribute("messageType");
		}

		if(messageContent != null){
	%>
	
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류 메시지")) out.print("panel-warning"); else out.print("panel-success");%>">
					<div class="modal-header-panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%= messageType %>
						</h4>
					</div>
					<div class="modal-body">
						<%= messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#messageModal').modal("show");
	</script>
	<%
	//------------------------registerCheckFunction()에서 보낸 경고 메세지 읽는 부분 -------------------------------------------
		session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
		}
	%>
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							확인 메시지
						</h4>
					</div>
					<div class="modal-body" id="checkMessage">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>