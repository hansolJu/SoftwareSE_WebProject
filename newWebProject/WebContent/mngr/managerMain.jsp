<!-- 
메니저 메인페이지
작성자: 주한솔
수정자: 주한솔(11.19)
수정자 : 정은진
수정내용 : 회원 관리 부분(js포함)
최종수정일: 17.11.23
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/newWebProject/css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/newWebProject/js/bootstrap.js"></script>
<jsp:include page="/mngr/managerMain.jsp" />

<style>
ul{
   list-style:none;
   }
</style>
	<jsp:include page="/user/Menubar.jsp"/>
	<div id="mList" class="list-group" style="float:left">
		<h4>회원 관리</h4>
			<a href="/newWebProject/MngrUserAction" class="list-group-item">전체회원</a>
			<a href="/newWebProject/MngrBanAction" class="list-group-item">활동정지회원</a>
		<h4>스탭 관리</h4>
			<a href="/newWebProject/MngrStaffAction" class="list-group-item">스탭</a>
		<h4>메뉴 관리</h4>
			<a href="/newWebProject/MngrMenuAction" class="list-group-item">메뉴관리</a>
		<h4>신고 관리</h4>
			<a href="/newWebProject/MngrDeclarAction" class="list-group-item">신고처리 게시글</a>
	</div>