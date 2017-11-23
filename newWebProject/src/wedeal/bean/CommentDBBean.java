package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static CommentDBBean instance = new CommentDBBean();
	
	public static CommentDBBean getinstance() {
		return instance;
	}
	
	private CommentDBBean() {
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
		String SQL="SELECT comment_num FROM comment ORDER BY comment_num DESC";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//현재가 첫번째인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	

	//DB에 입력 댓글쓰기
	public int write(CommentDataBean comment) {
		String SQL="INSERT INTO comment VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, comment.getCate_num());
			pstmt.setInt(2, comment.getBoard_num());
			pstmt.setInt(3, getNext());
			pstmt.setString(4, comment.getUser_id());
			pstmt.setString(5, comment.getComment_content());
			pstmt.setString(6, getDate());
			pstmt.setInt(7, 1);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//comment를 불러오는 부분
	public CommentDataBean getComment(int comment_num) {
		String SQL="SELECT * FROM comment WHERE comment_num = ? AND comment_available = 1";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, comment_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				CommentDataBean comment = new CommentDataBean();
				comment.setCate_num(rs.getInt(1));
				comment.setBoard_num(rs.getInt(2));
				comment.setComment_num(rs.getInt(3));
				comment.setUser_id(rs.getString(4));
				comment.setComment_content(rs.getString(5));
				comment.setComment_date(rs.getString(6));
				comment.setComment_available(rs.getInt(7));
				return comment;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//댓글 list불러오기
	public ArrayList<CommentDataBean> getList(int board_num){
		String SQL="SELECT * FROM comment WHERE board_num = ? AND comment_available = 1 ORDER BY comment_num DESC";
		ArrayList<CommentDataBean> list = new ArrayList<CommentDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentDataBean comment = new CommentDataBean();
				comment.setCate_num(rs.getInt(1));
				comment.setBoard_num(rs.getInt(2));
				comment.setComment_num(rs.getInt(3));
				comment.setUser_id(rs.getString(4));
				comment.setComment_content(rs.getString(5));
				comment.setComment_date(rs.getString(6));
				comment.setComment_available(rs.getInt(7));
				list.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//삭제시 사용
	public int delete(int comment_num) {
		String SQL="UPDATE comment SET comment_available = ? WHERE comment_num = ?";
			
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, comment_num);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//삭제된 댓글 list불러오기
	public ArrayList<CommentDataBean> delete_getList(){
		String SQL="SELECT * FROM comment WHERE comment_available = 0 ORDER BY comment_num DESC";
		ArrayList<CommentDataBean> list = new ArrayList<CommentDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentDataBean comment = new CommentDataBean();
				comment.setCate_num(rs.getInt(1));
				comment.setBoard_num(rs.getInt(2));
				comment.setComment_num(rs.getInt(3));
				comment.setUser_id(rs.getString(4));
				comment.setComment_content(rs.getString(5));
				comment.setComment_date(rs.getString(6));
				comment.setComment_available(rs.getInt(7));
				list.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
		//수정시 사용
	public int update(CommentDataBean comment) {
		String SQL="UPDATE comment SET comment_content = ? WHERE comment_num = ?";
			
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, comment.getComment_content());
			pstmt.setInt(2, comment.getComment_num());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
