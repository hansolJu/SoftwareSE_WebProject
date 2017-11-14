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
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
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
		// TODO Auto-generated method stub
		requestPro(request, response);//요청처리 메소드 호출
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);//요청처리 메소드 호출
	}
	
	//웹브라우저의 요청을 분석하고, 해당 로직의 처리를 할 모델 실행 및
	//처리 결과를 뷰에 보냄
	private void requestPro(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String view = null;
		CommandAction com=null;
		try {
			String command = request.getRequestURI();
			System.out.println(command);
	        if(command.indexOf(request.getContextPath()) == 0) 
	           command = command.substring(request.getContextPath().length());
	        System.out.println(command);
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
