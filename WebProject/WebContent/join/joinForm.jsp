<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="join.js"></script>

<div id="loginForm" class="box">
 <table border=1 cellspacing="1" cellpadding="5">
	<tr>
	<td><label for="user_name">Name</label></td>
	<td><input id="user_name" name="user_name" type="text" placeholder="hong-gil-dong"></td>
	</tr>
	
	<tr>
	<td><label for="user_age">Age</label></td>
	<td><input id="user_age" name="user_age" type="text" placeholder="ex)23"></td>
	</tr>
	
	<tr>
	<td><label for="user_phone">Phone Number</label></td>
	<td><input id="user_phone" name="user_phone" type="text" placeholder="010-xxxx-xxxx"></td>
	</tr>
	
	<tr>
	<td><label for="user_id">ID</label></td>
	<td><input id="user_id" name="user_id" type="text" placeholder="aaaa">
		<button id="checkId">Check ID</button></td>
	</tr>
	
	<tr>
	<td><label for="user_passwd">Password</label></td>
	<td><input id="user_passwd" name="user_passwd" type="password" placeholder="6~16자 숫자/문자"></td>
	</tr>
	
	<tr align="center">
	<td colspan="2">
	<class="label2"><button id="process">가입하기</button>
  	<button id="cancle">취소</button></td>
  	</tr>
   </table>
</div>