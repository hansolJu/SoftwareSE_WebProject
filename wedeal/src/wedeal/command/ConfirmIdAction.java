package wedeal.command;
/**
 * 코드설명
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.15
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.UserDBBean;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");		
		String user_id = request.getParameter("user_id");
		//주어진 id의 중복여부를 체크해 값을 반환.
		UserDBBean manager = UserDBBean.getinstance();
		int check= manager.registerCheck(user_id);
		
		request.setAttribute("check", new Integer(check));
		return "/member/confirmId.jsp";
	}
}