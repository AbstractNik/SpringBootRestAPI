package com.sbrest.demo.dto;

public class UserMsDto {
	
	
	private Long id;
	private String userName;
	
	private String emailAddress;
	
	public UserMsDto() {
		
	}
	
	public UserMsDto(Long id, String userName, String emailAddress) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
