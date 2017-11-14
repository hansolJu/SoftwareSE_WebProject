
$(document).ready(function(){
	//중복확인버튼을 눌렀을 경우
	$("#checkId").click(function(){		
		if($("#user_id").val()){
			var query = {user_id:$("#user_id").val()};
			$.ajax({
				type: "POST",
				url: "/WebProject/confirmId.do",
				data: query,
				success:function(data){
					var str1 = '<p id="ck">';
		    		var loc = data.indexOf(str1);
		    		var len = str1.length;
		    		var check = data.substr(loc+len,1);
					if(data == 1){
						alert("사용할 수 없는 아이디입니다.");
						$("#user_id").val("");
					}else if(data == -1)
						alert("사용할 수 있는 아이디입니다.");
				}
			});
		}else{
			alert("아이디를 입력해주세요.");
			$("#user_id").focus();
		}
	});
	
	//가입하기 버튼을 클릭할 경우
	$("#process").click(function(){
		checkIt();
			var query = {user_name:$("#user_name").val(),
					user_age:$("#user_age").val(),
					user_phone:$("#user_phone").val(),
					user_id:$("#user_id").val(),
					user_pw:$("#user_pw").val(),
					user_hope:$("#user_hope").val()
					};
			$.ajax({
				type: "POST",
				url: "/registerPro.do",
				type: query,
				success:function(data){
					window.location.href("~~");
				}
			});
		});
	
	$("#cancle").click(function(){
		window.location.href("~~~~");
	});
});
