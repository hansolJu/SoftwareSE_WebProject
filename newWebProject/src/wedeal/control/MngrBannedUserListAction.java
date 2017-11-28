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
 * 활동 정지된 회원들을 가져오는 서블릿
 */
@WebServlet("/MngrBannedUserListAction")
public class MngrBannedUserListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ArrayList<UserDataBean> bannedUserList = null;
		bannedUserList = UserDBBean.getinstance().getBannedUser();
		request.setAttribute("bannedUserList", bannedUserList);
		request.setAttribute("count", new Integer(bannedUserList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mngr/member/stopMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}