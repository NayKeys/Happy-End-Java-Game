package org.happyEnd.happyEnd.version1.update0.patch0.map.excpetions;

import org.happyEnd.happyEnd.version1.update0.patch0.excetpionsManager.DefaultExceptionInInitializerError;

public class IllegalPlayerNumber extends DefaultExceptionInInitializerError {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 201805201716L;

	public IllegalPlayerNumber(int players) {
		super("For adding" + players + "players");
	}
}
