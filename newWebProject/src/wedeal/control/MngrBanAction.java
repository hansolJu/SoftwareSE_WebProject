package wedeal.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;
import wedeal.bean.UserDataBean;

/**
 * Servlet implementation class MngrBanAction
 */
@WebServlet("/MngrBanAction")
public class MngrBanAction extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String mngrAction = request.getParameter("mngrAction");
		
		if(mngrAction != null) {
			if(mngrAction.equals("userStart")) {
				UserDBBean.getinstance().startUser(request.getParameter("user_id"));
			}
		}
		
		ArrayList<UserDataBean> bannedUserList = null;
		bannedUserList = UserDBBean.getinstance().getBannedUser();
		request.setAttribute("bannedUserList", bannedUserList);
		request.setAttribute("count", new Integer(bannedUserList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mngr/member/stopMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}
