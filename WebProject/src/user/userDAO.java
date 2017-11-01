package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {

	private Connection conn;
	
	public userDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/se";
			String dbID = "jy";
			String dpPassword = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dpPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//check id
	public int registerCheck(String user_id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String SQL="SELECT * FROM USER WHERE user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			//no
			if(rs.next()) {
				return 0;
			}
			//ok
			else {
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
	public int register(userDTO user) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String SQL="INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_age());
			pstmt.setString(3, user.getUser_phone());
			pstmt.setString(4, user.getUser_id());
			pstmt.setString(5, user.getUser_pw());
			pstmt.setString(6, user.getUser_hope());
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
