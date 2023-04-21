package org.happyEnd.happyEnd.version1.update0.patch0.map.excpetions;

import org.happyEnd.happyEnd.version1.update0.patch0.excetpionsManager.DefaultExceptionInInitializerError;

public class IllegalMovementException extends DefaultExceptionInInitializerError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201819052109L;

	public IllegalMovementException() {
		super();
	}
	
	public IllegalMovementException(String msg) {
		super(msg);
	}
	
	public IllegalMovementException(Throwable t) {
		new Exception(t);
	}
}
