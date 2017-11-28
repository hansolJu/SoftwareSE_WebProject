package wedeal.control;

import java.io.IOException;
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
		
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		
		CommentDBBean comment = CommentDBBean.getinstance();
		CommentDataBean commentdt = comment.getComment(comment_num);
		
		request.setAttribute("comment", comment);
	}

}
