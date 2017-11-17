/**
 * 카테고리를 추가해주는 action
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.17
 */
package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CateDBBean;

public class AddCateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
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
		return "/cart/cartUpdatePro.jsp";
	}
}