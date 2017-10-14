package com.wedeal.model;

import java.util.Date;

public class User {

    private int userid;
    private String firstName;
    private Date dob;
    private String email;
    private String fileName;
    private String savePath;
    
    public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", dob=" + dob + ", email=" + email + ", fileName=" + fileName + ", savePath=" + savePath + "]";
	}
}