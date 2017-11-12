package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserTelModifyAction
 */
@WebServlet("/UserTelModify")
public class UserTelModifyAction extends HttpServlet {
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		String user_phone = request.getParameter("user_phone");
		
		if(user_phone == null) {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "전화번호를 다시 입력해주십시오.");
			response.sendRedirect("modifyPhone.jsp");
			return;
		}
		
		userDAO user = new userDAO();
		boolean result = user.modifyPhone(user_id, user_phone);
		if(result == true) {
			response.sendRedirect("modifyPhone.jsp");
			return;
		}
		
		else {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "내부적인 오류입니다.");
			response.sendRedirect("userinfo.jsp");
			return;
		}
	}
}
