package wedeal.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.MngrStaffDBBean;

/**
 * Servlet implementation class MngrStaffListAction
 */
@WebServlet("/MngrStaffListAction")
public class MngrStaffAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST = "/mngr/staff/staffList.jsp";
	private static String ADD = "/mngr/staff/staff.jsp";
	private MngrStaffDBBean dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MngrStaffAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {//삭제
			int staffId = Integer.parseInt(request.getParameter("Id"));
			dao.deleteStaff(staffId);
			forward = LIST;
			request.setAttribute("borads", dao.getAllList());
		} else {//삽입
			forward = ADD;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
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
