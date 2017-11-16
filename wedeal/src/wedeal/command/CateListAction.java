/**
 * 사용자에게 카테고리를 보여주기 위해서 DataBean에서 불러오는 함수
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.17
 */
package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import wedeal.bean.CateDBBean;
import wedeal.bean.CateDataBean;

public class CateListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
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
		return "/board/cateList.jsp";
	}
}