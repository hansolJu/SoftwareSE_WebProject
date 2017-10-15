package com.wedeal.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.wedeal.model.BoardDataBean;

/*
 * 최종 수정일: 2017/10/15
 * 작성자:이재윤
 * 
 * 이 파일은 게시판 시스템의 DB를 처리하는 DAO파일입니다.
 * 
 * 수정해야할 부분-> 이미지 경로를 저장하는 부분
 * 앙 기모띠!!!!ㅋㅋㅋㅋㅋ
 */

public class BoardDAO {
	
	private static BoardDAO instance=new BoardDAO();
	
	//.jsp 페이지에서 BoardDAO클래스의 메소드에 접근시 필요함
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private BoardDAO() {}
	
	//Connection객체를 얻어냄->쿼리문을 수행하는 메소드에서 사용함
	private	Connection getConnection() throws Exception{
		
			Context initCtx=new InitialContext();
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			DataSource ds=(DataSource)envCtx.lookup("jdbc/jsptest");
			return ds.getConnection();
		}
	
	//board테이블에 글을 추가함
	public int insertArticle(BoardDataBean article) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int x=0;
		int number=0;
		String sql="";
	
		int num=article.getBoard_num();
		int ref=article.getBoard_ref();
		int re_step=article.getBoard_re_step();
		int re_level=article.getBoard_re_level();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("select max(board_num) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next())
				number=rs.getInt(1)+1;
			else
				number=1;
			
			if(num!=0) {
				sql="update board set board_re_step=re_step+1 where board_ref=? and board_re_step>?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}
			else {
				ref=number;
				re_step=0;
				re_level=0;
			}
			
			sql="insert into board(board_writer,board_subject,board_content,board_passwd,board_date,";
			sql+="board_ip,board_ref,board_re_step,board_re_level) value(?,?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, article.getBoard_writer());
			pstmt.setString(2, article.getBoard_subject());
			pstmt.setString(3, article.getBoard_content());
			pstmt.setString(4, article.getBoard_passwd());
			pstmt.setTimestamp(5, article.getBoard_date());
			pstmt.setString(6, article.getBoard_ip());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, re_step);
			pstmt.setInt(9, re_level);
			pstmt.executeQuery();
			x=1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return x;
	}
	
	//board테이블에 저장된 전체 글의 수를 얻어냄
	public int getArticleCount() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int x=0;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement("select count(*) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next())
				x=rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return x;
	}
	
	//글의 목록을 가져옴
	public List<BoardDataBean> getArticles(int start,int end){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardDataBean> articleList=null;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement("select * from board order by board_ref desc,board_re_step asc limit ?,?");
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				articleList=new ArrayList<BoardDataBean>(end);
				do {
					BoardDataBean article=new BoardDataBean();
					article.setBoard_num(rs.getInt("board_num"));
					article.setBoard_writer(rs.getString("board_writer"));
					article.setBoard_subject(rs.getString("board_subject"));
					article.setBoard_content(rs.getString("board_count"));
					article.setBoard_passwd(rs.getString("board_passwd"));
					article.setBoard_date(rs.getTimestamp("board_date"));
					article.setBoard_readcount(rs.getInt("board_readcount"));
					article.setBoard_ref(rs.getInt("board_ref"));
					article.setBoard_re_step(rs.getInt("board_step"));
					article.setBoard_re_level(rs.getInt("board_re_level"));
					article.setBoard_ip(rs.getString("board_ip"));
					
					//List객체에 BoardDataBean객체를 저장
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return articleList;
	}
	
	//글 수정 폼에서 사용할 글의 내용
	public BoardDataBean updateGetArticle(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDataBean article=null;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement("select * from board where board_num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				article=new BoardDataBean();
				article.setBoard_num(rs.getInt("board_num"));
				article.setBoard_writer(rs.getString("board_writer"));
				article.setBoard_subject(rs.getString("board_subject"));
				article.setBoard_content(rs.getString("board_count"));
				article.setBoard_passwd(rs.getString("board_passwd"));
				article.setBoard_date(rs.getTimestamp("board_date"));
				article.setBoard_readcount(rs.getInt("board_readcount"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_re_step(rs.getInt("board_step"));
				article.setBoard_re_level(rs.getInt("board_re_level"));
				article.setBoard_ip(rs.getString("board_ip"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return article;
	}
	
	//글수정 처리에서 사용
	public int updateArticle(BoardDataBean article) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int x=-1;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement("select board_passwd from board where board_num=?");
			pstmt.setInt(1,article.getBoard_num());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String dbpasswd=rs.getString("board_passwd");
				if(dbpasswd.equals(article.getBoard_passwd())) {
					String sql="update board set board_subject=?,";
					sql+="board_content=? where board_num=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,article.getBoard_subject());
					pstmt.setString(2,article.getBoard_content());
					pstmt.setInt(3,article.getBoard_num());
					pstmt.executeQuery();
					x=1;
				}
				else x=0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return x;
	}
	
	//글삭제 처리시 사용
	public int deleteArticle(int num,String passwd) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int x=-1;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement("select board_passwd from board where board_num=?");
			pstmt.setInt(1,num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				String dbpasswd=rs.getString("board_passwd");
				if(dbpasswd.equals(passwd)) {
					pstmt=conn.prepareStatement("delete from board where board_num=?");
					pstmt.setInt(1,num);
					pstmt.executeQuery();
					x=1;
				}
				else x=0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return x;
	}
}

