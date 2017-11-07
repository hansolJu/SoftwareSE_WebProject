package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wedeal.dao.DataDBBean;
import com.wedeal.util.DbUtil;

public class userDAO {

	private Connection conn;
	private static DataDBBean instance = new DataDBBean();
	
	public userDAO() {
		conn = DbUtil.getConnection();
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
	public int register(String user_name,String user_age,String user_phone,String user_id,String user_pw,String user_hope) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
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
