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
import wedeal.bean.DeclarationDBBean;

@WebServlet("/CommentControl")
public class CommentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		CommentDBBean comment = CommentDBBean.getinstance();
		CommentDataBean commentdt = new CommentDataBean();
		
		if(action != null) {
			if(action.equals("write")) {
				
				commentdt.setCate_num(Integer.parseInt(request.getParameter("cate_num")));
				commentdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
				commentdt.setComment_content(request.getParameter("comment_content"));
				commentdt.setUser_id((String)request.getSession().getAttribute("user_id"));
				
				System.out.println(commentdt.getCate_num());
				System.out.println(commentdt.getBoard_num());
				System.out.println(commentdt.getComment_content());
				System.out.println(commentdt.getUser_id());
				if(commentdt.getComment_content() == null || commentdt.getComment_content().equals("")){
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
					request.setAttribute("board_num", commentdt.getBoard_num());
				}
				
				else if(request.getParameter("board_num") == null || request.getParameter("board_num").equals("") || request.getSession().getAttribute("user_id") == null) {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "내부적인 오류입니다.");
					request.setAttribute("board_num", commentdt.getBoard_num());
					
				}
				
				else {
					int result = comment.write(commentdt);
					
					if(result == -1) {
						request.getSession().setAttribute("messageType", "오류 메시지");
						request.getSession().setAttribute("messageContent", "댓글 작성 실패");
						request.setAttribute("board_num", commentdt.getBoard_num());
					}
					else {
						request.getSession().setAttribute("messageType", "성공 메시지");
						request.getSession().setAttribute("messageContent", "댓글 작성을 성공했습니다.");
						request.setAttribute("board_num", commentdt.getBoard_num());
						}
				}
			}
			
			else if(action.equals("update")) {
				commentdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
				commentdt.setCate_num(Integer.parseInt(request.getParameter("cate_num")));
				commentdt.setComment_content(request.getParameter("comment_content"));
				commentdt.setUser_id((String)request.getSession().getAttribute("user_id"));
				int result = comment.update(commentdt);
				
				if(result == -1) {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "댓글 수정을 실패했습니다.");
					request.setAttribute("board_num", commentdt.getBoard_num());
				}
				
				else {
					request.getSession().setAttribute("messageType", "성공 메시지");
					request.getSession().setAttribute("messageContent", "댓글 수정을 성공했습니다.");
					request.setAttribute("board_num", commentdt.getBoard_num());
				}
			}
			
			else if(action.equals("delete")) {
				commentdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
				commentdt.setComment_num(Integer.parseInt(request.getParameter("comment_num")));
				int result = comment.delete(commentdt.getComment_num());
				
				if(result == -1) {
					request.getSession().setAttribute("messageType", "오류 메시지");
					request.getSession().setAttribute("messageContent", "댓글 삭제를 실패했습니다.");
					request.setAttribute("board_num", commentdt.getBoard_num());
				}
				
				else {
					request.getSession().setAttribute("messageType", "성공 메시지");
					request.getSession().setAttribute("messageContent", "댓글 삭제를 성공했습니다.");
					request.setAttribute("board_num", commentdt.getBoard_num());
				}
			}
			
			else if(action.equals("view")) {
				request.setAttribute("board_num", request.getParameter("board_num"));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
			dispatcher.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
