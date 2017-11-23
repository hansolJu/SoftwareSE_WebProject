package wedeal.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;

@WebServlet("/ConfirmIdAction")
public class ConfirmIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmIdAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String user_id = request.getParameter("user_id");
		//주어진 id의 중복여부를 체크해 값을 반환.
		UserDBBean manager = UserDBBean.getinstance();
		int check= manager.registerCheck(user_id);
		response.getWriter().write(manager.registerCheck(user_id)+"");
	}

}
