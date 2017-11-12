package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wedeal.util.DbUtil;

public class boardDAO {
	
	private Connection conn;
	private ResultSet rs;
	private static boardDAO instance = new boardDAO();
	
	private boardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/se";
			String dbID = "root";
			String dbPW = "wjd123";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static boardDAO getInstance() {
		return instance;
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
	
	//DB에 입력 글쓰기
	public int write(String board_title,String user_id,String board_content,String board_image,String board_path) {
		String SQL="INSERT INTO board VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, board_title);
			pstmt.setString(3, user_id);
			pstmt.setString(4, getDate());
			pstmt.setString(5, board_content);
			pstmt.setString(6, board_image);
			pstmt.setString(7, board_path);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<boardDTO> getList(int pageNumber){
		String SQL="SELECT * FROM board WHERE board_num < ? ORDER BY board_num DESC LIMIT 8";
		ArrayList<boardDTO> list = new ArrayList<boardDTO>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*8);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				boardDTO boarddto = new boardDTO();
				boarddto.setBoard_num(rs.getInt(1));
				boarddto.setBoard_title(rs.getString(2));
				boarddto.setUser_id(rs.getString(3));
				boarddto.setBoard_date(rs.getString(4));
				boarddto.setBoard_content(rs.getString(5));
				boarddto.setBoard_image(rs.getString(6));
				boarddto.setBoard_path(rs.getString(7));
				list.add(boarddto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//페이징 처리를 위한 nextpage함수
	public boolean nextPage(int pageNumber) {
		String SQL="SELECT * FROM board WHERE board_num < ? ORDER BY board_num DESC LIMIT 8";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext()-(pageNumber-1)*8);
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
				boarddto.setBoard_image(rs.getString(6));
				boarddto.setBoard_path(rs.getString(7));
				return boarddto;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//수정시 사용
	public int update(int board_num,String board_title,String board_content,String board_image,String board_path) {
		String SQL="UPDATE board SET board_title = ?, board_content = ?, board_image = ?, board_path = ? WHERE board_num = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setString(3, board_image);
			pstmt.setString(4, board_path);
			pstmt.setInt(5, board_num);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int delete(int board_num) {
		String SQL = "DELETE FROM board WHERE board_num = ?";
		
		try {
			//삭제할 데이터를 받아와서 삭제 테이블에 삽입
			boardDTO boarddto = getBoard(board_num);
			insert_delete(boarddto.getBoard_num(),boarddto.getBoard_title(),boarddto.getUser_id(),boarddto.getBoard_date(),boarddto.getBoard_content(),boarddto.getBoard_image(),boarddto.getBoard_path());
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int insert_delete(int board_num,String board_title,String user_id,String board_date,String board_content,String board_image,String board_path) {
		String SQL = "INSERT INTO delete_board VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, board_title);
			pstmt.setString(3, user_id);
			pstmt.setString(4, board_date);
			pstmt.setString(5, board_content);
			pstmt.setString(6, board_image);
			pstmt.setString(7, board_path);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 
	 * @param searchName
	 * @return 결과 list 반환
	 * @throws SQLException
	 * 이름으로 물품을 검색하는 메소드
	 */
	public ArrayList<boardDTO> selectProductByName(String searchName) throws SQLException{
		conn = DbUtil.getConnection();  //connection 연결
		ResultSet rset = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<boardDTO> searchNameProductList = new ArrayList<boardDTO>();  //반환할 결과 리스트
		preparedStatement = conn.prepareStatement("select * from product where name like '%'||?||'%'");  //해당 이름을 포함하는 물품 검색
		preparedStatement.setString(1, searchName);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			//각 dto 변수 저장
			boardDTO board = new boardDTO();
			board.setBoard_num(rset.getInt(1));
			board.setBoard_title(rs.getString(2));
			board.setUser_id(rs.getString(3));
			board.setBoard_date(rs.getString(4));
			board.setBoard_content(rs.getString(5));
			board.setBoard_image(rs.getString(6));
			board.setBoard_path(rs.getString(7));
			searchNameProductList.add(board);  //리스트에 추가
		}
		DbUtil.close(conn, preparedStatement, rset);  //연결 해지
		return searchNameProductList;
	}
	/**
	 * 
	 * @param category
	 * @return
	 * @throws SQLException
	 * 상품명과 카테고리로 검색
	 * TO-DO : 계층 검색
	 */
	public ArrayList<boardDTO> selectProductByCategory(String searchName, String category) throws SQLException {
		conn = DbUtil.getConnection();  //connection 연결
		ResultSet rset = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<boardDTO> searchCategoryProductList = new ArrayList<boardDTO>();  //반환할 결과 리스트
		preparedStatement = conn.prepareStatement("select * from product where name like '%'||?||'%' and category = ?");  //해당 카테고리의 해당 이름을 포함하는 물품 검색
		preparedStatement.setString(1, searchName);
		preparedStatement.setString(2,  category);
		rset = preparedStatement.executeQuery();
		while(rset.next()) {
			//각 dto 변수 저장
			boardDTO board = new boardDTO();
			board.setBoard_num(rset.getInt(1));
			board.setBoard_title(rs.getString(2));
			board.setUser_id(rs.getString(3));
			board.setBoard_date(rs.getString(4));
			board.setBoard_content(rs.getString(5));
			board.setBoard_image(rs.getString(6));
			board.setBoard_path(rs.getString(7));
			searchCategoryProductList.add(board);  //리스트에 추가
		}
		DbUtil.close(conn, preparedStatement, rset);  //연결 해지
		return searchCategoryProductList;
	}
}
