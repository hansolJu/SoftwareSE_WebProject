package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class userDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/se?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//login
	public int login(String user_id,String user_pw) {
		String SQL = "SELECT user_pw FROM USER WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(user_pw)) 
					return 1; //로그인 성공
			}
			return 0; //아이디&비밀번호 틀림
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //오류
	}
	
	//check id(join)
	public int registerCheck(String user_id) {
		String SQL="SELECT * FROM USER WHERE user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			//no
			if(rs.next() || user_id.equals("")) {
				return 0;
			}
			//ok
			else{
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//error
	}
	
	//add user
	public int register(String user_name,String user_age,String user_phone,String user_id,String user_pw,String user_hope) {
		String SQL="INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_age);
			pstmt.setString(3, user_phone);
			pstmt.setString(4, user_id);
			pstmt.setString(5, user_pw);
			pstmt.setString(6, user_hope);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;//error
	}
	

}
