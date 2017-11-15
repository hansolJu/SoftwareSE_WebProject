/**
 * 회원가입폼 함수
 * 작성자: 주한솔
 * 수정자: 이재윤
 * 최종수정일 : 17.11.15
 * @returns
 */
$(document).ready(function(){
	$("#checkId").click(function(){//[ID중복확인]버튼 클릭
	  if($("#user_id").val()){
		var query = {user_id:$("#user_id").val()};
		
	    $.ajax({
	    	type:"post",
	    	url:"/wedeal/confirmId.do",
	    	data:query,
	    	success:function(data){
	    		var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);
	    		if(check == "1"){//사용할 수 없는 아이디
	    			alert("사용할 수 없는 아이디");
	    	    	$("#user_id").val("");
	    	     }else//사용할 수 있는 아이디
	    	  	    alert("사용할 수 있는 아이디");
	 	    }
	    });
	  }else{//아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
		  alert("사용할 아이디를 입력");
		  $("#user_id").focus();
	  }
	});
	
	$("#process").click(function(){//[가입하기]버튼 클릭
		  var query = {user_name:$("#user_name"),
				  user_age:$("#user_age"),
				  user_phone:$("#user_phone"),
				  user_id:$("#id").val(), 
				  user_pw:$("#user_pw").val(),
			      user_hope:$("#user_hope").val()};
		  alert("눌리긴함");
		  $.ajax({
		      type:"post",
		      url:"/wedeal/registerPro.do",
		      data:query,
		      success:function(data){
		    	  window.location.href("/wedeal/index.do");
		 	  }
		  });
	});
	
	$("#cancle").click(function(){//[취소]버튼 클릭
		window.location.href("/wedeal/index.do");
	});

 });
