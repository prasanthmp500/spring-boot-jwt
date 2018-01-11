package com.openmind.springjwt.springbootjwt.rest.errors;

public class Error {
	
	private int error;
	
	private String message;

	public Error(int error, String message) {
		super();
		this.error = error;
		this.message = message;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [error=" + error + ", message=" + message + "]";
	}

}
