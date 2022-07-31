package com.devsuperior.dsmeta.exceptions;

public class SmsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SmsException() {
	}
	
	public SmsException(String msg) {
		super(msg);
	}
}
