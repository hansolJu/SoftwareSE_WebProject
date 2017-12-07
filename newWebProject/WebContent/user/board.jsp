<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.*" %>
<%@ page import="wedeal.bean.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menutag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="MetaData/css/demo.css" />
     <link rel="stylesheet" type="text/css" href="MetaData/css/style.css" />
     <noscript><link rel="stylesheet" type="text/css" href="MetaData/css/noJS.css"/></noscript>
	<title>메인 화면</title>
	<script type="text/javascript">
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: '/newWebProject//LogoutAction',
			})
		}
	</script>
</head>
<body>
	<!-- 상단바 -->
	<jsp:include page="Menubar.jsp"/>
	
	<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
	};
	</style>
	
	<!-- 메뉴 생성 부분 -->
	<menutag:menu/>
	
	<!-- 글 생성 부분 -->
	<div class='container'>
		<div class='row'>
			<table class='table table-striped' style='border: 1px solid #dddddd' width='1000' height='600'>
				<thead>
					<tr>
						<th colspan='5' style='background-color: #eeeeee; text-align: center;' height='30'>게시판</th>
					</tr>
				</thead>
			 <tbody>
			 	<tr>
	<%
		int pageNum = 1; if(request.getParameter("pageNumber") != null) pageNum = Integer.parseInt(request.getParameter("pageNumber")); //페이지번호 설정 
		int cate = 0; if(request.getParameter("cate_num") != null) cate = Integer.parseInt(request.getParameter("cate_num")); //카테고리 번호 설정
		int length = 0;
		BoardDBBean board = BoardDBBean.getinstance();
		CateDataBean cate_data = CateDBBean.getinstance().getcate(cate);
		ArrayList<CateDataBean> cate_list = null;
		ArrayList<BoardDataBean> list = null;
		
		//전체보기
		if(cate == 0){
			list = board.getList(0, pageNum);
			length = board.allCount(cate);
		}
		
		//카테고리 선택
		else{
			if(cate_data.getCate_parent() > 0){
				list = board.getList(cate_data.getCate_num(), pageNum);
				length = board.allCount(cate_data.getCate_num());
			}
			else{
				list = board.getList(cate_data.getCate_num(), pageNum);
				length = board.allCount(cate_data.getCate_num());
			}
		}
		
		if(list.size() == 0){
		%>
			<td align="center">등록된 게시글이 없습니다.</td>
		<%	
		}
		else{
		if(length%8 == 0)
			length = length/8;
		else if((length%8) != 0)
			length = length/8 +1;

		for(int i = 0; i < list.size(); i++){
			String image = list.get(i).getBoard_image();
			String[] images = image.split("/");
	%>	
			<td align="center"><a href="view.jsp?board_num=<%=list.get(i).getBoard_num() %>&user_id=${user_id}">
			<%=list.get(i).getBoard_title().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>")%></a><%="<br>"%>
			<label>작성자:<%=list.get(i).getUser_id() %></label><%="<br>"%>
			<a href="view.jsp?board_num=<%=list.get(i).getBoard_num()%>&user_id=${user_id}"><img src="/test/<%= images[0] %>" height= 200px width=200px></a><%="<br>"%>
			<%=list.get(i).getBoard_date().substring(0,11) + list.get(i).getBoard_date().substring(11,13)+"시" + list.get(i).getBoard_date().substring(14,16)+"분"%>
			</td>
	<%
		if((i+1)%4 == 0 && i > 0){
	%>
		</tr>
		<tr>
	<%
			}
		}
	}
	%>
		</tr></tbody></table>
	<nav>
		 <ul class="pagination">
		 	 <li><a href="board.jsp?PageNumber=<%=pageNum-1%>" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
	<%
		for(int j = 0; j < length; j++){
	%>
		<li><a href="board.jsp?pageNumber=<%=(j+1)%>"><%= (j+1) %></a></li>
	<%
		}
	%>
			<li><a href="board.jsp?pageNumber=<%=pageNum+1%>" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
			</ul>
	</nav>
	<form class="navbar-form navbar-center" role="search" name="CateSearch" method=post action="/newWebProject/CategorySearchAction">
    	<div class="form-group">
          	<input type="text" class="form-control" placeholder="Search" name="keyword">
          	<input type="hidden" name = "searchCategory" value = "<%=cate%>"> 
        </div>
        	<button type="submit" class="btn btn-default">Search</button>
    </form>
	<c:if test="${user_id ne null}">
		<a href="write.jsp"  class='btn btn-primary pull-right'>글쓰기</a>
	</c:if>
		</div>
		</div>
	<script type="text/javascript">
            $(function () {
                $(' #da-thumbs > li ').hoverdir();
            });
       </script>
</body>
</html>