$(document).ready(function(){
	$("#uRes").click(function(){//[회원가입]버튼 클릭
		window.location.href("/wedeal/registerForm.do");
	});
	
	$("#uLogin").click(function(){//[로그인]버튼 클릭
		  var query = {user_id : $("#user_id").val(), 
				       user_pw:$("#user_pw").val()};
		  
		  $.ajax({
		     type: "POST",
		     url: "/wedeal/loginPro.do",
		     data: query,
		     success: function(data){
		    	 var str1 = '<p id="ck">';
		    	 var loc = data.indexOf(str1);
		    	 var len = str1.length;
		    	 var check = data.substr(loc+len,1);
		    	 if(check == "1"){//
		    		window.location.href("/wedeal/index.do");
		    	 }else if(check == "0"){
		    	  	alert("비밀번호 틀림");
		    	  	$("#user_pw").val("");
		    	 }else{
		    	    alert("아이디 틀림");
		    	    $("#user_id").val("");
		    	    $("#user_pw").val("");
		        }
		 	}
		  });
	});
	
	$("#uUpdate").click(function(){//[회원 정보 변경]버튼 클릭
		window.location.href("/wedeal/modify.do");
	});
	
	$("#uLogout").click(function(){//[로그아웃]버튼 클릭
		$.ajax({
		   type: "POST",
		   url: "/wedeal/logout.do",
		   success: function(data){
			   window.location.href("/wedeal/index.do");
		   }
		});
	});
	
	$("#cart").click(function(){//[장바구니]버튼 클릭
		window.location.href("/wedeal/cartList.do");
	});
	
	$("#buy").click(function(){//[구매내역]버튼 클릭
		window.location.href("/wedeal/buyList.do");
	});

});
