package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/user?autoReconnect=true&useSSL=false";
			String dbID = "blesk";
			String dbPW = "6572609";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Login
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
	
	//get user info
	public userDTO getUser(String user_id) {
		String sql = "select * from user where user_id=?";
		userDTO user = new userDTO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			user.setUser_name(rs.getString("user_name"));
			user.setUser_age(rs.getString("user_age"));
			user.setUser_phone(rs.getString("user_phone"));
			user.setUser_id(rs.getString("user_id"));
			user.setUser_pw(rs.getString("user_pw"));
			user.setUser_hope(rs.getString("user_hope"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	//user_phone modify
	public boolean modifyPhone(String user_id, String user_phone) {
		String sql = "update user set user_phone=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_phone);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	//user_pw modify
	public boolean modifyPasswd(String user_id, String user_pw) {
		String sql = "update user set user_pw=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	//user delete
	public boolean deleteUser(String user_id) {
		String sql = "delete from user where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
