<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.UserDBBean" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>비밀번호 확인</title>
	<style type="text/css">
		div{
			text-align:center;
		}
	</style>
</head>
<%
   request.setCharacterEncoding("UTF-8");
   String user_id = request.getParameter("user_id");
   String Phone = request.getParameter("Phone");
   String phone1 = request.getParameter("phone1");
   String phone2 = request.getParameter("phone2");
   String user_phone = Phone+"-"+phone1+"-"+phone2;
   UserDBBean searchService = UserDBBean.getinstance();
   String user_pw = searchService.searchPw(user_id, user_phone);
%>
<body>	
	<table width="700px" height="500px" align="center"  border="0" style="color:black; background-color: #e1f2f2; font-size:20px; ">
	<tr>
		<td>
		<table width="500px" height="100px" align="center" border=0; style="background-color:white;" >
		<tr>
			<th>비밀번호 확인</th>
		</tr>
		</table>
		     
    	<tr>
     		<td>
      		<table width="450px" align="center" border="0" style="font-size:19px">     						
       			<tr>
        		<td>
		        <%if(user_id!= null){ %>
				<tr>
				    <td><div><%=user_id %>님의 비밀번호는</div></td>
				</tr>
				<tr>
					<td><h1><div><%=user_pw %></div></h1><div>입니다.</div></td>
				</tr>
		        </td>
				</tr>
				<tr>
					<td>
					<table width="700px" align="center"  border="0" style="color:black;; margin-top:5%; font-size:20px; ">
					<tr>
        				<td>
        				<div>
        					<input type="button" value="로그인하기" class="btn btn-primary" onclick="location.href='login.jsp'">
        				</div>
        				</td>
         			</tr>
      				</table>
      				<%} else{%>
      				<tr>
						<td><%=user_id %>님!</td>
					</tr>
					<tr>
						<td><h1>가입정보가 없습니다.</h1></td>
					</tr>
					</table>
					</td> 
				</tr>       
				<tr> 
					<td>
					<table width="150px"  align="center" border="0" style="margin-top:1%">
					<tr>
						<td><input type="button" value="회원가입하기" class="btn btn-primary" onclick="location.href='join.jsp'"></td>
						<td><input type="button" value="처음으로" class="btn btn-primary" onclick="location.href='login.jsp'"></td>
					</tr>
					</table>   
					<%} %>
          			</table>
     			</td>
			</tr> 
		</table>
</body>
</html>