<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="wedeal.bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menutag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	게시글 보는 페이지
	board.jsp에서 누른 게시글에 대한 board_num를 받아옴.
	boardDAO의 getBoard메소드 호출을 통해 해당 게시글에 대한 정보를 boardDTO로 받아옴.
	session id와  boardDTO()의 id와 같을 경우에만'수정','삭제'버튼이 보임.
	최종 수정: 2017/11/05
-->

<!DOCTYPE>
<html>
<head>
	<title>메인 화면</title>
	<script type="text/javascript">
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './LogoutAction',
			})
		}
	</script>
</head>
<body>
	
	<!-- 상단바 -->
	<jsp:include page="Menubar.jsp"/>
	
	<!-- 메뉴 생성 부분 -->
	<menutag:menu/>
	<%
		int board_num = 0;
		if(request.getParameter("board_num") != null){
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}
		if(board_num == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'board.jsp'");
			script.println("</script>");
		}
		BoardDataBean board = BoardDBBean.getinstance().getBoard(board_num);
	%>
	
	
	<!-- 게시글에 관련된 부분 -->
	<div class="container">
		<div class="row">
		<!-- 테이블 색 -->
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2"><%= board.getBoard_title().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= board.getUser_id()%></td>
					</tr>
					<tr>
						<td>조회수</td>
						<td colspan="2"><%= board.getBoard_hit() %></td>
					</tr>
					<tr>
						<td>가격</td>
						<td colspan="2"><%= board.getBoard_price() %></td>
					</tr>
					<tr>
						<td>작성 일자</td>
						<td><%=board.getBoard_date().substring(0,11) + board.getBoard_date().substring(11,13)+"시" + board.getBoard_date().substring(14,16)+"분"%></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px, text-align: left;"><%= board.getBoard_content().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></td>
					</tr>
					<tr>
						<td>물품 사진</td>
						<td colspan="2" align="center">
						<%
							String image = board.getBoard_image();
							String[] images = image.split("/");
							for(int i = 0; i < images.length; i++)
								{
						
						%>
						<img src="<%= board.getBoard_path() %>\<%= images[i] %>" height= 300px width=300px><%="<br>"%>
						<%
							}
						%>
						</td>
					</tr>
				</tbody>
			</table>
			<a href="board.jsp?cate_num=<%=board.getBoard_num()%>" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span></a>
			<c:if test="${user_id ne null}">
					
					<% if(LikeDBBean.getinstance().check_id(request.getParameter("user_id"),board.getBoard_num()) == -1) {%>
					<a href="./LikeAction?board_num=<%= board.getBoard_num() %>&user_id=${user_id}" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span></a>
					<%} else{ %>
					<a href="./LikeAction?board_num=<%= board.getBoard_num() %>&user_id=${user_id}" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span></a>
					<%} %>
					<a href="./DeclarationAction?board_num=<%= board.getBoard_num() %>&user_id=${user_id}" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span></a>
			</c:if>
			<%
				if(request.getParameter("user_id").equals(board.getUser_id())) {%>
				<a href="update.jsp?board_num=<%= board.getBoard_num() %>&cate_num=<%=board.getCate_num() %>" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="./BoardDeleteAction?cate_num=<%=board.getCate_num()%>&board_num=<%=board.getBoard_num() %>" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
			<%} %>
		</div>
	</div>
	
	<!-- 댓글 기능 -->
	<div class="container">
	<div class="row">
		<form method="post" action="./CommentWriteAction?board_num=<%=board.getBoard_num()%>&cate_num=<%=board.getCate_num()%>&user_id=<%=board.getUser_id()%>">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="4" style="background-color: #eeeeee; text-align: center;">댓글</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${user_id ne null}">
					<tr>
						<td colspan="4"><textarea class="form-control" placeholder="댓글  내용" name="comment_content" maxlength="200" style="height: 100px;"></textarea></td>
					</tr>
					<tr>
						<td colspan="4">
						<input type="submit" class="btn btn-primary" value="등록">
						</td>
					</tr>
					</c:if>
					<c:if test="${user_id eq null}">
					<tr>
						<td colspan="4">댓글을 남기려면 로그인을 해주세요!</td>
					</tr>
					</c:if>
					<%
						ArrayList<CommentDataBean> list = CommentDBBean.getinstance().getList(board.getBoard_num());
						for(int i = 0; i < list.size(); i++){
					%>
					<tr>
						<td><%=list.get(i).getUser_id() %></td>
						<td><%=list.get(i).getComment_content() %></td>
						<td><%=list.get(i).getComment_date().substring(0,11) + list.get(i).getComment_date().substring(11,13)+"시" + list.get(i).getComment_date().substring(14,16)+"분"%></td>
					<%
						if(request.getParameter("user_id").equals(board.getUser_id())){
							int comment_num = list.get(i).getComment_num();
					%>
						<td>
						<input type=button onclick="updateComment();" class="btn btn-primary" value="수정">
						<a onclick="return confirm('정말로 삭제하시겠습니까?')"  href="./CommentDeleteAction?comment_num=<%=comment_num %>&board_num=<%=board_num %>" class="btn btn-primary">삭제</a>
						</td>
					</tr>
					<%
						}	}
					%>
				
				</tbody>
			</table>
			</form>
		</div>
	</div>
</body>
</html>