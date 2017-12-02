package wedeal.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.BoardDBBean;
import wedeal.bean.BoardDataBean;

/**
 * Servlet implementation class UserBoardList
 */
@WebServlet("/UserBoardList")
public class UserBoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String session_id = null;
		//ArrayList<String> cateNameList = new ArrayList<String>();
		ArrayList<BoardDataBean> boardlist = null;
		
		if(request.getSession().getAttribute("user_id")!=null)
			session_id = (String)request.getSession().getAttribute("user_id");
		
		BoardDBBean board = BoardDBBean.getinstance();
		boardlist = board.getUserBoardList(session_id);
		/*for(BoardDataBean b : boardlist) {
			String name = board.getCateName(b.getCate_num());
			cateNameList.add(name);
		}*/
		
		request.setAttribute("userboardlist", boardlist);
		//request.setAttribute("cateNameList", cateNameList);
		request.getRequestDispatcher("user/myBoard.jsp").forward(request, response);
	}
}
