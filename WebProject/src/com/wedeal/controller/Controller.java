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

<<<<<<< HEAD
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
	//¸í·É¾î¿Í ¸í·É¾î Ã³¸® Å¬·¡½º¸¦ ½ÖÀ¸·Î ÀúÀå
=======
/**
 * Servlet implementation class Controller
 * 
 * ì»¨íŠ¸ë¡¤ëŸ¬ì¸ Controller íŒŒì¼ì€ ì‚¬ìš©ìì˜ ìš”ì²­ì„ ë°›ì•„ ìš”ì²­ì— í•´ë‹¹í•˜ëŠ” ë¡œì§ì˜ ì§„ì…ì  ìŠˆí¼ ì¸í„°í˜ì´ìŠ¤ì¸ CommandAction í´ë˜ìŠ¤ì˜ ë©”ì†Œë“œë¥¼ í˜¸ì¶œí•œë‹¤. 
 * ê·¸ëŸ¬ë©´ ìŠˆí¼ ì¸í„°í˜ì´ìŠ¤ë¥¼ í†µí•´ì„œ í•´ë‹¹ ì‘ì—…ì„ ì²˜ë¦¬í•  ëª…ë ¹ì–´ ì²˜ë¦¬ í´ë˜ìŠ¤ì˜ requestPro(request, response) ë©”ì†Œë“œê°€ í˜¸ì¶œë˜ì–´ ì‘ì—…ì„ ì²˜ë¦¬í•œë‹¤.
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
	//ëª…ë ¹ì–´ì™€ ëª…ë ¹ì–´ ì²˜ë¦¬ í´ë˜ìŠ¤ë¥¼ ìŒìœ¼ë¡œ ì €ì¥
>>>>>>> master
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
<<<<<<< HEAD
    //¸í·É¾î¿Í Ã³¸®Å¬·¡½º°¡ ¸ÅÇÎµÇ¾î ÀÖ´Â properties ÆÄÀÏÀ» ÀĞ¾î¼­ 
    //HashMap°´Ã¼ÀÎ commandMap¿¡ ÀúÀå
	public void init(ServletConfig config) throws ServletException {
		System.out.println("1");
		//initParams¿¡¼­ propertyConfigÀÇ °ªÀ» ÀĞ¾î¿È
		String props = config.getInitParameter("propertyConfig");
		System.out.println("2");
		String realFolder = "/property"; //propertiesÆÄÀÏÀÌ ÀúÀåµÈ Æú´õ
		//À¥¾îÇÃ¸®ÄÉÀÌ¼Ç ·çÆ® °æ·Î
		ServletContext context = config.getServletContext();
		System.out.println("3");
		//realFolder¸¦ À¥¾îÇÃ¸®ÄÉÀÌ¼Ç ½Ã½ºÅÛ»óÀÇ Àı´ë°æ·Î·Î º¯°æ
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//¸í·É¾î¿Í Ã³¸®Å¬·¡½ºÀÇ ¸ÅÇÎÁ¤º¸¸¦ ÀúÀåÇÒ Properties°´Ã¼ »ı¼º
		Properties pr = new Properties();
		System.out.println("4");
		FileInputStream f = null;
		try{
			//command.propertiesÆÄÀÏÀÇ ³»¿ëÀ» ÀĞ¾î¿È
			f = new FileInputStream(realPath); 
			//command.propertiesÀÇ ³»¿ëÀ» Properties°´Ã¼ pr¿¡ ÀúÀå
			System.out.println("5");
			pr.load(f);
			
			System.out.println("6");
=======
    //ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ë˜ìŠ¤ê°€ ë§¤í•‘ë˜ì–´ ìˆëŠ” properties íŒŒì¼ì„ ì½ì–´ì„œ 
    //HashMapê°ì²´ì¸ commandMapì— ì €ì¥
	public void init(ServletConfig config) throws ServletException {
		
		//initParamsì—ì„œ propertyConfigì˜ ê°’ì„ ì½ì–´ì˜´
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; //propertiesíŒŒì¼ì´ ì €ì¥ëœ í´ë”
		//ì›¹ì–´í”Œë¦¬ì¼€ì´ì…˜ ë£¨íŠ¸ ê²½ë¡œ
		ServletContext context = config.getServletContext();
		//realFolderë¥¼ ì›¹ì–´í”Œë¦¬ì¼€ì´ì…˜ ì‹œìŠ¤í…œìƒì˜ ì ˆëŒ€ê²½ë¡œë¡œ ë³€ê²½
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ë˜ìŠ¤ì˜ ë§¤í•‘ì •ë³´ë¥¼ ì €ì¥í•  Propertiesê°ì²´ ìƒì„±
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.propertiesíŒŒì¼ì˜ ë‚´ìš©ì„ ì½ì–´ì˜´
			f = new FileInputStream(realPath); 
			//command.propertiesì˜ ë‚´ìš©ì„ Propertiesê°ì²´ prì— ì €ì¥
			pr.load(f);
>>>>>>> master
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
<<<<<<< HEAD
		//Set°´Ã¼ÀÇ iterator()¸Ş¼Òµå¸¦ »ç¿ëÇØ Iterator°´Ã¼¸¦ ¾ò¾î³¿
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator°´Ã¼¿¡ ÀúÀåµÈ ¸í·É¾î¿Í Ã³¸®Å¬·¡½º¸¦ commandMap¿¡ ÀúÀå
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			System.out.println(command);
			String className = pr.getProperty(command);
			System.out.println(className);
			try{
				Class<?> commandClass = Class.forName(className);//ÇØ´ç ¹®ÀÚ¿­À» Å¬·¡½º·Î ¸¸µç´Ù.
				System.out.println("7");
			    Object commandInstance = commandClass.newInstance();//ÇØ´çÅ¬·¡½ºÀÇ °´Ã¼¸¦ »ı¼º
			    System.out.println("8");
			    commandMap.put(command, commandInstance);// Map°´Ã¼ÀÎ commandMap¿¡ °´Ã¼ ÀúÀå
				System.out.println("9");
=======
		//Setê°ì²´ì˜ iterator()ë©”ì†Œë“œë¥¼ ì‚¬ìš©í•´ Iteratorê°ì²´ë¥¼ ì–»ì–´ëƒ„
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iteratorê°ì²´ì— ì €ì¥ëœ ëª…ë ¹ì–´ì™€ ì²˜ë¦¬í´ë˜ìŠ¤ë¥¼ commandMapì— ì €ì¥
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
>>>>>>> master
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
<<<<<<< HEAD
		// TODO Auto-generated method stub
		requestPro(request, response);//¿äÃ»Ã³¸® ¸Ş¼Òµå È£Ãâ
=======
		requestPro(request, response);//ìš”ì²­ì²˜ë¦¬ ë©”ì†Œë“œ í˜¸ì¶œ
>>>>>>> master
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		requestPro(request, response);//¿äÃ»Ã³¸® ¸Ş¼Òµå È£Ãâ
	}
	
	//À¥ºê¶ó¿ìÀúÀÇ ¿äÃ»À» ºĞ¼®ÇÏ°í, ÇØ´ç ·ÎÁ÷ÀÇ Ã³¸®¸¦ ÇÒ ¸ğµ¨ ½ÇÇà ¹×
	//Ã³¸® °á°ú¸¦ ºä¿¡ º¸³¿
=======
		requestPro(request, response);//ìš”ì²­ì²˜ë¦¬ ë©”ì†Œë“œ í˜¸ì¶œ
	}
	
	//ì›¹ë¸Œë¼ìš°ì €ì˜ ìš”ì²­ì„ ë¶„ì„í•˜ê³ , í•´ë‹¹ ë¡œì§ì˜ ì²˜ë¦¬ë¥¼ í•  ëª¨ë¸ ì‹¤í–‰ ë°
	//ì²˜ë¦¬ ê²°ê³¼ë¥¼ ë·°ì— ë³´ëƒ„
>>>>>>> master
	private void requestPro(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String view = null;
		CommandAction com=null;
		try {
			String command = request.getRequestURI();
<<<<<<< HEAD
			System.out.println(command);
	        if(command.indexOf(request.getContextPath()) == 0) 
	           command = command.substring(request.getContextPath().length());
	        System.out.println(command);
=======
	        if(command.indexOf(request.getContextPath()) == 0) 
	           command = command.substring(request.getContextPath().length());
>>>>>>> master
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
<<<<<<< HEAD
}
=======
}
>>>>>>> master
