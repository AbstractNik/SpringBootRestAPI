package com.sbrest.demo.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	
	private Date timeStamp;
	private String message;
	private String errorDetais;
	public CustomErrorDetails(Date timeStamp, String message, String errorDetais) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errorDetais = errorDetais;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetais() {
		return errorDetais;
	}
	public void setErrorDetais(String errorDetais) {
		this.errorDetais = errorDetais;
	}
	@Override
	public String toString() {
		return "CustomErrorDetails [timeStamp=" + timeStamp + ", message=" + message + ", errorDetais=" + errorDetais
				+ "]";
	}
	
	

}
