<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, wedeal.bean.CateDataBean, wedeal.bean.CateDBBean"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>SB Admin - Start Bootstrap Template</title>
  <!-- Bootstrap core CSS-->
  <link href="Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="Resources/css/sb-admin.css" rel="stylesheet">
</head>
<%
	CateDBBean cate = CateDBBean.getinstance();
	ArrayList<CateDataBean> out_cate = cate.getList();
	ArrayList<CateDataBean> in_cate = cate.in_getList();
%>
<body id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark  fixed-top" id="mainNav">
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
	<%
		for (int i = 0; i < out_cate.size(); i++) {
			for (int j = 0; j < in_cate.size(); j++) {
	%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text"><%=out_cate.get(i).getCate_name()%></span>
          </a>
          <%
		if (in_cate.get(j).getCate_parent() == out_cate.get(i).getCate_num()) {
	%>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
    
            <li>
              <a href="http://localhost:8080/newWebProject/board.jsp?cate_num=<%=in_cate.get(j).getCate_num()%>"><%=in_cate.get(j).getCate_name()%></a>
            </li>
            <%
							}
			%>
          </ul>
        </li>
 	<%
						}
					}
	%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
          <a class="nav-link" href="#">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Link</span>
          </a>
        </li>
      </ul>
    </div>
  </nav>
    
    <!-- Bootstrap core JavaScript-->
    <script src="Resources/vendor/jquery/jquery.min.js"></script>
    <script src="Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="Resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="Resources/vendor/datatables/jquery.dataTables.js"></script>
    <script src="Resources/vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="Resources/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="Resources/js/sb-admin-datatables.min.js"></script>
  </div>
</body>

</html>