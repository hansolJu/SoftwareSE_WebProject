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
 * 강제 탈퇴된 회원들을 가져오는 서블릿
 */
@WebServlet("/DeletedUserListAction")
public class DeletedUserListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserDataBean> deletedUserList = null;
		deletedUserList = UserDBBean.getinstance().getDeletedUser();
		if(deletedUserList.size() > 0)
			request.setAttribute("deletedUserList'", deletedUserList);
		request.setAttribute("count", new Integer(deletedUserList.size()));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mngr/memeber/fullMemberManage.jsp");
		dispatcher.forward(request, response);
	}
}


