/**
 * 관리자가 사용자를 강제 탈퇴시키는 서블릿
 * 작성일 : 17.11.19
 * 수정일 :
 * 작성자 : 정은진
 */
package wedeal.control;

import java.io.IOException;
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
 * Servlet implementation class UserListAction
 */
@WebServlet("/UserDeleteAction")
public class UserDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		UserDBBean.getinstance().deleteUser(user_id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/memeber/fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}


