package wedeal.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;


@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		UserDBBean userdb = UserDBBean.getinstance();
		//비어있을 경우
		if( user_id == null || user_id.equals("") || user_pw == null || user_pw.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("login.jsp");
			return;
		}
		
		int result=userdb.login(user_id, user_pw);
		
		if(result == 1) {
			//로그인 성공시 id 세션 부여
			request.getSession().setAttribute("user_id",user_id);
			response.sendRedirect("index.jsp"); //complete page로 redirect해야함
			
			return;
		}
		
		else if(result == 0) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "아이디 혹은 비밀번호가 맞지 않습니다.");
			response.sendRedirect("login.jsp");
		}
		
		else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "내부적인 오류입니다. 다시 시도해 주세요.");
			response.sendRedirect("login.jsp");
		}
	}

}
