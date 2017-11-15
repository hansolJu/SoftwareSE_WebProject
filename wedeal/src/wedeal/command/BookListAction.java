package wedeal.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.MngrDBBean;
import wedeal.bean.MngrDataBean;

public class BookListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<MngrDataBean> bookList = null;
		String book_kind = request.getParameter("book_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		MngrDBBean bookProcess = MngrDBBean.getInstance();
        count = bookProcess.getBookCount(); 
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 bookList에 저장
        	bookList = bookProcess.getBooks(book_kind);
        	//bookList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("bookList", bookList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("book_kind", book_kind);
        request.setAttribute("type", new Integer(0));
		return "/mngr/productProcess/bookList.jsp";
	}
}