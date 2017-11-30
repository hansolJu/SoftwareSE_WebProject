/**
 * 관리자가 활동 정지된 사용자를 정지 해제 시켜주는 서블릿
 * 작성일 : 17.11.19
 * 수정일 : 17.11.27
 * 수정 내용 : 정지 해제를 시키고 정지된 회원들을 반환
 * 작성자 : 정은진
 */
package wedeal.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;
import wedeal.bean.UserDataBean;

/**
 * 활동 정지 상태의 회원의 정지 상태를 해제 시켜주는 서블릿
 */
@WebServlet("/MngrUserStartAction")
public class MngrUserStartAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		UserDBBean.getinstance().startUser(user_id);
		
		ArrayList<UserDataBean> bannedUserList = null; //정지 해제 후 보여줄 리스트
		bannedUserList = UserDBBean.getinstance().getBannedUser();
		request.setAttribute("bannedUserList", bannedUserList);
		request.setAttribute("count", new Integer(bannedUserList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/member/stopMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}
