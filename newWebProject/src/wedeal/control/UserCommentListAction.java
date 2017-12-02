package wedeal.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CommentDBBean;
import wedeal.bean.CommentDataBean;

/**
 * Servlet implementation class UserCommentList
 */
@WebServlet(value = "/UserCommentList", name = "UserCommentList")
public class UserCommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String session_id = null;
		ArrayList<CommentDataBean> commentlist = null;
		
		if(request.getSession().getAttribute("user_id")!=null)
			session_id = (String)request.getSession().getAttribute("user_id");
		
		CommentDBBean comment = CommentDBBean.getinstance();
		commentlist = comment.getUserCommentList(session_id);
		
		request.setAttribute("usercommentlist", commentlist);
		request.getRequestDispatcher("user/myComment.jsp").forward(request, response);
	}

}
