<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.*" %>
<%@ page import="wedeal.bean.*" %>

<!-- 
	글쓰기 페이지
	글쓰기 양식을 모두 채워야만 글 등록 가능.
	채우지 않을 경우 BoardWriterServlet에서 session을 통해 경고 메세지를 보냄.
	글쓰기 완료되면 board.jsp로 이동.
	최종 수정: 2017/11/05
-->

<!DOCTYPE html>
<html>
<head>
	<title>Write 화면</title>
	<script type="text/javascript">
		function logout(){
			var user_id = $('#user_id').val();
			$.ajax({
				type: 'POST',
				url: './UserLogoutServlet',
			})
		}
	</script>
	
</head>
<body>
	<!-- 상단바 -->
	<jsp:include page="Menubar.jsp"/>
	<div class="container">
		<div class="row">
		<!-- 테이블 색 -->
			<form name=writetable method="post" action="./BoardWriteAction" enctype="multipart/form-data">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" placeholder="글 제목" name="board_title" maxlength="50"></td>
					</tr>
					<tr>
						<td>
						<select class="form-control" id="out_cate_num" name="out_cate_num">
						<option value="">대 카테고리를 선택해 주세요</option>
						<% 
							CateDBBean cate = CateDBBean.getinstance();
							ArrayList<CateDataBean> list = cate.getList(); //대 카테고리
							for(int i = 0; i < list.size(); i++){
						%>
							<option value=""><%=list.get(i).getCate_name() %></option>
						<%
							}
						%>
						</select>
						<select class="form-control" id="cate_num" name="cate_num">
						<option>소 카테고리를 선택해 주세요</option>
						<% 
							ArrayList<CateDataBean> in_list = cate.in_getList();
							for(int i = 0; i < in_list.size(); i++){
						%>
							<option value="<%=in_list.get(i).getCate_num() %>" name="cate_num"><%=in_list.get(i).getCate_name() %></option>
						<%
							}
						%>
						</select>
						</td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" placeholder="가격" name="board_price"></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글  내용" name="board_content" maxlength="2048" style="height: 350px;"></textarea></td>
					</tr>
					<tr>
						<td><label>최대 업로드 파일 수 : 5개</label></td>
					</tr>
					<tr>
						<td>
						파일: <input type="file" class="form-control" name="file1"><br>
						파일: <input type="file" class="form-control" name="file2"><br>
						파일: <input type="file" class="form-control" name="file3"><br>
						파일: <input type="file" class="form-control" name="file4"><br>
						파일: <input type="file" class="form-control" name="file5"><br>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	</div>
</body>
</body>
</html>