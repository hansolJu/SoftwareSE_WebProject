<!--
카테고리 화면
 작성자:이재윤
 수정자:
 최종수정일: 17.11.17
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="/wedeal/board/cartList.js"></script>

<!--
<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=/shoppingmall/index.do">
</c:if>
-->

<div id ="test" class="box2">
	<ul>
		<li><a href="/wedeal/list.do">카테고리 보기</a>
	</ul>
</div>
<div id="goShop">
  <button id="conShopping">쇼핑계속</button>
  <button id="shopMain">메인으로</button>
</div>
<div id="cateList">
<c:if test="${empty count}">
   <ul>
      <li>생성된 카테고리가 없습니다.
   </ul>
</c:if>
<c:if test="${count > 0}">
  <table> 
  <tr> 
   <td width="300">카테고리들</td> 
  </tr>
  <c:set var="total" value="0"/>
  <c:forEach var="list" items="${list}">
    <tr> 
       <td  width="300">
         <a href="각 이름에 관한 페이지 넘겨랑">${list.getCate_name()}</a> >>
         <c:if test="${in_count > 0}">
         	<c:forEach var="in_list" items="${in_list}">
         		<c:if test="${list.getCate_num() == in_list.getCate_parent()}">
         			<a href="각 이름에 관한 페이지 넘겨랑">${in_list.getCate_name()}</a> 
         		</c:if>
         	</c:forEach>
         </c:if>
        </td>
    </tr>
   </c:forEach>
  </table>
</c:if>
</div> 