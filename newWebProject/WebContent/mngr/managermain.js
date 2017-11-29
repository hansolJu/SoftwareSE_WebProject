/**
 * 메인페이지 함수
 * 작성자: 주한솔
 * 수정자: 주한솔
 * 최종수정일: 17.11.23
 * 
 */
//모델 2 구조에서 $("#main_auth").load(로드할 페이지)와 같이 화면의 특정 영역에 .do 요청을 통해 웹페이지를 넣을 수 없다.
//화면이 필요한 페이지의 경우 window.location.href(로드할페이지)와 같이 사용해야한다.
var status = true;

$(document).ready(function(){
	$("#member").click(function(){//[회원]버튼 클릭
		window.location.href="/newWebProject/MngrUserListAction";
	});
	$("#mBanned").click(function(){//[강제탈퇴맴버]버튼 클릭
		window.location.href="/newWebProject/MngrBannedUserListAction";
	});
	$("#step").click(function(){//[스탭]버튼 클릭
		window.location.href='staff/staffList.jsp';
	});
	$("#menu").click(function(){//[메뉴]버튼 클릭
		window.location.href='menu/boardList.jsp';
	});
	$("#spam").click(function(){//[신고글]버튼 클릭
		window.location.href='spam/spamList.jsp'
	});
	$("#deleted").click(function(){//[삭제글,댓글]버튼 클릭
		window.location.href='deleted/delList.jsp';
	});
});