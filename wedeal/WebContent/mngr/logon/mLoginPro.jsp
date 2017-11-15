<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 회원인증이 성공하면 session 속성 id를 설정해 세션을 지정한다. -->
<c:if test="${check == 1}">
	<c:set var="id" value="${id}" scope="session"/>
</c:if>