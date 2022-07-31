package com.devsuperior.dsmeta.exceptions;

public class SalesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SalesNotFoundException() {
	}
	
	public SalesNotFoundException(String msg) {
		super(msg);
	}
}