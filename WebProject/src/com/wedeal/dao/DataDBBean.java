	package com.wedeal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wedeal.model.LogonDataBean;
import com.wedeal.util.DbUtil;

public class DataDBBean {
	private Connection connection;
	private static DataDBBean instance = new DataDBBean(); //DataDBBean 전역객체 생성
	
	public DataDBBean() {
        connection = DbUtil.getConnection();
    }
	//객체를 리턴하는 메소드
	public static DataDBBean getInstance() {
		return instance;
	}
	//회원가입 처리에서 사용하는 새 레코드 추가 메서드
	public void insertMember(LogonDataBean member) {
	
		PreparedStatement pstmt = null;
		 //sha = new Sha256PasswordPlugin();//암호화 374page
		try {
			//String onePass = member.getPasswd();
			//String shaPass = sha.
			pstmt = connection.prepareStatement("insert into member values (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setTimestamp(5, member.getLast_date());
			pstmt.setInt(6, member.getVisitCount());
			pstmt.setInt(7, member.getPostCount());
			pstmt.setInt(8, member.getCommentCount());
			pstmt.setString(9, member.getGender());
			pstmt.setString(10, member.getAges());
			pstmt.setBoolean(11, member.isActive());
			pstmt.executeUpdate();
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch (SQLException ex) {}
		}
	}
	//로그인 폼처리 페이지의 사용자 인증 처리 및 
	//회원정보 수정/탈퇴를 사용자 인증에서 사용하는 메서드
	public int userCheck(String id, String passwd) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		//SHA256 sha = SHA256.getInstance();
		try {
			//String onePass = member.getPasswd();
			
			pstmt = connection.prepareStatement("select passwd form member where id = ?");
			
			String orgPass = passwd;
			//String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//해당 아이디가 있으면 수행
				String dbpasswd=rs.getString("passwd");
				if(true)//????
					x=1;
				else
					x=0;
			}else//해당 아이디 없으면 수행
				x=-1;
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
			if(pstmt != null)try {pstmt.close();}catch (SQLException ex) {}
		}
		return x;
	}
	//아이디 중복 확인에서 아이디의 중복 여부를 확인하는 메소드
	public int confirmId(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			pstmt = connection.prepareStatement("select id form member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())//아이디 존재
				x=1;//같은 아이디 있음
			else
				x=-1;//같은 아이디 없음
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch (SQLException ex) {}
		}
		return x;
	}
	//회원 정보 수정 폼을 위한 기존 가입 정보를 가져오는 메소드
	//public LogonDataB
	
}
