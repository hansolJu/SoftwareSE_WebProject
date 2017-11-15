$(document).ready(function(){
	$("#uRes").click(function(){
		window.location.href("/WebProject/registerForm.do");
	});
	
	$("#uLogin").click(function(){
		var query = {user_id:$("#user_id").val(),
				user_pw:$("user_pw").val()};
		
		$.ajax({
			type: "POST",
			url: "/com.wedeal.command/loginPro.do",
			data: query,
			success: function(data){
				var str1 = '<p id= "ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc+len,1);
				if(check == "1"){
					window.location.href("/Web~~");
				}else if(check == "0"){
					alert("비밀번호가 틀렸습니다.");
					$("#user_pw").val("");
				}else{
					alert("아이디가 틀렸습니다.");
					$("#user_id").val("");
					$("user_pw").val("");
				}
			}
		});
	});
	
	$("#uLogout").click(function(){
		$.ajax({
			type: "POST",
			url: "/com.wedeal.command/logout.do",
			success: function(data){
				window.loacation.href("~~");
			}
		});
	});
});