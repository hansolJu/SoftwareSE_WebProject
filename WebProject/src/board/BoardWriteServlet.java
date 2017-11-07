package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String board_title=request.getParameter("board_title");
		String user_id=(String)request.getSession().getAttribute("user_id");
		String board_content=request.getParameter("board_content");
		
		if(board_title == null || board_title.equals("") || user_id == null || user_id.equals("") || board_content == null || board_content.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("write.jsp");
			return;
		}
		
		else {
			boardDAO boarddao=new boardDAO();
			
			int result = boarddao.write(board_title,user_id,board_content);
			
			if(result == -1) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "글쓰기에 실패했습니다.");
				response.sendRedirect("write.jsp");
				return;
			}
			
			else 
				response.sendRedirect("board.jsp");
		}

	}

}
