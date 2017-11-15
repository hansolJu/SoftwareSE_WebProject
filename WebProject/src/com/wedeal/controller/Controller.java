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
	//명령어와 명령어 처리 클래스를 쌍으로 저장
=======
/**
 * Servlet implementation class Controller
 * 
 * 而⑦듃濡ㅻ윭�씤 Controller �뙆�씪��� �궗�슜�옄�쓽 �슂泥��쓣 諛쏆븘 �슂泥��뿉 �빐�떦�븯�뒗 濡쒖쭅�쓽 吏꾩엯�젏 �뒋�띁 �씤�꽣�럹�씠�뒪�씤 CommandAction �겢�옒�뒪�쓽 硫붿냼�뱶瑜� �샇異쒗븳�떎. 
 * 洹몃윭硫� �뒋�띁 �씤�꽣�럹�씠�뒪瑜� �넻�빐�꽌 �빐�떦 �옉�뾽�쓣 泥섎━�븷 紐낅졊�뼱 泥섎━ �겢�옒�뒪�쓽 requestPro(request, response) 硫붿냼�뱶媛� �샇異쒕릺�뼱 �옉�뾽�쓣 泥섎━�븳�떎.
 * 泥섎━寃곌낵��� 寃곌낵瑜� �몴�떆�븷 酉곗뿉 ����븳 �젙蹂대뒗 �떎�떆 而⑦듃濡ㅻ윭濡� 蹂대궡吏꾨떎. 而⑦듃濡ㅻ윭媛� �씠 �젙蹂대�� Template.jsp濡� 蹂대궡硫� �솕硫댁뿉 寃곌낵媛� �몴�떆�맂�떎.
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
	//紐낅졊�뼱��� 紐낅졊�뼱 泥섎━ �겢�옒�뒪瑜� �뙇�쑝濡� ����옣
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
    //명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 
    //HashMap객체인 commandMap에 저장
	public void init(ServletConfig config) throws ServletException {
		System.out.println("1");
		//initParams에서 propertyConfig의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		System.out.println("2");
		String realFolder = "/property"; //properties파일이 저장된 폴더
		//웹어플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		System.out.println("3");
		//realFolder를 웹어플리케이션 시스템상의 절대경로로 변경
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//명령어와 처리클래스의 매핑정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		System.out.println("4");
		FileInputStream f = null;
		try{
			//command.properties파일의 내용을 읽어옴
			f = new FileInputStream(realPath); 
			//command.properties의 내용을 Properties객체 pr에 저장
			System.out.println("5");
			pr.load(f);
			
			System.out.println("6");
=======
    //紐낅졊�뼱��� 泥섎━�겢�옒�뒪媛� 留ㅽ븨�릺�뼱 �엳�뒗 properties �뙆�씪�쓣 �씫�뼱�꽌 
    //HashMap媛앹껜�씤 commandMap�뿉 ����옣
	public void init(ServletConfig config) throws ServletException {
		
		//initParams�뿉�꽌 propertyConfig�쓽 媛믪쓣 �씫�뼱�샂
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; //properties�뙆�씪�씠 ����옣�맂 �뤃�뜑
		//�쎒�뼱�뵆由ъ���씠�뀡 猷⑦듃 寃쎈줈
		ServletContext context = config.getServletContext();
		//realFolder瑜� �쎒�뼱�뵆由ъ���씠�뀡 �떆�뒪�뀥�긽�쓽 �젅���寃쎈줈濡� 蹂�寃�
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//紐낅졊�뼱��� 泥섎━�겢�옒�뒪�쓽 留ㅽ븨�젙蹂대�� ����옣�븷 Properties媛앹껜 �깮�꽦
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.properties�뙆�씪�쓽 �궡�슜�쓣 �씫�뼱�샂
			f = new FileInputStream(realPath); 
			//command.properties�쓽 �궡�슜�쓣 Properties媛앹껜 pr�뿉 ����옣
			pr.load(f);
>>>>>>> master
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
<<<<<<< HEAD
		//Set객체의 iterator()메소드를 사용해 Iterator객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator객체에 저장된 명령어와 처리클래스를 commandMap에 저장
		while( keyIter.hasNext() ) {
			String command = (String)keyIter.next();
			System.out.println(command);
			String className = pr.getProperty(command);
			System.out.println(className);
			try{
				Class<?> commandClass = Class.forName(className);//해당 문자열을 클래스로 만든다.
				System.out.println("7");
			    Object commandInstance = commandClass.newInstance();//해당클래스의 객체를 생성
			    System.out.println("8");
			    commandMap.put(command, commandInstance);// Map객체인 commandMap에 객체 저장
				System.out.println("9");
=======
		//Set媛앹껜�쓽 iterator()硫붿냼�뱶瑜� �궗�슜�빐 Iterator媛앹껜瑜� �뼸�뼱�깂
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator媛앹껜�뿉 ����옣�맂 紐낅졊�뼱��� 泥섎━�겢�옒�뒪瑜� commandMap�뿉 ����옣
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
		requestPro(request, response);//요청처리 메소드 호출
=======
		requestPro(request, response);//�슂泥�泥섎━ 硫붿냼�뱶 �샇異�
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
		requestPro(request, response);//요청처리 메소드 호출
	}
	
	//웹브라우저의 요청을 분석하고, 해당 로직의 처리를 할 모델 실행 및
	//처리 결과를 뷰에 보냄
=======
		requestPro(request, response);//�슂泥�泥섎━ 硫붿냼�뱶 �샇異�
	}
	
	//�쎒釉뚮씪�슦����쓽 �슂泥��쓣 遺꾩꽍�븯怨�, �빐�떦 濡쒖쭅�쓽 泥섎━瑜� �븷 紐⑤뜽 �떎�뻾 諛�
	//泥섎━ 寃곌낵瑜� 酉곗뿉 蹂대깂
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
