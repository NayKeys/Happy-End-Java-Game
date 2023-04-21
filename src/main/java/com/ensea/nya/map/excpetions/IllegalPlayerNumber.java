package com.ensea.nya.map.excpetions;

import com.ensea.nya.excetpionsManager.DefaultExceptionInInitializerError;

public class IllegalPlayerNumber extends DefaultExceptionInInitializerError {

	/**
	 *
	 */
	private static final long serialVersionUID = 201805201716L;

	public IllegalPlayerNumber(int players) {
		super("For adding" + players + "players");
	}
}
