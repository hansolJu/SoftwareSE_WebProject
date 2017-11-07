package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_name = request.getParameter("user_name");
		String user_age=request.getParameter("user_age");
		String user_phone=request.getParameter("user_phone");
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String check_passwd=request.getParameter("check_passwd");
		String user_hope=request.getParameter("user_hope");
		
		if(user_name == null || user_name.equals("") || user_age == null || user_age.equals("") || user_phone == null || user_phone.equals("") || user_id == null || user_id.equals("") || user_pw == null || user_pw.equals("") || user_hope == null || user_hope.equals("") || check_passwd == null || check_passwd.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("join.jsp");
			return;
		}
		
		if(!user_pw.equals(check_passwd)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			response.sendRedirect("join.jsp");
			
			return;
		}
		
		int result = new userDAO().register(user_name,user_age,user_phone,user_id,user_pw,user_hope);
		
		if(result ==1) 
			response.sendRedirect("index.jsp");
		
		else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
			response.sendRedirect("join.jsp");
		}
	}

}
