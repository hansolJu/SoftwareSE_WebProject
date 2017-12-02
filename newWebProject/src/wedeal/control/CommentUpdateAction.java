package wedeal.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CommentDBBean;
import wedeal.bean.CommentDataBean;

@WebServlet(value = "/CommentUpdateAction", name = "CommentUpdateAction")
public class CommentUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentUpdateAction() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		CommentDBBean comment = CommentDBBean.getinstance();
		CommentDataBean commentdt = new CommentDataBean();
		commentdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		commentdt.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
		commentdt.setComment_content(request.getParameter("comment_content"));
		comment.update(commentdt);
		System.out.println(commentdt.getBoard_num());//왜안가냐...
		request.getSession().setAttribute("messageType", "성공 메시지");
		request.getSession().setAttribute("messageContent", "댓글 수정을 성공했습니다.");
		RequestDispatcher view = request.getRequestDispatcher("board.jsp");
		request.setAttribute("board_num", commentdt.getBoard_num());
		view.forward(request, response);
		return;
	}

}
