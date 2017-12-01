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


@WebServlet("/CommentWriteAction")
public class CommentWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentWriteAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		CommentDBBean comment = CommentDBBean.getinstance();
		CommentDataBean commentdt = new CommentDataBean();
		
		String cate_num = request.getParameter("cate_num");
		String board_num = request.getParameter("board_num");
		commentdt.setComment_content(request.getParameter("comment_content"));
		commentdt.setUser_id(request.getParameter("user_id"));
		
		if(commentdt.getComment_content() == null || commentdt.getComment_content().equals("")){
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			return;
		}
		
		else if(cate_num == null || cate_num.equals("") || board_num == null || board_num.equals("") || commentdt.getUser_id() == null || commentdt.getUser_id().equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "내부적인 오류입니다.");
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			return;
		}
		else {
		commentdt.setCate_num(Integer.parseInt(cate_num));
		commentdt.setBoard_num(Integer.parseInt(board_num));
		
		int result = comment.write(commentdt);
		
		if(result == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "댓글 작성 실패");
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			return;
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			}
		}
	}
}
