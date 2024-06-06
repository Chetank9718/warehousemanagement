package com.jsp.wms.utility;

public class ErrorStructure<T> {

	private int status;
	private String message;
	private T rootcause;
	
	public int getStatuscode() {
		return status;
	}
	public ErrorStructure setStatus(int statuscode) {
		this.status = statuscode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ErrorStructure setMessage(String message) {
		this.message = message;
		return this;
	}
	public T getRootcause() {
		return rootcause;
	}
	public ErrorStructure setRootcause(T rootcause) {
		this.rootcause = rootcause;
		return this;
	}
	
	
}
