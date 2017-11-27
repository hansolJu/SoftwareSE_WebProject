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

@WebServlet("/CommentUpdateAction")
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
		CommentDBBean comment = CommentDBBean.getinstance();
		CommentDataBean commentdt = new CommentDataBean();
		
		commentdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		commentdt.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
		commentdt.setComment_content(request.getParameter("comment_content"));
		
		int result = comment.update(commentdt);
		
		if(result == -1) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "글쓰기에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			return;
		}
		
		else {
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", commentdt.getBoard_num());
			view.forward(request, response);
			return;
		}
		
	}

}
