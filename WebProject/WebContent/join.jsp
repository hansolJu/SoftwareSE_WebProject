<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
</head>
<body>
<center>
<h3>회원가입</h3>
<hr>
<form action=UserController method="post" name=form1>
	<table border=1 cellspacing="1" cellpadding="5">
	<tr>
	<td>이름</td>
	<td><input type="text" name="user_name" value="홍길동"></td>
	</tr>
	<tr>
	
	<td>연령대</td>
	<td><select value="" name="user_age">
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
	<td>핸드폰 번호</td>
	<td><input type="text" name="user_phone" value="010-0000-0000" ></td>
	</tr>
	
	<tr>
	<td>ID</td>
	<td><input type="text" name="user_id"><input type="button" name="check" value="중복확인"></td>
	</tr>
	
	<tr>
	<td>PASSWORD</td>
	<td><input type="password" name="user_pw"></td>
	</tr>
	
	<tr>
	<td>관심 상품(최대 3개 선택)</td>
	<td><input type="radio" name="user_hope_list" value="clothes">의류
	<input type="radio" name="user_hope_list" value="dome_app">가전제품
	<input type="radio" name="user_hope_list" value="book">책</td>
	</tr>
	</table>
	<input type="submit" value="가입하기">
	<input type="reset" value="리셋">
	<input type="button" value="취소">
</form>
</center>
</body>
</html>