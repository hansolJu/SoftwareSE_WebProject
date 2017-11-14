package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.QnaDBBean;

public class QnaDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int qna_id =  Integer.parseInt(request.getParameter("qna_id"));
		
		//qna_id에 해당하는 qna삭제
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int check = qnaProcess.deleteArticle(qna_id);
		
		request.setAttribute("check", new Integer(check));
		return "/qna/qnaDeletePro.jsp";
	}
}