package wedeal.bean;
/**
 * 코드설명
 * 작성자:이재윤
 * 수정자:
 * 최종수정일: 17.11.17
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;


public class CateDBBean {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private static CateDBBean instance = new CateDBBean();
	
	public static CateDBBean getinstance() {
		return instance;
	}
	
	private CateDBBean() {
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
	
	//추가될 카테고리 번호
	public int getNext() {
		String SQL="SELECT cate_num FROM cate ORDER BY cate_num DESC";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1;//현재가 첫번째 카테고리인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//대 카테고리 추가하는부분 ->한솔오빠가 쓰는부분
	public int add_out_cate(String cate_name) {
		String SQL="INSERT INTO cate VALUES (?, ?, ?)";
		try {
			//여기에 추가해야함
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, cate_name);
			pstmt.setString(3, getDate());
			//만약 대 카테고리가 아니라면 이부분 을 수정해줘야함!
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//소 카테고리 추가하는부분 ->한솔오빠가 쓰는부분 대 카테고리가 있어야만 생성 가능
		public int add_in_cate(String cate_name,int cate_parent) {
			String SQL="INSERT INTO cate VALUES (?, ?, ?, ?)";
			try {
				//여기에 추가해야함
				PreparedStatement pstmt=conn.prepareStatement(SQL);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, cate_name);
				pstmt.setString(3, getDate());
				//만약 cate_parent가 존재하지 않으면 -1리턴 (오류)
				if(getBoard(cate_parent) == null)
					return -1;
				pstmt.setInt(4, cate_parent);
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
	
	//현재 카테고리번호로 정보를 불러옴
	public CateDataBean getBoard(int cate_num) {
		String SQL="SELECT * FROM out_cate WHERE out_cate_num = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, cate_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				CateDataBean catedatabean = new CateDataBean();
				catedatabean.setCate_num(rs.getInt(1));
				catedatabean.setCate_name(rs.getString(2));
				catedatabean.setCate_date(rs.getString(3));
				catedatabean.setCate_parent(rs.getInt(4));
				catedatabean.setCate_available(rs.getBoolean(5));
				return catedatabean;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	//카테고리 리스트를 출력
	public ArrayList<CateDataBean> getList(){
		String SQL="SELECT * FROM cate ORDER BY cate_num DESC";
		ArrayList<CateDataBean> list = new ArrayList<CateDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CateDataBean catedatabean = new CateDataBean();
				catedatabean.setCate_num(rs.getInt(1));
				catedatabean.setCate_name(rs.getString(2));
				catedatabean.setCate_date(rs.getString(3));
				catedatabean.setCate_parent(rs.getInt(4));
				list.add(catedatabean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}*/
	
	public ArrayList<CateDataBean> in_getList(){
		String SQL="SELECT * FROM cate WHERE cate_parent > 0 ORDER BY cate_num DESC";
		ArrayList<CateDataBean> list = new ArrayList<CateDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CateDataBean catedatabean = new CateDataBean();
				catedatabean.setCate_num(rs.getInt(1));
				catedatabean.setCate_name(rs.getString(2));
				catedatabean.setCate_date(rs.getString(3));
				catedatabean.setCate_parent(rs.getInt(4));
				list.add(catedatabean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<CateDataBean> getList(){
		String SQL="SELECT * FROM cate WHERE cate_parent IS NULL ORDER BY cate_num DESC";
		ArrayList<CateDataBean> list = new ArrayList<CateDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CateDataBean catedatabean = new CateDataBean();
				catedatabean.setCate_num(rs.getInt(1));
				catedatabean.setCate_name(rs.getString(2));
				catedatabean.setCate_date(rs.getString(3));
				catedatabean.setCate_parent(rs.getInt(4));
				list.add(catedatabean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

