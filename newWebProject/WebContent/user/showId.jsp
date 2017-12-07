<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wedeal.bean.UserDBBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	아이디 찾기를 했을 때
	아이디를 보여주는 페이지
 -->
 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>아이디 확인</title>
	</head>
	<%
	   request.setCharacterEncoding("euc-kr");
	   String user_name = request.getParameter("user_name");
	   String user_phone = request.getParameter("user_phone");
	   UserDBBean userdbbean = UserDBBean.getinstance();
	   String user_id = userdbbean.searchId(user_name, user_phone);
	%>
	<body>
		<jsp:include page="Menubar.jsp"/>
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<h4 style="text-align: center;">아이디 확인</h4>
		        	<%if(user_id!= null){ %>
		        	<div>
		        	<br/><br/>			
						<div class="form-group">
							<%=user_name %>님의 아이디는<br/>
						</div>
						<div class="form-group">
							<h1><%=user_id %></h1>	<br/>			
						</div>
						<div class="form-group">						
							입니다.
						</div>	<br/><br/>						
       					<input type="button" value="로그인하기" class="btn btn-primary" onclick="location.href='login.jsp'">
        				<input type="button" value="비밀번호 찾기" class="btn btn-primary" onclick="location.href='SearchPW.jsp'">
      				</div>	
      				
      				<%} else{%>
						<div class="form-group">
							<br/><br/><br/>
							<center>가입정보가 없습니다.</center>				
						</div><br/><br/><br/><br/>
						<div>				
       					<input type="button" value="회원가입 하기" class="btn btn-primary" onclick="location.href='join.jsp'">
        				<input type="button" value="처음으로" class="btn btn-primary" onclick="location.href='SearchId.jsp'">     							
						</div>
					<%}%>
			</div>
			<div class="col-lg-4"></div>
		</div>
		</div>							
</body>
</html>