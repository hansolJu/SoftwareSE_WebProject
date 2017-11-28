package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeclarationDataBean {
	private int board_num;
	private int declaration_num;
	private String declaration_date;
	private String user_id;//신고자 아이디
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getDeclaration_num() {
		return declaration_num;
	}
	public void setDeclaration_num(int declaration_num) {
		this.declaration_num = declaration_num;
	}
	public String getDeclaration_date() {
		return declaration_date;
	}
	public void setDeclaration_date(String declaration_date) {
		this.declaration_date = declaration_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
