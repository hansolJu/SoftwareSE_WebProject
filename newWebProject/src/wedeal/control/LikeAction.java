package wedeal.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.BoardDBBean;
import wedeal.bean.BoardDataBean;
import wedeal.bean.LikeDBBean;
import wedeal.bean.LikeDataBean;


@WebServlet(value = "/LikeAction", name = "LikeAction")
public class LikeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LikeAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDBBean board = BoardDBBean.getinstance();
		BoardDataBean boarddt = new BoardDataBean();
		LikeDBBean like = LikeDBBean.getinstance();
		LikeDataBean likedt = new LikeDataBean();
		
		
		likedt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		likedt.setUser_id(request.getParameter("user_id"));
		
		boarddt = board.getBoard(likedt.getBoard_num());
		
		if(like.check_id(likedt.getUser_id(),likedt.getBoard_num()) == 1 ) {
			like.delete(likedt);
			board.like(boarddt.getBoard_num(),boarddt.getBoard_like()-1);
			request.getSession().setAttribute("messageType", "알림");
			request.getSession().setAttribute("messageContent", "좋아요 취소");
			//response.sendRedirect("view.jsp?board_num="+likedt.getBoard_num());
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", likedt.getBoard_num());
			view.forward(request, response);
		}
		
		else {
			like.add(likedt);
			board.like(boarddt.getBoard_num(),boarddt.getBoard_like()+1);
			request.getSession().setAttribute("messageType", "알림");
			request.getSession().setAttribute("messageContent", "좋아요 누름");
			//response.sendRedirect("view.jsp?board_num="+likedt.getBoard_num());
			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			request.setAttribute("board_num", likedt.getBoard_num());
			view.forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
