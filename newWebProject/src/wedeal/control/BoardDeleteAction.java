package wedeal.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.BoardDBBean;


@WebServlet("/BoardDeleteAction")
public class BoardDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int cate_num = Integer.parseInt(request.getParameter("cate_num"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDBBean board = BoardDBBean.getinstance();
		int result = board.delete(board_num);
		
		if(result == -1) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "글 삭제를 실패했습니다.");
			response.sendRedirect("history.back()");
			return;
		}
		
		else
		response.sendRedirect("board.jsp?cate_num="+cate_num);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
