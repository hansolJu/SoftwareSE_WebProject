<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="board.boardDAO" %>
<%@ page import="board.boardDTO" %>
<%@ page import="java.util.ArrayList" %>
<!-- 
	게시판 페이지
	게시판 페이지는 회원 session없이도 접속 가능함.
	회원 session이 없다면 '글쓰기'버튼 비활성.
	글 목록은 번호,제목,작성자,작성일을 보여줌.
	boardDAO의 getList메소드를 사용하여 글 목록을 받아오고, pageNumber를 통해 10개씩 글을 잘라 목록을 만듬.
	글이 10개 미만일 경우 아무 버튼 없음. 10개 이상일 경우 '다음','이전'버튼 활성.
	게시글의 제목을 클릭할 경우 write.jsp로 넘어감.
	최종 수정: 2017/11/05
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<title>게시판 화면</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript">
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './UserLogoutServlet',
			})
		}
	</script>
<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
	}
</style>
</head>
<body>
	<%
		String session_id=null;
	
		if(session.getAttribute("user_id") !=null){
			session_id=(String)session.getAttribute("user_id");
		}
		int pageNumber = 1; //기본페이지 초기값
		if(request.getParameter("pageNumber")!=null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">중고 장터</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">메인</a></li>
				<li class="active"><a href="board.jsp">게시판</a></li>
			</ul>
			<%
				if(session_id == null){
				//-------------------------------------------------------로그인이 되어있지 않은 경우
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					 aria-expanded="false">접속하기<span class="caret"></span></a>
					 <ul class="dropdown-menu">
					 	<li><a href="login.jsp">로그인</a></li>
					 	<li><a href="join.jsp">회원가입</a></li>
					 </ul>
				</li>
			</ul>
			<% 
				} else{
				//-------------------------------------------------------로그인이 되어있는 경우
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					 aria-expanded="false">마이 페이지<span class="caret"></span></a>
					 <ul class="dropdown-menu">
					 	<li><a href="" onclick="logout();">로그아웃</a></li>
					 </ul>
				</li>
			</ul>
			<%
				} 
			%>
		</div>
	</nav>
	<div class="container">
		<div class="row">
		<!-------------------------------- 글 목록을 테이블로 생성하는 부분 -------------------------------->
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
						boardDAO boarddao = new boardDAO();
						ArrayList<boardDTO> list = boarddao.getList(pageNumber);
						for(int i = 0; i < list.size(); i++){
							
					%>
					<tr>
						<td><%=list.get(i).getBoard_num() %></td>
						<td><a href="view.jsp?board_num=<%=list.get(i).getBoard_num() %>"><%=list.get(i).getBoard_title().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></a></td>
						<td><%=list.get(i).getUser_id() %></td>
						<td><%=list.get(i).getBoard_date().substring(0,11) + list.get(i).getBoard_date().substring(11,13)+"시" + list.get(i).getBoard_date().substring(14,16)+"분"%></td>
					</tr>
					<% 
						} 
					%>
					
				</tbody>
			</table>
			<% 
				//-------------------------------------- 페이지 넘기는 버튼 생성하는 부분 --------------------------------------
				if(pageNumber != 1) {
			%>
				<a href="board.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn-arraw-left">이전</a>
			<% 
				} if(boarddao.nextPage(pageNumber + 1)){
			%>
				<a href="board.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn-arraw-left">다음</a>
			<%
				}
				if(session_id != null){
			%>
			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>