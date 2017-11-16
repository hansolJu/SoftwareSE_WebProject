<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/wedeal/css/style.css"/>
<script src="/wedeal/js/jquery-1.11.0.min.js"></script>
<script src="/wedeal/member/login.js"></script>

<c:if test="${empty sessionScope.user_id}">
  <div id="lStatus">
     <ul>
        <li><label for="user_id">아이디</label>
            <input type="text" id="user_id" placeholder="아이디" name="user_id" maxlength="10" autofocus>
            <label for="user_pw">비밀번호</label>
            <input type="password" id="user_pw" placeholder="비밀번호" name="user_pw" maxlength="20">
            <button id="uLogin">로그인</button>
            <button id="uRes">회원가입</button>
     </ul>
  </div>
</c:if>
<c:if test="${!empty sessionScope.user_id}">
  <div id="lStatus">
     <ul>
        <li>${sessionScope.user_id}님이 로그인 하셨습니다.
           <div id="info">
             <table>
               <tr height="10">
                 <td><button id="uLogout">로그아웃</button></td>
                 <td><button id="uUpdate">회원 정보 변경</button></td>
                 <td><form id="cartForm" method="post" action="/wedeal/cartList.do">
                   <input type="hidden" name="buyer" value="${sessionScope.id}">
                   <input type="submit" name="cart" value="장바구니"></form></td>
                 <td><form id="buyForm" method="post" action="/wedeal/buyList.do">
                   <input type="hidden" name="buyer" value="${sessionScope.id}">
                   <input type="submit" name="buy" value="구매내역"></form></td>
                 </tr>
             </table>
        </div>     
            
     </ul>
  </div>
</c:if> 