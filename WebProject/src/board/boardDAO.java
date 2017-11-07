package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class boardDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public boardDAO() {
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
	
	//현재 시간을 서버에 넣어준다.
	public String getDate() {
		String SQL="SELECT NOW()";//현재 시간을 돌려준다.
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
	 
	//작성될 글 번호 구하기
	public int getNext() {
		String SQL="SELECT board_num FROM board ORDER BY board_num DESC";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//현재가 첫번째 게시글인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write(String board_title,String user_id,String board_content) {
		String SQL="INSERT INTO board VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, board_title);
			pstmt.setString(3, user_id);
			pstmt.setString(4, getDate());
			pstmt.setString(5, board_content);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<boardDTO> getList(int pageNumber){
		String SQL="SELECT * FROM board WHERE board_num < ? AND board_available = 1 ORDER BY board_num DESC LIMIT 10";
		ArrayList<boardDTO> list = new ArrayList<boardDTO>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				boardDTO boarddto = new boardDTO();
				boarddto.setBoard_num(rs.getInt(1));
				boarddto.setBoard_title(rs.getString(2));
				boarddto.setUser_id(rs.getString(3));
				boarddto.setBoard_date(rs.getString(4));
				boarddto.setBoard_content(rs.getString(5));
				boarddto.setAvailable(rs.getInt(6));
				list.add(boarddto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//페이징 처리를 위한 nextpage함수
	public boolean nextPage(int pageNumber) {
		String SQL="SELECT * FROM board WHERE board_num < ? AND board_available = 1 ORDER BY board_num DESC LIMIT 10";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*10);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boardDTO getBoard(int board_num) {
		String SQL="SELECT * FROM board WHERE board_num = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				boardDTO boarddto = new boardDTO();
				boarddto.setBoard_num(rs.getInt(1));
				boarddto.setBoard_title(rs.getString(2));
				boarddto.setUser_id(rs.getString(3));
				boarddto.setBoard_date(rs.getString(4));
				boarddto.setBoard_content(rs.getString(5));
				boarddto.setAvailable(rs.getInt(6));
				return boarddto;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//수정시 사용
	public int update(int board_num,String board_title,String board_content) {
		String SQL="UPDATE board SELECT board_title = ?, board_content = ? WHERE board_num = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setInt(3, board_num);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
