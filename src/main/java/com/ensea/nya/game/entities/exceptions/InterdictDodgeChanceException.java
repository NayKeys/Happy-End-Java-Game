package com.ensea.nya.game.entities.exceptions;

import com.ensea.nya.excetpionsManager.DefaultExceptionInInitializerError;

public class InterdictDodgeChanceException extends DefaultExceptionInInitializerError {

	/**
	 *
	 */
	private static final long serialVersionUID = 201805131620L;

	public InterdictDodgeChanceException(float value) {
		super("for Wanted Value : " + value);
	}
}
