package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String user_id = request.getParameter("user_id");
		String user_pw  = request.getParameter("user_pw");

		//사용자가 입력한 id, passwd를 가지고 인증 체크 후 값 반환
		UserDBBean manager = UserDBBean.getinstance();
		int check= manager.login(user_id,user_pw);
		
		request.setAttribute("user_id", user_id);
		request.setAttribute("check", new Integer(check));
		return "/member/loginPro.jsp";
	}
}