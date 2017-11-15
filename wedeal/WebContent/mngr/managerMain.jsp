<!-- 
메니저 메인페이지
작성자: 주한솔
수정자: 
최종수정일: 17.11.15
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="/shoppingmall/js/jquery-1.11.0.min.js"></script>
<script src="/shoppingmall/mngr/managermain.js"></script>
<!-- 관리자 인증이 되지 않은 경우 표시되는 내용 -->    
<c:if test="${empty sessionScope.id}">
  <div id="mList"><p>로그인 하세요.
  </div>
</c:if>
<!-- 관리자 인증이 성공한 경우 표시되는 내용 -->
<c:if test="${!empty sessionScope.id}">
	<div id="mList">
		<ul>
			<li>회원 관리
			<li><button id="">전제회원정보</button>
		</ul>
		
		<ul>
			<li>스탭 관리
			<li><button id="">스탭</button>
			<li><button id="">활동정지 맴버</button>
			<li><button id="">강제 탈퇴 맴버</button>
		</ul>
		
		<ul>
			<li>메뉴 관리
			<li><button id="">전체 메뉴</button>
			<li><button id="">상품수정/삭제</button>
		</ul>
		
		<ul>
			<li>신고 관리
			<li><button id="">신고된 게시글</button>
			<li><button id="">삭제된 글/댓글 </button>
		</ul>

	</div>
</c:if>     