package org.happyEnd.happyEnd.version1.update0.patch0.game.entities.exceptions;

import org.happyEnd.happyEnd.version1.update0.patch0.excetpionsManager.DefaultExceptionInInitializerError;

public class InterdictDodgeChanceException extends DefaultExceptionInInitializerError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201805131620L;

	public InterdictDodgeChanceException(float value) {
		super("for Wanted Value : " + value);
	}
}
