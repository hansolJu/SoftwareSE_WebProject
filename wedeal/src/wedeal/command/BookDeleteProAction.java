package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.MngrDBBean;

public class BookDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String book_kind = request.getParameter("book_kind");
		
		//DB연동 - book_id에 해당하는 상품을 삭제
		MngrDBBean bookProcess = MngrDBBean.getInstance();
		bookProcess.deleteBook(book_id); 
		
		request.setAttribute("book_kind", book_kind);
		return "/mngr/productProcess/bookDeletePro.jsp";
	}
}