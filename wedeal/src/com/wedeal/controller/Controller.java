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
	//명령?��?? 명령?�� 처리 ?��?��?���? ?��?���? ???��
	private Map<String, Object> commandMap = new HashMap<String, Object>();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    //명령?��?? 처리?��?��?���? 매핑?��?�� ?��?�� properties ?��?��?�� ?��?��?�� 
    //HashMap객체?�� commandMap?�� ???��
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("컨트롤러 초기?��~");
		//initParams?��?�� propertyConfig?�� 값을 ?��?��?��
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; //properties?��?��?�� ???��?�� ?��?��
		//?��?��?��리�??��?�� 루트 경로
		ServletContext context = config.getServletContext();
		//realFolder�? ?��?��?��리�??��?�� ?��?��?��?��?�� ?��??경로�? �?�?
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//명령?��?? 처리?��?��?��?�� 매핑?��보�?? ???��?�� Properties객체 ?��?��
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//command.properties?��?��?�� ?��?��?�� ?��?��?��
			f = new FileInputStream(realPath); 
			//command.properties?�� ?��?��?�� Properties객체 pr?�� ???��
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Set객체?�� iterator()메소?���? ?��?��?�� Iterator객체�? ?��?��?��
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator객체?�� ???��?�� 명령?��?? 처리?��?��?���? commandMap?�� ???��
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
		// TODO Auto-generated method stub
		requestPro(request, response);//?���?처리 메소?�� ?���?
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);//?���?처리 메소?�� ?���?
	}
	
	//?��브라?��???�� ?���??�� 분석?���?, ?��?�� 로직?�� 처리�? ?�� 모델 ?��?�� �?
	//처리 결과�? 뷰에 보냄
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