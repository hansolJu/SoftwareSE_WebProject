package wedeal.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CateDBBean;
import wedeal.bean.CateDataBean;

@WebServlet("/CateListAction")
public class CateListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CateListAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CateDBBean catedbbean = CateDBBean.getinstance();
		int count = 0;
		int in_count = 0;
		
		ArrayList<CateDataBean> list = catedbbean.getList();
		ArrayList<CateDataBean> in_list = catedbbean.in_getList();
		count = list.size();
		in_count = in_list.size();

		request.setAttribute("in_list", in_list);
		request.setAttribute("in_count", new Integer(in_count));
		request.setAttribute("list", list);
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(1));
	}

}
