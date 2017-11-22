package wedeal.control;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import wedeal.bean.CateDataBean;
import wedeal.bean.MngrDBBean;

/**
 * Servlet implementation class MngrMenuListAction
 */
@WebServlet("/MngrMenuAction")
public class MngrMenuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_BORAD = "/mngr/menu/boardList.jsp";
	private static String INSERT_OR_EDIT = "/mngr/menu/board.jsp";
	private static String savePath = "C:\\workspace\\my\\savefile";
	private static int maxSize = 5 * 1024 * 1024;
	private MngrDBBean dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MngrMenuAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 삽입, 편집외 모든기능을 맡아서 한다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {//삭제
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			dao.deleteBoard(boardId);
			forward = LIST_BORAD;
			request.setAttribute("borads", dao.getAllBoard());
		} else if (action.equalsIgnoreCase("edit")) {//수정
			forward = INSERT_OR_EDIT;
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			CateDataBean board = dao.getBoardById(boardId);
			request.setAttribute("board", board);
		} else if (action.equalsIgnoreCase("listUser")) {//출력
			forward = LIST_BORAD;
			request.setAttribute("borads", dao.getAllBoard());
		} else {//삽입
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 추가또는 편집을 맡아서 한다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	MultipartRequest multipartrequest = new MultipartRequest(request, savePath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());
    	CateDataBean cateDataBean = new CateDataBean();
    	String fileName = "";
    	File file = null;
    	Enumeration files = null;
    	    	
    	cateDataBean.setCate_name(multipartrequest.getParameter("boardName"));//게시판이름
    	cateDataBean.setUpCategoryName(multipartrequest.getParameter("upBoardName"));//상위게시판 이름
        cateDataBean.setAdminName(multipartrequest.getParameter("adminName"));
        
		try {
			fileName = multipartrequest.getFilesystemName("fileName"); // 파일의 이름 얻기
			
			files = multipartrequest.getFileNames();//?
			String name = (String) files.nextElement();
			file = multipartrequest.getFile(name);

			cateDataBean.setSavePath(savePath);
			cateDataBean.setFileName(fileName);

			if (fileName == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일 업로드 되지 않았음");
			} else { // 파일이 업로드 되었을때
				System.out.println("File Name  : " + fileName);
				System.out.println("Save Path : " + savePath);
			} // else
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
		} // catch
           
		String boardId = multipartrequest.getParameter("boardId");
        if(boardId == null || boardId.isEmpty())
        {
            dao.addBoard(cateDataBean);//게시판 생성
        }
        else
        {
        	cateDataBean.setCate_num(Integer.parseInt(boardId));
            dao.updateBoard(cateDataBean);//게시판 수정
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_BORAD);
        request.setAttribute("borads", dao.getAllBoard());
        view.forward(request, response);
    }

}
