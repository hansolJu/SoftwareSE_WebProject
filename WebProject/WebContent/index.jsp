<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>메인 화면</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<form method="post" action="./userRegister">
			<!-- design part -->
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원가입</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="user_name" name="user_name" placeholder="ex)홍길동"></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><select class="form-control" id="user_age" name="user_age">
						<option value="">연령대를 선택해주세요.</option>
						<option value="10">10대</option>
						<option value="20">20대</option>
						<option value="30">30대</option>
						<option value="40">40대</option>
						<option value="50">50대</option>
						<option value="60">60대 이상</option>
						</select></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>핸드폰 번호</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="user_phone" name="user_phone" placeholder="ex)010-xxxx-xxxx"></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" type="text" id="user_id" name="user_id"></td>
						<td style="width: 110px;"><button class="btn btn-primary" onclick="registerCheckFunction();" type="button">중복체크</button></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input class="form-control" type="password" id="user_passwd" name="user_passwd"></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input class="form-control" type="password" id="check_passwd" name="check_passwd"></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>관심 상품</h5></td>
						<td colspan="2">
							<div class="form-group" style="text-align: center; margin: 0 auto;">
								<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-primary active">
									<input type="radio" name="user_hope" autocomplete="off" value="의류">의류
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="user_hope" autocomplete="off" value="가전">가전
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="user_hope" autocomplete="off" value="도서">도서
								</label>
								</div>
							</div>
					</tr>
					<tr>
						<td style="text-align: center" colspan="3"><input class="btn btn-primary pull-center" type="submit" value="회원가입"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>