package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wedeal.util.DbUtil;

public class userDAO {

	private Connection conn;
	//check id
	public int registerCheck(String user_id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String SQL="SELECT * FROM USER WHERE user_id = ?";
		
		try {
			conn = DbUtil.getConnection();
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
			conn = DbUtil.getConnection();
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
	public userDTO getUserByID(String user_id) {
        userDTO user = new userDTO();
        PreparedStatement preparedStatement = null;
    	ResultSet  rs = null;
        try {
        	conn = DbUtil.getConnection();
            preparedStatement = conn.prepareStatement("select * from User where user_id=?");
            preparedStatement.setString(1, user_id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUser_name(rs.getString("user_age"));
                user.setUser_age(rs.getString("user_age"));
                user.setUser_phone(rs.getString("user_phone"));
                user.setUser_id(rs.getString("user_id"));
                user.setUser_pw(rs.getString("user_pw"));
                user.setUser_hope(rs.getString("user_hope_1"));
                user.setUser_date(rs.getDate("user_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        	DbUtil.close(conn, preparedStatement, rs);
        }
        return user;
    }
    /**
     * 
     * @param userName
     * @return User
     * 관리자가 회원을 조회하는 메소드
     */
    public userDTO getUserByName(String userName) {
        userDTO user = new userDTO();
        PreparedStatement preparedStatement = null;
    	ResultSet  rs = null;
        try {
        	conn = DbUtil.getConnection();
            preparedStatement = conn.prepareStatement("select * from User where user_name=?");
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUser_name(rs.getString("user_name"));
                user.setUser_age(rs.getString("user_age"));
                user.setUser_phone(rs.getString("user_phone"));
                user.setUser_id(rs.getString("user_id"));
                user.setUser_pw(rs.getString("user_pw"));
                user.setUser_hope(rs.getString("user_hope"));
                user.setUser_date(rs.getDate("user_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        	DbUtil.close(conn, preparedStatement, rs);
        }
        return user;
    }
}
