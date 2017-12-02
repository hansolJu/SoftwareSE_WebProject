<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.UserDBBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	비밀번호 찾기를 했을 때
	비밀번호를 보여주는 페이지
 -->
 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>비밀번호 확인</title>
	</head>
	<%
	   request.setCharacterEncoding("euc-kr");
	   String user_id = request.getParameter("user_id");
	   String user_phone = request.getParameter("user_id");
	   UserDBBean userdbbean = UserDBBean.getinstance();
	   String user_pw = userdbbean.searchPw(user_id, user_phone);
	%>
<body>	
		<jsp:include page="Menubar.jsp"/>
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<h4 style="text-align: center;">비밀번호 확인</h4>
		        	<%if(user_id!= null){ %>	
		        	<center>	
		        	<br/><br/>				
						<div class="form-group">
							<%=user_id %>님의 비밀번호는
						</div>
						<div class="form-group">
							<h1><%=user_pw %></h1>				
						</div>
						<div class="form-group">						
							입니다.
						</div><br/><br/><br/>				
       					<input type="button" value="로그인하기" class="btn btn-primary" onclick="location.href='login.jsp'">
      				</center>
      				<%} else{%>
						<div class="form-group">
							<br/><br/><br/>
							<center><h1>가입정보가 없습니다.</h1></center>				
						</div><br/><br/><br/><br/>
						<center>				
       					<input type="button" value="회원가입 하기" class="btn btn-primary" onclick="location.href='join.jsp'">
        				<input type="button" value="처음으로" class="btn btn-primary" onclick="location.href='SearchPW.jsp'">     							
						</center>
					<%}%>
			</div>
			<div class="col-lg-4"></div>
		</div>
		</div>							
</body>
</html>