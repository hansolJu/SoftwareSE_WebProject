/**
 * 관리자가 사용자를 활동 정지시키는 서블릿
 * 작성일 : 17.11.19
 * 수정일 : 17.11.27
 * 수정 내용 : 사용자를 정지시킨 후 결과리스트를 보여준다.
 * 작성자 : 정은진
 */
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
 *회원을 황동 정지 시키는 서블릿
 */
@WebServlet("/MngrUserBanAction")
public class MngrUserBanAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		UserDBBean.getinstance().banUser(user_id);
		
		ArrayList<UserDataBean> userList = null; //삭제 후 보여줄 리스트
		userList = UserDBBean.getinstance().getAllUser();
		request.setAttribute("userList", userList);
		request.setAttribute("count", new Integer(userList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/member/fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}
