<!--
게시판 생성 페이지
작성자: 주한솔
수정자: 주한솔
:
최종수정일: 17.11.23
  -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>

    <form method="POST" action='MngrMenuAction' enctype="multipart/form-data" name="frmAddBoard">
        Board ID : <input type="text" readonly="readonly" name="cate_num" value="<c:out value="${board.cate_num}" />" /> <br /> 
        Board Name : <input type="text" name="cate_name" value="<c:out value="${board.cate_name}" />" /> <br /> 
        UpBoard Name : <input type="text" name="upCategoryName" value="<c:out value="${board.cate_name}" />" /> <br /> 
        Admin Image : <input type="file" name="fileName" value="<c:out value="${board.fileName}" />" /> <br />
        
        <input type="submit" value="Submit" />
    </form>
</body>
</html>