package wedeal.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CateDBBean;

@WebServlet("/AddCateAction")
public class AddCateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCateAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		String cate_name = request.getParameter("cate_name");
		CateDBBean catedbbean = CateDBBean.getinstance();
		
		//대 카테고리를 추가했을 때
		if(request.getParameter("cate_parent") == null)
			catedbbean.add_out_cate(cate_name);
		//소 카테고리를 추가했을 때 
		else 
			catedbbean.add_in_cate(cate_name, Integer.parseInt(request.getParameter("cate_parent")));
	
		request.setAttribute("type", new Integer(1));
	}
}
