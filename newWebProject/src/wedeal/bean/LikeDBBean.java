package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class LikeDBBean {
		private Connection conn = null;
		private ResultSet rs;
		
		private static LikeDBBean instance = new LikeDBBean();
		
		public static LikeDBBean getinstance() {
			return instance;
		}
		
		private LikeDBBean() {
			try {
				String dbURL = "jdbc:mysql://204.249.22.34:3306/se?autoReconnect=true&useSSL=false";
				String dbID = "jy";
				String dbPW = "1365";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getDate() {
			String SQL="SELECT NOW()";
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
		
		//좋아요 번호 
		public int getNext() {
			String SQL="SELECT like_num FROM user_like ORDER BY like_num DESC";
			
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
		
		//좋아요 추가
		public int add(LikeDataBean like) {
			String SQL="INSERT INTO user_like VALUES (?, ?, ?, ?)";
			try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, like.getUser_id());
				pstmt.setInt(2, getNext());
				pstmt.setInt(3, like.getBoard_num());
				pstmt.setString(4, getDate());
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		//좋아요 삭제
		public int delete(LikeDataBean like) {
			String SQL="DELETE FROM user_like WHERE user_id = ? AND board_num = ?";
			try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, like.getUser_id());
				pstmt.setInt(2, like.getBoard_num());
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		//이미 좋아요 한 사람인지 check 하나의 게시글을 여러번 좋아요 할 수 없음
		public int check_id(String user_id, int board_num) {
			String SQL="SELECT * FROM user_like WHERE user_id = ? AND board_num = ?";
			try {
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, board_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;	
		}
		
		//user_id에 따라 좋아요한 글의 목록을 가져옴
		public ArrayList<LikeDataBean> getList(String user_id){
			String SQL="SELECT * FROM user_like WHERE user_id = ? ";
			ArrayList<LikeDataBean> list = new ArrayList<LikeDataBean>();
			
			try {
					PreparedStatement pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1, user_id);
					rs=pstmt.executeQuery();

				while(rs.next()) {
					LikeDataBean like = new LikeDataBean();
					like.setUser_id(rs.getString(1));
					like.setLike_num(rs.getInt(2));
					like.setBoard_num(rs.getInt(3));
					like.setLike_date(rs.getString(4));
					list.add(like);
				}
				return list;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//현재글을 좋아요한 user들의 list를 뽑음
		public ArrayList<String> getUser(int board_num) {
			String SQL="SELECT user_id FROM user_like WHERE board_num = ? ";
			ArrayList<String> list = new ArrayList<String>();
			
			try {
					PreparedStatement pstmt=conn.prepareStatement(SQL);
					pstmt.setInt(1, board_num);
					rs=pstmt.executeQuery();

				while(rs.next()) {
					String user_id;
					user_id = rs.getString("user_id");
					list.add(user_id);
				}
				return list;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//user들이 좋아요한 게시글들 번호를 뽑음
		public ArrayList<Integer> getBoard(ArrayList<String> user_id,int board_num){
			String SQL="SELECT board_num FROM user_like WHERE user_id = ? ";
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			try {
					
				for(int i = 0; i < user_id.size(); i++) {
					PreparedStatement pstmt=conn.prepareStatement(SQL);
					pstmt.setString(1, user_id.get(i));
					rs=pstmt.executeQuery();
					while(rs.next()) {
						Integer new_board_num;
						new_board_num = rs.getInt("board_num");
						if(!list.contains(new_board_num) && new_board_num != board_num) {//리스트에 없고, 현재 게시글이 아닐 경우에만 추가
							list.add(new_board_num);
						}
					}
				}
				Collections.shuffle(list); //골고루 뿌려주기 위해서 섞어버림
				return list;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
