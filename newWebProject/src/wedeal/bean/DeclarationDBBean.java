package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeclarationDBBean {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private static DeclarationDBBean  instance = new DeclarationDBBean();
	
	public static DeclarationDBBean  getinstance() {
		return instance;
	}
	
	private DeclarationDBBean() {
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
	
	//신고접수 번호 
	public int getNext() {
		String SQL="SELECT declaration_num FROM declaration ORDER BY declaration_num DESC";
		
			try {
				pstmt=conn.prepareStatement(SQL);
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
		
	//DB에 입력 글쓰기
	public int declaration(DeclarationDataBean declaration) {
		String SQL="INSERT INTO declaration VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, declaration.getBoard_num());
			pstmt.setInt(2, getNext());
			pstmt.setString(3, getDate());
			pstmt.setString(4, declaration.getUser_id());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//이미 신고한 사람인지 check 하나의 게시글을 여러번 신고할 수 없음
	public int check_id(DeclarationDataBean declaration) {
		String SQL="SELECT * FROM declaration WHERE user_id = ? AND board_num = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, declaration.getUser_id());
			pstmt.setInt(2, declaration.getBoard_num());
			rs = pstmt.executeQuery();
			if(rs.next())
				return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	//모든 신고 목록을 보여준다.(한솔오빠 사용하는부분)
	public ArrayList<DeclarationDataBean> declaration_getList(){
		String SQL="SELECT * FROM declaration ";
		ArrayList<DeclarationDataBean> list = new ArrayList<DeclarationDataBean>();
		
		try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				rs=pstmt.executeQuery();

			while(rs.next()) {
				DeclarationDataBean declaration = new DeclarationDataBean();
				declaration.setBoard_num(rs.getInt(1));
				declaration.setDeclaration_num(rs.getInt(2));
				declaration.setDeclaration_date(rs.getString(3));
				declaration.setUser_id(rs.getString(4));
				list.add(declaration);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
