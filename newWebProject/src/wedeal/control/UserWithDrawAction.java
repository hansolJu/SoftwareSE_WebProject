package wedeal.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wedeal.bean.UserDBBean;

/**
 * Servlet implementation class UserWithDrawAction
 */
@WebServlet("/UserWithDrawAction")
public class UserWithDrawAction extends HttpServlet {
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
		// TODO Auto-generated method stub
		UserDBBean user = UserDBBean.getinstance();
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		int checkit = user.registerCheck(user_id);
		if(checkit == 0) {
			boolean check = user.deleteUser(user_id);
			if(check == true) {
				request.setAttribute("messageType", "성공 메시지");
				request.setAttribute("messageContent", "탈퇴가 완료 되었습니다.");
				request.getSession().invalidate();
				response.sendRedirect("http://localhost:8080/newWebProject/index.jsp");
			}
			else {
				request.setAttribute("messageType", "오류 메시지");
				request.setAttribute("messageContent","탈퇴에 실패 했습니다.");
				response.sendRedirect("http://localhost:8080/newwebProject/user/deleteUser.jsp");
			}
		}
		else {
			request.setAttribute("messageType", "오류 메시지");
			request.setAttribute("messageContent", "회원 정보를 찾지 못했습니다.");
			response.sendRedirect("http://localhost:8080/newWebProject/user/deleteUser.jsp");
		}
	}
}
