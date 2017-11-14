package wedeal.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.BuyDBBean;
import wedeal.bean.BuyDataBean;

public class BuyListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		
		List<BuyDataBean> buyLists = null;
		int count = 0;
		
		//해당 buyer의 구매목록의 수를 얻어냄
		BuyDBBean buyProcess = BuyDBBean.getInstance();
		count = buyProcess.getListCount(buyer);

		if(count > 0){//구매 목록이 있으면 수행
			//해당 buyer의 구매목록을 얻어냄
			buyLists = buyProcess.getBuyList(buyer);
			request.setAttribute("buyLists",buyLists);
		}
	
		request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(1));
		return "/buy/buyList.jsp";
	}
}