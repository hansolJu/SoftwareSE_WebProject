package wedeal.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wedeal.bean.DeclarationDBBean;
import wedeal.bean.DeclarationDataBean;


@WebServlet(value = "/DeclarationAction", name = "DeclarationAction")
public class DeclarationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeclarationAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		DeclarationDataBean declarationdt = new DeclarationDataBean();
		DeclarationDBBean declaration = DeclarationDBBean.getinstance();
		
		declarationdt.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		declarationdt.setUser_id(request.getParameter("user_id"));
		
		if(declaration.check_id(declarationdt) == -1) {
			int result = declaration.declaration(declarationdt);
		
			if(result == -1) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "내부적인 오류입니다.");
				RequestDispatcher view = request.getRequestDispatcher("user/view.jsp");
				request.setAttribute("board_num", declarationdt.getBoard_num());
				view.forward(request, response);
				return;
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("user/view.jsp");
				request.setAttribute("board_num", declarationdt.getBoard_num());
				view.forward(request, response);
				return;
		}
	}
		else {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "이미 신고하셨습니다.");
				RequestDispatcher view = request.getRequestDispatcher("user/view.jsp");
				request.setAttribute("board_num", declarationdt.getBoard_num());
				view.forward(request, response);
				return;
		}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}