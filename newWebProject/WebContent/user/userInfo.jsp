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
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>회원 정보 조회</title>
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
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">WeDEAL</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">메인</a></li>
				<li><a href="board.jsp">게시판</a></li>
			</ul>
			<%
				if(session_id == null){
				//-------------------------------------------------------로그인이 되어있지 않은 경우
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
					 <ul class="dropdown-menu">
					 	<li><a href="login.jsp">로그인</a></li>
					 	<li><a href="join.jsp">회원가입</a></li>
					 </ul>
				</li>
			</ul>
			<% 
				} else{
				//-------------------------------------------------------로그인이 되어있는 경우
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">설정<span class="caret"></span></a>
					 <ul class="dropdown-menu">
					 	<li><a href="" onclick="logout();">로그아웃</a></li>
					 	<li><a href="userinfo.jsp" >내 정보</a></li>
					 </ul>
				</li>
			</ul>
			<%
				} 
			%>
		</div>
	</nav>
	<!-- 회원 메뉴 -->
	<div id="menu" style="display:inline-block; border-right:1px solid; float:left; height:400px; width:15%; padding:10px;">
		<ul style="list-style:none;">
			<li><a href="#">내 게시글</a></li>
			<li><a href="#">내 댓글</a></li>
			<li><a href="#">내 찜</a></li>
		</ul>
	</div>
	
	<!-- 회원 정보 조회 및 수정-->
	<div class="userinfo" style="float:left;">
		<div id="profile" style="display:inline-block; border:1px; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>WeDEAL 프로필</h3>
			<hr>
			<div>
				<table>
					<tr>
						<td>이름 : </td>
						<td><%=userinfo.getUser_name() %></td>
					</tr>
					<tr>
						<td>연령대 : </td>
						<td><%=userinfo.getUser_age() %></td>
					</tr>
				</table>
				<br>
			</div>
		</div>
		
		<div id="contact" style="display:inline-block; border:1px ; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>연락처</h3>
			<hr>
			<div>
				<table>
					<tr>
						<td>휴대전화 : </td>
						<td><%=userinfo.getUser_phone() %></td>
					</tr>
				</table>
				<br>
				<a href="modifyPhone.jsp">수정</a>
			</div>
		</div>
		<br>
		<div id="secret" style="display:inline-block; border:1px ; float:bottom; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>비밀번호</h3>
			<hr>
			<div>
				<table>
					<tr>
						<td><a href="modifyPasswd.jsp">수정</a></td>
					</tr>	
				</table>
			</div>
		</div>
		
		<div id="withdraw" style="display:inline-block; border:1px ; height:200px; width:500px; padding-left:50px; padding-right:50px;">
			<h3>회원 탈퇴</h3>
			<hr>
			<div>
				<table>
					<tr>
						<td><a href="deleteUser.jsp">탈퇴</a>
					<tr>
				</table>
			</div>
		</div>
	</div>
	<%
	//------------------------보낸 경고 메세지 읽는 부분 -------------------------------------------
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