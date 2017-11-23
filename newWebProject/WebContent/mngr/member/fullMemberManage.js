
$(document).ready(function(){
	$("#bb").click(function(){
		$.ajax({
			   type: "POST",
			   url: "/UserListAction",
			   success: function(data){
				   window.location.href("/mngr/member/fullMemberManage.jsp");
			   }
			});
	});
	$("#stop").click(function(){
		$.ajax({
			   type: "POST",
			   url: "/wedeal/contorl/UserStopAction",
			   success: function(data){
				   window.location.href("/mngr/member/fullMemberManage.jsp");
			   }
			});
		//window.location.href = "/wedeal/control/UseStopAction";
	});
	$("#out").click(function(){
		window.location.href = "/wedeal/control/UserOutAction";
	});
});