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
@WebServlet("/UserListAction")
public class UserListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDataBean> userList = null;
		userList = UserDBBean.getinstance().getAllUser();
		if(userList.size() > 0)
			request.setAttribute("userList", userList);
		request.setAttribute("count", new Integer(userList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}


