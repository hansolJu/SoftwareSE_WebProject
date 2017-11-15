<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="/WebProject/user/register.js"></script>

	<script type="text/javascript">
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
	
	<div id="regForm" class="box">
		<ul>
				<li><h3 style="text-align: center;">회원가입</h3>
				
				<li><label for="user_name">이름</label>
					<input type="text" id="user_name" name="user_name" placeholder="이름">
					
				<li><label for="user_age">나이</label>
					<select id="user_age" name="user_age">
					<option value="">연령대를 선택해주세요.</option>
					<option value="10">10대</option>
					<option value="20">20대</option>
					<option value="30">30대</option>
					<option value="40">40대</option>
					<option value="50">50대</option>
					<option value="60">60대 이상</option>
					</select>
					
				<li><label for="user_phone">핸드폰 번호</label>
					<input type="text" id="user_phone" placeholder="핸드폰 번호를 입력해 주세요." name="user_phone" maxlength="20">
					
					
				<li><label for="user_id">아이디</label>
					<input type="text" id="user_id" placeholder="아이디" name="user_id" maxlength="10" autofocus>
					<button id="checkId" type="button">중복체크</button>
					
				<li><label for="user_pw">비밀번호</label>
					<input type="password" id="user_pw" onkeyup="passwordCheckFunction();" placeholder="비밀번호" name="user_pw" maxlength="20">
					
					
				<li><label for="repass">비밀번호 확인</label>
					<input type="password" id="check_passwd" onkeyup="passwordCheckFunction();" placeholder="비밀번호 확인" name="check_passwd" maxlength="20">
					
				<li><label for="user_hope">관심상품</label>
					<input type="radio" name="user_hope" autocomplete="off" value="의류" selected>의류
					<input type="radio" name="user_hope" autocomplete="off" value="가전">가전
					<input type="radio" name="user_hope" autocomplete="off" value="도서">도서
					
				<li><h5 style="color: red;" id="passwordCheckMessage"></h5>
				<li class="label2"><button id="process">가입</button>
					<button id="cancle">취소</button>
			</ul>
	</div>
	
