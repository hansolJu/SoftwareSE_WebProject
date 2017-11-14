<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
	회원가입 페이지
	회원가입 각각 text들이 모두 채워져있지 않은 상태로 회원가입 버튼을 누르면 UserRegisterServlet에서 session으로 경고 메세지를 보냄
	아이디 중복체크 버튼을 누르면 UserRegisterCheckServlet에서 판단하여 결과를 보내고, 결과 값이 1이면 사용 가능, 1이 아니면 사용 불가능
	비밀번호 일치 확인은 비밀번호를 입력하는 순간부터 자동으로 passwordCheckFunction이 호출됨. 두 개의 text값이 불일치 하는 경우에만 메세지 출력.
	최종 수정: 2017/11/05
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>회원가입 화면</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script type="text/javascript">
	//------------------------아이디 중복확인하는 부분------------------------
		function registerCheckFunction(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './UserRegisterCheckServlet',
				data: {user_id: user_id},
				success: function(result){
				if(result == 1){
						$('#checkMessage').html('사용할 수 있는 아이디입니다.');
						$('#checkType').attr('class', 'modal-content panel-success');
				}
				else{
					$('#checkMessage').html('사용할 수 없는 아이디입니다.');
					$('#checkType').attr('class', 'modal-content panel-warning');
				}
				$('#checkModal').modal("show");
				}
			})
		}
		//------------------------비밀번호 일치 확인------------------------
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
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">중고 장터</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">메인</a></li>
				<li><a href="board.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					 aria-expanded="false">접속하기<span class="caret"></span></a>
					 <ul class="dropdown-menu">
					 	<li><a href="login.jsp">로그인</a></li>
					 	<li class="active"><a href="join.jsp">회원가입</a></li>
					 </ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="./userRegister">
					<h3 style="text-align: center;">회원가입</h3>
					
					<div class="form-group">
						<input type="text" class="form-control" id="user_name" name="user_name" placeholder="이름">
					</div>
					
					<div class="form-group">
						<select class="form-control" id="user_age" name="user_age">
						<option value="">연령대를 선택해주세요.</option>
						<option value="10">10대</option>
						<option value="20">20대</option>
						<option value="30">30대</option>
						<option value="40">40대</option>
						<option value="50">50대</option>
						<option value="60">60대 이상</option>
						</select>
					</div>
					
					<div class="form-gropu">
						<input type="text" class="form-control" id="user_phone" placeholder="핸드폰 번호를 입력해 주세요." name="user_phone" maxlength="20">
					</div>
					
					<div class="form-group">
						<input type="text" class="form-control" id="user_id" placeholder="아이디" name="user_id" maxlength="10">
						<button class="btn btn-primary" onclick="registerCheckFunction();" type="button">중복체크</button>
					</div>
					
					<div class="form-group">
						<input type="password" class="form-control" id="user_pw" onkeyup="passwordCheckFunction();" placeholder="비밀번호" name="user_pw" maxlength="20">
					</div>
					
					<div class="form-group">
						<input type="password" class="form-control" id="check_passwd" onkeyup="passwordCheckFunction();" placeholder="비밀번호 확인" name="check_passwd" maxlength="20">
					</div>
					
					<div class="form-group" style="text-align: center; margin: 0 auto;">
						관심상품
						<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-primary active">
									<input type="radio" name="user_hope" autocomplete="off" value="의류" selected>의류
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="user_hope" autocomplete="off" value="가전">가전
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="user_hope" autocomplete="off" value="도서">도서
								</label>
								</div>
					</div>
					<div class="form-group">
							<h5 align="center" style="color: red;" id="passwordCheckMessage"></h5>
							<input type="submit" class="btn btn-primary form-control" value="회원가입">
					</div>
				</form>
		</div>
		<div class="col-lg-4"></div>
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