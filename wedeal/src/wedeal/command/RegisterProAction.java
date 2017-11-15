/**
 * 코드설명
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.15
 */
package wedeal.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;
import wedeal.bean.UserDataBean;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//회원가입 정보
		UserDataBean member = new UserDataBean();
		member.setUser_name(request.getParameter("user_name"));
		member.setUser_age(Integer.parseInt(request.getParameter("user_age")));
		member.setUser_phone(request.getParameter("user_phone"));
		member.setUser_id(request.getParameter("user_phone"));
		member.setUser_pw(request.getParameter("user_pw"));
		member.setUser_hope(request.getParameter("user_hope"));
        
		//연결확인부분
		System.out.println(member.getUser_name());
		System.out.println(member.getUser_age());
		System.out.println(member.getUser_phone());
		System.out.println(member.getUser_id());
		System.out.println(member.getUser_pw());
		System.out.println(member.getUser_hope());
		//회원가입처리
		UserDBBean dbPro = UserDBBean.getinstance();
		dbPro.register(member);
		
		return "/member/registerPro.jsp";
	}

}
