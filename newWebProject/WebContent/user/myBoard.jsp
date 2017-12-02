<!-- 
	작성자 : 채지훈
	작성일 : 2017.11.26
	수정자 : 채지훈
	수정일 : 2017.11.26
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="wedeal.bean.UserDBBean, wedeal.bean.UserDataBean, wedeal.bean.BoardDataBean, wedeal.bean.BoardDBBean, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<!-- 메뉴 바 -->
	<jsp:include page="../Menubar.jsp" />

	<!-- 회원 메뉴 -->
	<jsp:include page="userMenubar.jsp" />
	<!-- 게시글 리스트 -->
	<div class='container'>
		<div class='row'>
			<table class="table table-striped" style="border: 1px solid #dddddd; float:left; width:1000px; ">
				<thead>
					<tr>
						<th colspan="5" style="background-color: #eeeeee; text-align: center; height: 30px;">내 게시글</th>
					</tr>
					<tr>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">카테고리</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">제목</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">작성일</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">조회수</th>
						<th style="baokground-color: #eeeeee; text-align: center; height: 30px;">좋아요</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="board" items="${userboardlist}">
							<tr >
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getCate_num() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getBoard_title()}</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getBoard_date() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getBoard_hit() }</td>
								<td style="baokground-color: #eeeeee; text-align: center; height: 30px;">${board.getBoard_like() }</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>