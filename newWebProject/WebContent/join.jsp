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
	<title>회원가입 화면</title>
	<script type="text/javascript">
	//------------------------아이디 중복확인하는 부분------------------------
		function registerCheckFunction(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './ConfirmIdAction',
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
	<!-- 상단바 -->
	<jsp:include page="Menubar.jsp"/>
	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="./RegisterAction">
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
</body>
</html>