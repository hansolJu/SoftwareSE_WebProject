//모델 2 구조에서 $("#main_auth").load(로드할 페이지)와 같이 화면의 특정 영역에 .do 요청을 통해 웹페이지를 넣을 수 없다.
//화면이 필요한 페이지의 경우 window.location.href(로드할페이지)와 같이 사용해야한다.
var status = true;

$(document).ready(function(){
	$("#registProduct").click(function(){//[상품등록]버튼 클릭
		window.location.href("/shoppingmall/mg/bookRegisterForm.do");
	});
	
	$("#updateProduct").click(function(){//[상품수정/삭제]버튼 클릭
		window.location.href("/shoppingmall/mg/bookList.do?book_kind=all");
	});
	
	$("#orderedProduct").click(function(){//[전체구매목록 확인]버튼 클릭
		window.location.href("/shoppingmall/mg/orderList.do");
	});
	
	$("#qna").click(function(){//[상품 QnA답변]버튼 클릭
		window.location.href("/shoppingmall/mg/qnaList.do");
	});
});