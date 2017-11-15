/**
 * 회원가입폼 함수
 * 작성자: 주한솔
 * 수정자: 이재윤
 * 최종수정일 : 17.11.15
 * @returns
 */

var status = true;

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
		checkIt();
		
		if(status){
		  var query = {user_name:$("#user_name").val(),
				  user_age:$("#user_age").val(),
				  user_phone:$("#user_phone").val(),
				  user_id:$("#id").val(), 
				  user_pw:$("#user_pw").val(),
			      user_hope:$("input[name=user_hope]:checked").val()};
		  alert("눌리긴함");
		  $.ajax({
		      type:"post",
		      url:"/wedeal/registerPro.do",
		      data:query,
		      success:function(data){
		    	  window.location.href("/wedeal/index.do");
		 	  }
		  });
		}
	});
	
	$("#cancle").click(function(){//[취소]버튼 클릭
		window.location.href("/wedeal/index.do");
	});

 });

//사용자가 입력폼에 입력한 상황을 체크
function checkIt() {
	status = true;
	
	
	if(!$("#user_name").val()) {//아이디를 입력하지 않으면 수행
        alert("이름을 입력하세요");
        $("#user_name").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
	
	if(!$("#user_age").val()) {//아이디를 입력하지 않으면 수행
        alert("나이를 입력하세요");
        $("#user_age").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
	
	if(!$("#user_phone").val()) {//아이디를 입력하지 않으면 수행
        alert("핸드폰 번호를 입력하세요");
        $("#user_phone").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
	
    if(!$("#user_id").val()) {//아이디를 입력하지 않으면 수행
        alert("아이디를 입력하세요");
        $("#user_id").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
    
    if(!$("#user_pw").val()) {//비밀번호를 입력하지 않으면 수행
        alert("비밀번호를 입력하세요");
        $("#user_pw").focus();
        status = false;
        return false;
    }
    
    if(!$("#name").val()) {//이름을 입력하지 않으면 수행
        alert("사용자 이름을 입력하세요");
        $("#name").focus();
        status = false;
        return false;
    }
    
    if(!$("input[name=user_hope]:checked").val()) {//전화번호를 입력하지 않으면 수행
        alert("관심 상품을 선택해주세요");
        $("input[name=user_hope]:checked").val();
        status = false;
        return false;
    }  
}