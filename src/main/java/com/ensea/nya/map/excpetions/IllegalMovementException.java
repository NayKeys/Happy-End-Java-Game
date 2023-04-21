package com.ensea.nya.map.excpetions;

import com.ensea.nya.excetpionsManager.DefaultExceptionInInitializerError;

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
