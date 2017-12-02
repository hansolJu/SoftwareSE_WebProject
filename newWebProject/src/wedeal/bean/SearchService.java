package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SearchService {

	private static SearchService  instance = new SearchService ();
	
	public static SearchService  getInstance() {
		return instance;
	 }

	private SearchService () {
		
	}
 
	public String searchId(String user_name, String user_phone) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wedeal","root","ekgus2");
            //DAO 객체를 생성 시 Connection 전달 
			SearchDAO searchDAO = new SearchDAO();
            String user_id = searchDAO.searchId(user_name, user_phone);
            //    System.out.println(name);
            return (user_id);
            } 
		finally {
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		}
	public String  searchPw(String user_id, String user_phone) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wedeal","root","ekgus2");
            //DAO 객체를 생성 시 Connection 전달 
            SearchDAO SearchDAO = new SearchDAO();
   
            String user_pw = SearchDAO.searchPw(user_id, user_phone);
            //    System.out.println(name);
            return (user_pw);
            } 
		finally {
			if (conn != null) try { conn.close(); } catch(SQLException ex) {};
			}
		}
	}
