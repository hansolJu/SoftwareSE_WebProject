package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import board.boardDTO;

public class likeDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public likeDAO() {
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
	
	public int create(String user_id) {
		String SQL = "CREATE TABLE "+user_id+"_like"+
				"(  out_cate_num INT NOT NULL," + 
				"	in_cate_num INT NOT NULL," + 
				"	board_num INT NOT NULL," + 
				"	CONSTRAINT "+user_id+"_like_out_cate_num FOREIGN KEY (out_cate_num) REFERENCES out_cate (out_cate_num) ON DELETE CASCADE)";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(SQL);
			return 1; //积己 己傍
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //积己 角菩
	}
	
	public int check(String user_id,int out_cate_num,int in_cate_num,int board_num) {
		String SQL = "INSERT INTO ? VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id+"_like");
			pstmt.setInt(2, out_cate_num);
			pstmt.setInt(3, in_cate_num);
			pstmt.setInt(4, board_num);
			pstmt.executeQuery();
			return 1; //己傍
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //角菩
	}
	
	public int uncheck(String user_id,int out_cate_num,int in_cate_num,int board_num) {
		String SQL = "DELETE FROM " + user_id + "_like" + " WHERE out_cate_num = ? AND in_cate_num = ? AND board_num = ?";
		
		try {

			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, out_cate_num);
			pstmt.setInt(3, in_cate_num);
			pstmt.setInt(4, board_num);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
