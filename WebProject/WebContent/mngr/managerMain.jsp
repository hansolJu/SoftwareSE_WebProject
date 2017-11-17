<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/WebProject/js/jquery-1.11.0.min.js"></script>
<script src="/WebProject/mngr/managermain.js"></script>
<!-- 관리자 인증이 되지 않은 경우 표시되는 내용 -->    
<c:if test="${empty sessionScope.id}">
  <div id="mList"><p>로그인 하세요.
  </div>
</c:if>
<!-- 관리자 인증이 성공한 경우 표시되는 내용 -->
<c:if test="${empty sessionScope.id}">
	<div id="mList">
		<ul>
			<li>회원 관리
			<li><button id="memberList">전제회원정보</button>
			<li><button id="bannedList">강제 탈퇴 맴버</button>
		</ul>
		<ul>
			<li>스탭 관리
			<li><button id="stepList">스탭</button>
		</ul>
		<ul>
			<li>메뉴 관리
			<li><button id="menuList">메뉴 관리</button>
		</ul>
		<ul>
			<li>신고 관리
			<li><button id="spamList">신고된 게시글</button>
			<li><button id="deletedList">삭제된 글/댓글 </button>
		</ul>
	</div>
</c:if>     