package wedeal.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;
import wedeal.bean.UserDataBean;


@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterAction() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDBBean manager = UserDBBean.getinstance();
		UserDataBean user = new UserDataBean(); 
		//회원가입 정보
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_age(Integer.parseInt(request.getParameter("user_age")));
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_pw(request.getParameter("user_pw"));
		String check_passwd=request.getParameter("check_passwd");
		user.setUser_hope(request.getParameter("user_hope"));
		
		if(user.getUser_name() == null || user.getUser_name().equals("") || user.getUser_age() == 0 || user.getUser_phone() == null || user.getUser_phone().equals("") || user.getUser_id() == null || user.getUser_id().equals("") || user.getUser_pw() == null || user.getUser_pw().equals("") || user.getUser_hope() == null || user.getUser_hope().equals("") || check_passwd == null || check_passwd.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("join.jsp");
			return;
		}
		
		if(!user.getUser_pw().equals(check_passwd)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			response.sendRedirect("join.jsp");
			return;
		}
		
		int result = manager.register(user);
		
		if(result ==1) 
			response.sendRedirect("index.jsp");
		
		else {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
			response.sendRedirect("join.jsp");
		}
	}

}
