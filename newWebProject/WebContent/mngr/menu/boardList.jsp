<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Show All Users</title>
</head>
<body>
	<table border=1>
		<thead>
			<tr>
				<th>게시판 번호</th>
				<th>상위 게시판 이름</th>
				<th>게시판 이름</th>
				<th>관리자 이름</th>
				<th>Image</th>
				<th colspan=3>설정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${borads}" var="board">
				<tr>
					<td><c:out value="${board.boardId}" /></td>
					<td><c:out value="${board.upBoardName}" /></td>
					<td><c:out value="${board.boardName}" /></td>
					<td><c:out value="${board.adminName}" /></td>
					<td><img src="<c:out value="${board.savePath}"/>\<c:out value="${board.fileName}"/>" width="50" height="50"></img></td>
					<td><a href="/HansolWeb/MngrMenuAction?action=edit&boardId=<c:out value="${board.boardId}"/>">수정</a></td>
					<td><a href="/HansolWeb/MngrMenuAction?action=delete&boardId=<c:out value="${board.boardId}"/>">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/HansolWeb/MngrMenuAction?action=insert">게시판 추가</a>
	</p>
</body>
</html>