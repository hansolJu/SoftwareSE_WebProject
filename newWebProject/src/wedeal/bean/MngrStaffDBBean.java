package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MngrStaffDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MngrStaffDBBean instance = new MngrStaffDBBean();

	public static MngrStaffDBBean getinstance() {
		return instance;
	}

	private MngrStaffDBBean() {
		try {
			String dbURL = "jdbc:mysql://203.249.22.34:3306/se?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteStaff(String staffId) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from users where staffId=?");
            preparedStatement.setString(1, staffId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

		
	}

	public ArrayList<MngrStaffDataBean> getAllList() {
		String SQL="SELECT * FROM cate WHERE cate_parent > 0 ORDER BY cate_num DESC";
		ArrayList<MngrStaffDataBean> list = new ArrayList<MngrStaffDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MngrStaffDataBean staffdatabean = new MngrStaffDataBean();
				staffdatabean.setAuthor(rs.getInt(1));
				staffdatabean.setDate(rs.getString(2));
				staffdatabean.setId(rs.getString(3));
				list.add(staffdatabean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
