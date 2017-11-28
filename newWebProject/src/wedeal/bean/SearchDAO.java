package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchDAO {
	private Connection conn = null;
	
	private static SearchDAO instance = new SearchDAO();
	
	public static SearchDAO getinstance() {
		return instance;
	}
	
	public SearchDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/wedeal?autoReconnect=true&useSSL=false";
			String dbID = "root";
			String dbPW = "ekgus2";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
     
    public String searchId(String user_name, String user_phone) throws SQLException {
    	PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT user_id FROM user WHERE user_name = ? AND user_phone = ? ";
        try {
        	pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, user_name);
        	pstmt.setString(2, user_phone);
        	rs = pstmt.executeQuery();
        	if( rs.next() )
        		return (rs.getString("user_id"));
        	else
        		return null;
        	} finally {
        		if(rs!=null)try { rs.close(); } catch(SQLException ex) {}
        		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        		}

   
    }
    public String searchPw(String user_id, String user_phone) throws SQLException {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        String SQLL = "SELECT user_pw FROM user WHERE user_id = ? AND user_phone = ? ";

    	try {
    		pstmt = conn.prepareStatement(SQLL);
    		pstmt.setString(1, user_id);
    		pstmt.setString(2, user_phone);
    		rs = pstmt.executeQuery();
    		if( rs.next() )
    			return (rs.getString("user_pw"));
    		else
    			return null;
    		} finally {
    			if(rs!=null)try { rs.close(); } catch(SQLException ex) {}
    			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
    			}

    }
}
