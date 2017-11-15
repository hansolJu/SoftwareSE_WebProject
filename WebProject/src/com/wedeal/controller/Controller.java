package com.wedeal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedeal.command.CommandAction;

/**
 * Servlet implementation class Controller
 * 
 * ì»¨íŠ¸ë¡¤ëŸ¬ì¸ Controller íŒŒì¼ì€ ì‚¬ìš©ìžì˜ ìš”ì²­ì„ ë°›ì•„ ìš”ì²­ì— í•´ë‹¹í•˜ëŠ” ë¡œì§ì˜ ì§„ìž…ì  ìŠˆí¼ ì¸í„°íŽ˜ì´ìŠ¤ì¸ CommandAction í´ëž˜ìŠ¤ì˜ ë©”ì†Œë“œë¥¼ í˜¸ì¶œí•œë‹¤. 
 * ê·¸ëŸ¬ë©´ ìŠˆí¼ ì¸í„°íŽ˜ì´ìŠ¤ë¥¼ í†µí•´ì„œ í•´ë‹¹ ìž‘ì—…ì„ ì²˜ë¦¬í•  ëª…ë ¹ì–´ ì²˜ë¦¬ í´ëž˜ìŠ¤ì˜ requestPro(request, response) ë©”ì†Œë“œê°€ í˜¸ì¶œë˜ì–´ ìž‘ì—…ì„ ì²˜ë¦¬í•œë‹¤.
 * ì²˜ë¦¬ê²°ê³¼ì™€ ê²°ê³¼ë¥¼ í‘œì‹œí•  ë·°ì— ëŒ€í•œ ì •ë³´ëŠ” ë‹¤ì‹œ ì»¨íŠ¸ë¡¤ëŸ¬ë¡œ ë³´ë‚´ì§„ë‹¤. ì»¨íŠ¸ë¡¤ëŸ¬ê°€ ì´ ì •ë³´ë¥¼ Template.jspë¡œ ë³´ë‚´ë©´ í™”ë©´ì— ê²°ê³¼ê°€ í‘œì‹œëœë‹¤.
 * 
 * The Controller file, which is the controller, receives the user's request and calls the method of the CommandAction class which is the entry point super interface of the logic corresponding to the request.
 * Then, the requestPro (request, response) method of the command processing class to process the job through the super interface is called to process the job.
 * Information about the processing result and the view to display the result is sent back to the controller. When the controller sends this information to Template.jsp, the results are displayed on the screen.
 */
@WebServlet(
	urlPatterns = { 
		"/Controller", 
		"*.do"
	}, 
	initParams = { 
	    @WebInitParam(name = "propertyConfig", value = "commandMapping.properties")
	})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ëª…ë ¹ì–´ì™€ ëª…ë ¹ì–´ ì²˜ë¦¬ í´ëž˜ìŠ¤ë¥¼ ìŒìœ¼ë¡œ ì €ìž¥
	private Map<String, Object> commandMap = new HashMap<String, Object>();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    //ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ëž˜ìŠ¤ê°€ ë§¤í•‘ë˜ì–´ ìžˆëŠ” properties íŒŒì¼ì„ ì½ì–´ì„œ 
    //HashMapê°ì²´ì¸ commandMapì— ì €ìž¥
	public void init(ServletConfig config) throws ServletException {
		
		//initParamsì—ì„œ propertyConfigì˜ ê°’ì„ ì½ì–´ì˜´
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; //propertiesíŒŒì¼ì´ ì €ìž¥ëœ í´ë”
		//ì›¹ì–´í”Œë¦¬ì¼€ì´ì…˜ ë£¨íŠ¸ ê²½ë¡œ
		ServletContext context = config.getServletContext();
		//realFolderë¥¼ ì›¹ì–´í”Œë¦¬ì¼€ì´ì…˜ ì‹œìŠ¤í…œìƒì˜ ì ˆëŒ€ê²½ë¡œë¡œ ë³€ê²½
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ëž˜ìŠ¤ì˜ ë§¤í•‘ì •ë³´ë¥¼ ì €ìž¥í•  Propertiesê°ì²´ ìƒì„±
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.propertiesíŒŒì¼ì˜ ë‚´ìš©ì„ ì½ì–´ì˜´
			f = new FileInputStream(realPath); 
			//command.propertiesì˜ ë‚´ìš©ì„ Propertiesê°ì²´ prì— ì €ìž¥
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Setê°ì²´ì˜ iterator()ë©”ì†Œë“œë¥¼ ì‚¬ìš©í•´ Iteratorê°ì²´ë¥¼ ì–»ì–´ëƒ„
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iteratorê°ì²´ì— ì €ìž¥ëœ ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ëž˜ìŠ¤ë¥¼ commandMapì— ì €ìž¥
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		requestPro(request, response);//ìš”ì²­ì²˜ë¦¬ ë©”ì†Œë“œ í˜¸ì¶œ
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		requestPro(request, response);//ìš”ì²­ì²˜ë¦¬ ë©”ì†Œë“œ í˜¸ì¶œ
	}
	
	//ì›¹ë¸Œë¼ìš°ì €ì˜ ìš”ì²­ì„ ë¶„ì„í•˜ê³ , í•´ë‹¹ ë¡œì§ì˜ ì²˜ë¦¬ë¥¼ í•  ëª¨ë¸ ì‹¤í–‰ ë°
	//ì²˜ë¦¬ ê²°ê³¼ë¥¼ ë·°ì— ë³´ëƒ„

	private void requestPro(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String view = null;
		CommandAction com=null;
		try {
			String command = request.getRequestURI();

	        if(command.indexOf(request.getContextPath()) == 0) 
	           command = command.substring(request.getContextPath().length());

	        com = (CommandAction)commandMap.get(command);  
	        view = com.requestPro(request, response);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		request.setAttribute("cont",view);
	    RequestDispatcher dispatcher = 
	       request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
}

