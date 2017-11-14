$(document).ready(function(){
	$("#uRes").click(function(){//[회원가입]버튼 클릭
		window.location.href("/WeDeal/registerForm.do");
	});
	
	$("#uLogin").click(function(){//[로그인]버튼 클릭
		  var query = {id : $("#cid").val(), 
				       passwd:$("#cpasswd").val()};
		  
		  $.ajax({
		     type: "POST",
		     url: "/WeDeal/loginPro.do",
		     data: query,
		     success: function(data){
		    	 var str1 = '<p id="ck">';
		    	 var loc = data.indexOf(str1);
		    	 var len = str1.length;
		    	 var check = data.substr(loc+len,1);
		    	 if(check == "1"){//
		    		window.location.href("/WeDeal/index.do");
		    	 }else if(check == "0"){
		    	  	alert("비밀번호 틀림");
		    	  	$("#cpasswd").val("");
		    	 }else{
		    	    alert("아이디 틀림");
		    	    $("#cid").val("");
		    	    $("#cpasswd").val("");
		        }
		 	}
		  });
	});
	
	$("#uUpdate").click(function(){//[회원 정보 변경]버튼 클릭
		window.location.href("/WeDeal/modify.do");
	});
	
	$("#uLogout").click(function(){//[로그아웃]버튼 클릭
		$.ajax({
		   type: "POST",
		   url: "/WeDeal/logout.do",
		   success: function(data){
			   window.location.href("/WeDeal/index.do");
		   }
		});
	});
	
	$("#cart").click(function(){//[장바구니]버튼 클릭
		window.location.href("/WeDeal/cartList.do");
	});
	
	$("#buy").click(function(){//[구매내역]버튼 클릭
		window.location.href("/WeDeal/buyList.do");
	});

});
