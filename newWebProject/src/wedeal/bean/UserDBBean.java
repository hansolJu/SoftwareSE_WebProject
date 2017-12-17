package wedeal.bean;
/**
 * 코드설명
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.15
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static UserDBBean instance = new UserDBBean();

	public static UserDBBean getinstance() {
		return instance;
	}
	
	private UserDBBean() {
		try {
			String dbURL = "jdbc:mysql://203.249.22.34:3306/se?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp:/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/se");
		return ds.getConnection();
	}*/

	//���� �ð��� ������ �־��ش�.
	public String getDate() {
		String SQL="SELECT NOW()";//���� �ð��� �����ش�.
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//id/pw�� �˻��Ҷ� ����ϴ� �޼ҵ� (�α���)
	public int login(String user_id,String user_pw) {
		String SQL = "SELECT user_pw FROM USER WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(user_pw)) 
					return 1; //로그인
				else
					return 0; //비번이 틀림
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //아이디 없음
	}

	//check id(join) �ߺ�Ȯ�ο� ���
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

	//add user ȸ�����Կ� ���
	public int register(UserDataBean member) {
		String SQL="INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUser_name());
			pstmt.setInt(2, member.getUser_age());
			pstmt.setString(3, member.getUser_phone());
			pstmt.setString(4, member.getUser_id());
			pstmt.setString(5, member.getUser_pw());
			pstmt.setInt(6, member.getUser_hope());
			pstmt.setString(7, getDate());
			pstmt.setInt(8, member.getUser_available());
			//int result = new likeDAO().create(member.getUser_id());
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
	

	//get user info
	public UserDataBean getUser(String user_id) {
		String sql = "select * from user where user_id=?";
		UserDataBean user = new UserDataBean();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				user.setUser_name(rs.getString("user_name"));
				user.setUser_age(rs.getInt("user_age"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_hope(rs.getInt("user_hope"));
				user.setUser_date(rs.getString("user_date"));
				user.setUser_available(rs.getInt("user_available"));
			}
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
	/**
	 * 모든 유저의 리스트를 반환하는 메소드
	 * @return
	 */

	public ArrayList<UserDataBean> getAllUser() {
		ArrayList<UserDataBean> list = new ArrayList<UserDataBean>();
		try {
			String sql = "select * from user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDataBean user = new UserDataBean();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_age(rs.getInt("user_age"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_hope(rs.getInt("user_hope"));
				user.setUser_date(rs.getString("user_date"));
				user.setUser_available(rs.getInt("user_available"));
				list.add(user);
			}
		} catch (Exception e) {
			System.out.println("getAllUser err : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}
	/**
	 * 한 회원을 활동 정지 시키는 메소드
	 * @param user_id
	 */
	public void banUser(String user_id) {
		try {
			String sql = "update user set user_available = 0 where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("stopUser err : " + e.getMessage());
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 한 회원의 활동 정지 상태를 해지시키는 메소드
	 * @param user_id
	 */
	public void startUser(String user_id) {
		try {
			String sql = "update user set user_available = 1 where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("stopUser err : " + e.getMessage());
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 활동 정지된 회원들의 리스트를 반환하는 메소드
	 * @return
	 */
	public ArrayList<UserDataBean> getBannedUser() {
		ArrayList<UserDataBean> list = new ArrayList<UserDataBean>();
		try {
			String sql = "select * from user where user_available = 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDataBean user = new UserDataBean();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_age(rs.getInt("user_age"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_hope(rs.getInt("user_hope"));
				user.setUser_date(rs.getString("user_date"));
				user.setUser_available(rs.getInt("user_available"));
				list.add(user);
			}
		} catch (Exception e) {
			System.out.println("getAllUser err : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}
	/**
	 * 스탭의 리스트를 얻는 메소드
	 * @return
	 */
	public ArrayList<UserDataBean> getAllStaff() {
		ArrayList<UserDataBean> list = new ArrayList<UserDataBean>();
		try {
			String sql = "select * from user where user_available >= 2";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDataBean user = new UserDataBean();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_date(rs.getString("user_date"));
				user.setUser_available(rs.getInt("user_available"));
				list.add(user);
			}
		} catch (Exception e) {
			System.out.println("getAllUser err : " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
			}
		}
		return list;
	}
	/**
	 * 한 회원을 스탭으로 임명시키는 메소드
	 * @param user_id
	 */
	public void appointStaff(String user_id) {
		try {
			String sql = "update user set user_available = 3 where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("stopUser err : " + e.getMessage());
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
