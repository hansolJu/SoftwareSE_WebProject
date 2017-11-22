

$(document).ready(function(){
	$("#allList").click(function(){//[카데고리 리스트 툴력]버튼 클릭
		window.location.href("wedeal.command.CateCreateAction");
	});
	$("#create").click(function(){//[활동정지맴버]버튼 클릭
		window.location.href("wedeal.command.CateCreateAction");
	});
	$("#update").click(function(){//[강제탈퇴맴버]버튼 클릭
		window.location.href("wedeal.command.CateUpdateAction");
	});
	
	$("#delete").click(function(){//[스탭]버튼 클릭
		window.location.href("wedeal.command.CateDeleteAction");
	});
});