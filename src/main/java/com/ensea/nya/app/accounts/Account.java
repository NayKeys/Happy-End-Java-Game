package com.ensea.nya.app.accounts;

import com.ensea.nya.app.accounts.exceptions.WrongNameOrPasswordException;

public class Account {

	public static Account connectedAccount = new DevAccount();

	public static void Connect(String name, String password) throws WrongNameOrPasswordException {
		DevAccount account = new DevAccount();
		if (name.equals(account.getName()) && password.equals("lebacC'estdel'0")) {
			connectedAccount = account;
			connectedAccount.registered = true;
		} else
			throw new WrongNameOrPasswordException();
	}

	public static final String PASSWORDS_PATH = "app/accounts/passwords/";

	protected int ID;
	protected String name;
	protected boolean registered;

	public Account(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

	public boolean isRegistered() {
		return registered;
	}
}
