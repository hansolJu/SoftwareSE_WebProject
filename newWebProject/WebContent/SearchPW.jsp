<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>비밀번호 찾기</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<style type="text/css">
		div{
			text-align:center;
			}
	</style>
</head>
<body>
	<form name="searchPw" method="post" action="viaPW.jsp">
	
			<table width="700px" height="500px" align="center"  border="0" style="color:black; background-color: #e1f2f2; font-size:20px; ">
		           <tr>
		           <td>
		                <table width="500px" height="100px" align="center" border=0; style="background-color:#F6F6F6;" >
		              		<tr>
		                      	<th>비밀번호 찾기</th>
		                    </tr>
		                </table>
		          		<tr>
		          			<td>
		          				&nbsp;&nbsp;아이디와 핸드폰 번호를 입력해주세요.
		          			</td>
       					</tr>
    					<tr>
     					<td>
      						<table width="380px" align="center" border="0" style="font-size:19px">
       						<tr>
        						<td>아이디</td>
       							<td><input type="text" name="user_id"></td>
       						</tr>
       						<tr>
        						<td>핸드폰 번호</td>
								<td><select name="Phone">
					        	<option value ="010" selected="selected">010</option>
					        	<option value ="011">011</option>
					        	<option value ="016">016</option> 
					        	<option value ="017">017</option>
					        	</select> -
					        	<input type="text" name="phone1" style="width:70px"> -
					        	<input type="text" name="phone2" style="width:70px"></td>
					        </tr>
					        <table width="700px" align="center"  border="0" style="color:black; background-color: #e1f2f2; margin-top:5%; font-size:20px; ">
					        <tr>
        					<td>
        						<div>
        							<input type="submit" name="search" value="찾기" class="btn btn-primary">
         							<input type="button" name="cancel" value="취소" class="btn btn-primary" onclick="location.href='login.jsp'"></td>
         						</div>
         					</tr>
      						</table>
      						</table>
     					</td>
     					</tr>
     				</td>
    			</tr>
			</table>
	</form>
</body>
</html>
