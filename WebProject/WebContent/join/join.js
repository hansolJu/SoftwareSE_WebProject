var status = true;

$(document).ready(function(){
	//[ID중복확인]버튼을 클릭하면 자동실행
	//입력한 아이디 값을 갖고 confirmId.jsp페이지 실행
	$("#checkId").click(function(){
	  if($("#id").val()){
		//아이디를 입력하고 [ID중복확인]버튼을 클릭한 경우
		var query = {id:$("#id").val()};
		
	    $.ajax({
	    	type:"post",//요청방식
	    	url:"confirmId.jsp",//요청페이지
	    	data:query,//파라미터
	    	success:function(data){//요청페이지 처리에 성공시
	    		if(data == 1){//사용할 수 없는 아이디
	    			alert("사용할 수 없는 아이디");
	    	    	$("#id").val("");
	    	     }else if(data == -1)//사용할 수 있는 아이디
	    	  	    alert("사용할 수 있는 아이디");
	 	    }
	    });
	  }else{//아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
		  alert("사용할 아이디를 입력");
		  $("#id").focus();
	  }
	});
	
	//[가입하기]버튼을 클릭하면 자동실행
	//사용자가 가입폼인 registerForm.jsp페이지에 입력한 내용을 갖고
	//registerPro.jsp페이지 실행
	$("#process").click(function(){
	   checkIt(); //입력폼에 입력한 상황 체크
	   
	   if(status){
		  var query = {
				  
				  user_name:$("#user_name").val(),
				  user_age:$("#user_age").val(),
				  user_phone:$("#user_phone").val(),
				  user_id:$("#user_id").val(),
				  user_passwd:$("#user_passwd").val()};
		  
		  $.ajax({
		      type:"post",
		      url:"joinPro.jsp",
		      data:query,
		      success:function(data){
		    	 alert("성공!");
		    	  // window.location.href("main.jsp");
		 	  }
		  });
	   }
	});
	
	//[취소]버튼을 클릭하면 자동실행
	$("#cancle").click(function(){
		alert("취소!");
		//window.location.href("main.jsp");
	});

 });

//check writing data
function checkIt() {
	status = true;
	
    if(!$("#user_name").val()) {//아이디를 입력하지 않으면 수행
        alert("이름을 입력하세요!");
        $("#user_name").focus();
        status = false;
        return false;//사용자가 서비스를 요청한 시점으로 돌아감
    }
    
    if(!$("#user_age").val()) {//비밀번호를 입력하지 않으면 수행
        alert("나이를 입력하세요!");
        $("#user_age").focus();
        status = false;
        return false;
    }
    
    if(!$("#user_phone").val()) {//비밀번호를 입력하지 않으면 수행
        alert("핸드폰 번호를 입력하세요!");
        $("#user_phone").focus();
        status = false;
        return false;
    }
    /*
    //비밀번호와 재입력비밀번호가 같지않으면 수행
    if($("#passwd").val() != $("#repass").val()){
        alert("비밀번호를 동일하게 입력하세요");
        $("#repass").focus();
        status = false;
        return false;
    }*/
    
    if(!$("#user_id").val()) {//이름을 입력하지 않으면 수행
        alert("아이디를 입력하세요!");
        $("#user_id").focus();
        status = false;
        return false;
    }
    
    if(!$("#user_passwd").val()) {//주소를 입력하지 않으면 수행
        alert("비밀번호를 입력하세요!");
        $("#user_passwd").focus();
        status = false;
        return false;
    }
    
}