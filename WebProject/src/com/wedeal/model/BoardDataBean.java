package com.wedeal.model;
import java.sql.*;

/*
 * 최종 수정일: 2017/10/15
 * 작성자: 이재윤
 * 
 * 이 파일은 게시판 시스템의 데이터 저장 Bean입니다.
 * 
 * ***********************데이터 설명*******************************
 * board테이블 데이터 저장 빈
 * board_num:테이블의 번호를 저장
 * board_writer:작성자를 저장
 * board_subject:글 제목을 저장
 * board_content:글 내용을 저장
 * board_passwd:글 작성시 비밀번호를 저장(삭제 시 비밀번호를 입력해야함)
 * board_date:글쓴 날짜를 저장
 * board_readcount:조회수를 저장
 * board_ip:작성자의 ip를 저장
 * board_ref:글의 그룹 번호를 저장(제목글과 그에 딸린 답변글이 그룹화)
 * board_re_step:제목글과 답변글의 순서를 정리하기 위한 필드
 * board_level:글의 레벨을 저장하는 필드(답변 순서)
 * board_image:이미지 파일의 경로를 저장
 * board_price:상품의 가격을 저장
 * ***************************************************************
 */

public class BoardDataBean {
	private int board_num;
	private String board_writer;
	private String board_subject;
	private String board_content;
	private String board_passwd;
	private Timestamp board_date;
	private int board_readcount;
	private String board_ip;
	private int board_ref;
	private int board_re_step;
	private int board_re_level;
	private String board_image;
	private int board_price;
	
	
	public int getBoard_price() {
		return board_price;
	}
	public void setBoard_price(int board_price) {
		this.board_price = board_price;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_passwd() {
		return board_passwd;
	}
	public void setBoard_passwd(String board_passwd) {
		this.board_passwd = board_passwd;
	}
	public Timestamp getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public String getBoard_ip() {
		return board_ip;
	}
	public void setBoard_ip(String board_ip) {
		this.board_ip = board_ip;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_re_step() {
		return board_re_step;
	}
	public void setBoard_re_step(int board_re_step) {
		this.board_re_step = board_re_step;
	}
	public int getBoard_re_level() {
		return board_re_level;
	}
	public void setBoard_re_level(int re_level) {
		this.board_re_level = re_level;
	}
	public String getBoard_image() {
		return board_image;
	}
	public void setBoard_image(String board_image_1) {
		this.board_image = board_image_1;
	}
}
