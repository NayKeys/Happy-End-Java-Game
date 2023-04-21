package com.ensea.nya.app.accounts.exceptions;

public class WrongNameOrPasswordException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 201806161601L;

	public WrongNameOrPasswordException() {
		super("Le nom ou le mot de passe est incorrect");
	}
}
