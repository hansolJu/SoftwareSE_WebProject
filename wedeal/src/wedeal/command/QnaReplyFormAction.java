package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.QnaDBBean;
import wedeal.bean.QnaDataBean;

public class QnaReplyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//qna_id에 해당하는 QnA를 가져옴
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		QnaDataBean qna  =  qnaProcess.updateGetArticle(qna_id);
		
		//QnA답변에 필요한 정보를 얻어냄
		int book_id = qna.getBook_id();
		String book_title = qna.getBook_title();
		String qna_content = qna.getQna_content();
		byte qora = 2;//답변글
		
		request.setAttribute("qna_id", new Integer(qna_id));
		request.setAttribute("book_id", new Integer(book_id));
		request.setAttribute("book_title", book_title);
		request.setAttribute("qna_content", qna_content);
		request.setAttribute("qora", new Integer(qora));
		request.setAttribute("type", new Integer(0));
		return "/mngr/qnaProcess/qnaReplyForm.jsp";
	}
}