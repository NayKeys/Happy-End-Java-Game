package com.ensea.nya.game.game;

import com.ensea.nya.App;
import com.ensea.nya.game.entities.characters.PlayerCharacter;
import com.ensea.nya.map.Map;
import com.ensea.nya.map.excpetions.IllegalPlayerNumber;

public class Party {

	private PlayerCharacter[] players;
	private PlayerCharacter connectedP;
	private Map map;

	private int round = 0, turn = 0, index = 0;

	public Party(PlayerCharacter connectedP, PlayerCharacter... players) {
		if (players.length != 3)
			throw new IllegalPlayerNumber(players.length);
		this.players = new PlayerCharacter[]{connectedP, players[0], players[1],
				players[2]};
		this.connectedP = connectedP;
		map = new Map();
	}

	public int getNumberOfPlayer() {
		return players.length;
	}

	public void nextTurn() {
		round++;
		if(++index >= players.length)
			index = 0;
		players[index].yourTurn();
	}

	// Geters
	public PlayerCharacter getConnectedPlayerChar() {
		return connectedP;
	}

	public int getTurnNumber() {
		return turn;
	}

	public int getRoundNumber() {
		return round;
	}

	public static Map getMap() {
		return App.getParty().map;
	}
}
