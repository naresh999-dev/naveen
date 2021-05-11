package com.example.Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity  
public class User {
	@Id  
	@GeneratedValue(strategy =GenerationType.AUTO )
	private int id;  
	private String username; 
	private String password;
	private String emial;
	private String mobileNumber;
	private boolean emailActivation;
	private boolean mobileActivation;
	private int emailActivationCode;
	private int mobileActivationCode;
	public int getEmailActivationCode() {
		return emailActivationCode;
	}
	public void setEmailActivationCode(int emailActivationCode) {
		this.emailActivationCode = emailActivationCode;
	}
	public int getMobileActivationCode() {
		return mobileActivationCode;
	}
	public void setMobileActivationCode(int mobileActivationCode) {
		this.mobileActivationCode = mobileActivationCode;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmial() {
		return emial;
	}
	public void setEmial(String emial) {
		this.emial = emial;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public boolean isEmailActivation() {
		return emailActivation;
	}
	public void setEmailActivation(boolean emailActivation) {
		this.emailActivation = emailActivation;
	}
	public boolean isMobileActivation() {
		return mobileActivation;
	}
	public void setMobileActivation(boolean mobileActivation) {
		this.mobileActivation = mobileActivation;
	}	
	public int getId()   
	{  
	return id;  
	}  
	public void setId(int id)   
	{  
	this.id = id;  
	}  
	public String getUname()   
	{  
	return username;  
	}  
	public void setUname(String username)   
	{  
	this.username = username;  
	}  
	@Override  
	public String toString()   
	{  
	return "User [id=" + id + ", uname=" + username + "]";  
	}  

}
