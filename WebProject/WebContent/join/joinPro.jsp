<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.wedeal.dao.UserDao" %>
<%@ page import = "java.sql.Timestamp" %>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="../js/jquery-1.11.0.min.js"></script>

<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="member" class="com.wedeal.model.User">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>

<%  
 UserDao manager = UserDao.getInstance();
  //사용자가 입력한 데이터저장빈 객체를 가지고 회원가입 처리 메소드호출
  manager.addUser(member);
  
%>