<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	비밀번호 찾기 페이지
 -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>비밀번호 찾기</title>
</head>
<body>
		<jsp:include page="Menubar.jsp"/>
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<form method="post" action="showPW.jsp">
						<h3 style="text-align: center;">비밀번호 찾기</h3>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="아이디" name="user_id" maxlength="20">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="핸드폰 번호" name="user_phone" maxlength="20">
						</div>
						<input type="submit" class="btn btn-primary form-control" value="찾기"><br/><br/>
						<center><a href="login.jsp">로그인 하기</a> &emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;
						<a href="join.jsp">회원가입</a>
						</center>
					</form>
			</div>
			<div class="col-lg-4"></div>
		</div>
		</div>			
</body>
</html>
