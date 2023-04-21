package com.ensea.nya.game.entities.characters;

import com.ensea.nya.app.appText.SessionLanguage;
import com.ensea.nya.game.items.fightItems.weapons.Amulet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class CeliaCharacter extends PlayerCharacter {

	public static final short ID = 24;
	public static final String NAME = "Celia"; // Used for files Only

	public CeliaCharacter() {
		super();
		history = SessionLanguage.getHistory(ID);
		weapon = new Amulet();
	}

	@Override
	public short getID() {
		return ID;
	}

	@Override
	public String getNamePath() {
		return NAME;
	}

	@Override
	public void initBattle() throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean attackAnimation(GameContainer container) {
		// TODO Auto-generated method stub
		return false;
	}
}
