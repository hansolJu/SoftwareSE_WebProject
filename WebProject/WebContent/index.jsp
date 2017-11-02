<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>

<div id="header">
  <div id="logo" class="box">
    <img class="noborder" id="logo" src="/shoppingmall/images/mollalogo3.png"/>
  </div>
  <div id="auth" class="box">
    <c:if test="${type == 0}"><!-- type값이 0이면 관리자코드로, 관리자용 로그인폼이 표시된다. -->
      <jsp:include page="mngr/logon/mLoginForm.jsp"/>
    </c:if>
    <c:if test="${type == 1}"><!-- type값이 1이면 사용자 코드로, 사용자용 로그인 폼이 표시된다. -->
      <jsp:include page="member/loginForm.jsp"/>
    </c:if>
  </div>
</div>
<!-- 템플릿 화면의 내용 부분이다. 
로직에서 응답한 페이지가 템플릿의 내용이 되며 이것은 Controller의 코드 130~줄정도의 request속성값으로 지정했다. 
이값은 템플릿인 index.jsp페이지로 넘겨져서 화면에 표시된다. 
cont변수에는 화면의 내용에 해당하는 페이지가 저장되어 있어서 <jsp:include>를 사용해서 화면에 표시된다. -->
<div id="content" class="box2">
  <jsp:include page="${cont}"/>
</div>