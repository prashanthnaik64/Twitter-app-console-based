package com.tweetApp.model;

import java.util.Date;

//import java.sql.Date;

public class UserModel {
	public String userid;
	public String firstName;
	public String lastName;
	public String gender;
	public String password;
	public String dob;
	
	public UserModel() {
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
