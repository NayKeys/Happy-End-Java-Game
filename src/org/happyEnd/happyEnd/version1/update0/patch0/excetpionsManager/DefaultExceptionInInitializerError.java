package org.happyEnd.happyEnd.version1.update0.patch0.excetpionsManager;

public class DefaultExceptionInInitializerError extends ExceptionInInitializerError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201805192111L;

	public DefaultExceptionInInitializerError() {
		this("");
	}
	
	public DefaultExceptionInInitializerError(String msg) {
		super(msg);
		printStackTrace();
	}
	
	public DefaultExceptionInInitializerError(Throwable t) {
		super(t);
		ExceptionsManager.add(t);
	}
}
