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
 * Servlet implementation class MngrStaffListAction
 */
@WebServlet("/MngrStaffAction")
public class MngrStaffAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST = "/mngr/staff/staffList.jsp";
	private static String ADD = "/mngr/staff/staff.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String forward = "";
		String mngrAction = request.getParameter("mngrAction");
		
		if(mngrAction != null) {
			if (mngrAction.equals("dismissStaff")) {//삭제
				String staffId = request.getParameter("user_id");
				UserDBBean.getinstance().startUser(staffId);
				forward = LIST;
			} 
		}
		ArrayList<UserDataBean> staffList = null;
		staffList = UserDBBean.getinstance().getAllStaff();
		request.setAttribute("count", new Integer(staffList.size()));
		request.setAttribute("staffList", UserDBBean.getinstance().getAllStaff());

		RequestDispatcher view = request.getRequestDispatcher("/mngr/staff/staffList.jsp");
		view.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
