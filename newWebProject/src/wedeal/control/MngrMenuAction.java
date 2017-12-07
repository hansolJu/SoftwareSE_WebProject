package wedeal.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CateDBBean;
import wedeal.bean.CateDataBean;
/**
 * Servlet implementation class MngrMenuListAction
 */
@WebServlet(value = "/MngrMenuAction", name = "MngrMenuAction")
public class MngrMenuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 삽입, 편집외 모든기능을 맡아서 한다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "";
		String action = request.getParameter("action");
		CateDBBean cate = CateDBBean.getinstance();
		if(action == null)
			address = "mngr/menu/catelist.jsp";
		else {
			if (action.equals("delete")) {//삭제
				int cate_num = Integer.parseInt(request.getParameter("cate_num"));
				System.out.println(cate_num);
				cate.delete_cate(cate_num);
				address = "mngr/menu/catelist.jsp";
			} else if (action.equals("update")) {//수정
				int cate_num = Integer.parseInt(request.getParameter("cate_num"));
				CateDataBean catedt = cate.getcate(cate_num); 
				request.setAttribute("cate_name", catedt.getCate_name());
				request.setAttribute("cate_num", cate_num);
				address = "mngr/menu/updatecate.jsp";
			} else if (action.equals("add")) {//삽입
				address = "mngr/menu/addcate.jsp";
			}
		}

		RequestDispatcher view = request.getRequestDispatcher(address);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 추가또는 편집을 맡아서 한다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		CateDBBean cate = CateDBBean.getinstance();
		String address = "";
		String action = request.getParameter("action");
		if(action.equals("update_comp")) {
			CateDataBean catedt = new CateDataBean();
			System.out.println(request.getParameter("cate_num"));
			catedt.setCate_num(Integer.parseInt(request.getParameter("cate_num")));
			catedt.setCate_name(request.getParameter("cate_name"));

			if(!request.getParameter("cate_parent").equals("")) {
				catedt.setCate_parent(Integer.parseInt(request.getParameter("cate_parent")));
			}
			cate.update_cate(catedt);
			address = "mngr/menu/catelist.jsp";
		}

		else if(action.equals("add_comp")) {
			CateDataBean catedt = new CateDataBean();
			catedt.setCate_name(request.getParameter("cate_name"));
			if(!request.getParameter("cate_parent").equals("")) {
				catedt.setCate_parent(Integer.parseInt(request.getParameter("cate_parent")));
				cate.add_in_cate(catedt.getCate_name(), catedt.getCate_parent());
			}
			else {
				cate.add_out_cate(catedt);
			}
			address = "mngr/menu/catelist.jsp";
		}
		RequestDispatcher view = request.getRequestDispatcher(address);
		view.forward(request, response);
	}

}
