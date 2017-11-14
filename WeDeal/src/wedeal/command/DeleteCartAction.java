package wedeal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.CartDBBean;

public class DeleteCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String list = request.getParameter("list");
		String msg = "";
		
		CartDBBean bookProcess = CartDBBean.getInstance();
		
		if(list.equals("all")){//list값이 "all"이면 수행
			//해당 buyer의 장바구니 목록을 모두삭제
			String buyer = request.getParameter("buyer");
			bookProcess.deleteAll(buyer);
			msg = "장바구니가 모두 비워졌습니다.";
		}else{//list값이 "all"이외(cart_id)의 값이면 수행
			//list값(cart_id)에 해당하는 레코드 삭제
			bookProcess.deleteList(Integer.parseInt(list));
			msg = "지정한 항목이 삭제되었습니다..";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("type", new Integer(1));
		return "/cart/deleteCart.jsp";
	}

}
