package com.uxpsystems.assignment.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id",nullable = false,unique = true)
	private Long userId;
	
	@Column(name = "user_name",nullable = false,unique = true)
	private String userName;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="status",nullable = false)
	private String status;

	transient private String errorCode;
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	transient private String errorDesc;
	
	public User(Long userId, String userName, String password, String status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.status = status;
	}

	public User() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
}
