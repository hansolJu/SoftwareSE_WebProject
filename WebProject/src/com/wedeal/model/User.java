package com.wedeal.model;

import java.util.Date;

/*
 * 
 * 2017.11.01 jaeyun
 * 
 * user_num : PK 
 * user_name
 * user_age
 * user_phone
 * user_id
 * user_pw
 * user_hope1
 * user_hope2
 * user_hope3
 * 
 */

public class User {

	private int user_num;
    private String user_name;
    private int user_age;
    private String user_phone;
    private String user_id;
    private String user_pw;
    private String user_hope_1;
    private String user_hope_2;
    private String user_hope_3;
    
    public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_hope_1() {
		return user_hope_1;
	}
	public void setUser_hope_1(String user_hope_1) {
		this.user_hope_1 = user_hope_1;
	}
	public String getUser_hope_2() {
		return user_hope_2;
	}
	public void setUser_hope_2(String user_hope_2) {
		this.user_hope_2 = user_hope_2;
	}
	public String getUser_hope_3() {
		return user_hope_3;
	}
	public void setUser_hope_3(String user_hope_3) {
		this.user_hope_3 = user_hope_3;
	}
}