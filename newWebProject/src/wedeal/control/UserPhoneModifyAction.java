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
 * Servlet implementation class UserPhoneModifyAction
 */
@WebServlet("/UserPhoneModifyAction")
public class UserPhoneModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String user_phone = (String) request.getParameter("user_phone");

		UserDBBean user = UserDBBean.getinstance();
		boolean result =user.modifyPhone(user_id, user_phone);
		
		if(result == true) {
			response.sendRedirect("user/userInfo.jsp");
		}
		else if(result == false) {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "내부적인 오류입니다.");
			response.sendRedirect("user/userInfo.jsp");
		}
	}
}
