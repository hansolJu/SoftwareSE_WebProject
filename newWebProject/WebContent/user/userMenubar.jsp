<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://localhost:8080/newWebProject/css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="http://localhost:8080/newWebProject/js/bootstrap.js"></script>
<title>UserMenu</title>

</head>
<body>
	<div id="catelist" class="list-group" style="display: inline-block; border-right:1px solid #dddddd; float:left; height:1000px; width:7%;">
		<ul class="nav navbar-nav" style="list-style: none;">
			<li><a href="/newWebProject/UserBoardListAction">내 게시글</a></li>
			<li><a href="/newWebProject/UserCommentListAction">내 댓글</a></li>
			<li><a href="/newWebProject/UserLikeListAction">내 좋아요</a></li>
		</ul>
	</div>
</body>
</html>