package org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters;

import org.happyEnd.happyEnd.version1.update0.patch0.app.appText.SessionLanguage;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.weapons.Haxe;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class GreedCharacter extends PlayerCharacter {

	public static final short ID = 1;
	public static final String NAME = "Greed"; // Used for files Only

	public GreedCharacter() {
		super();
		history = SessionLanguage.getHistory(ID);
		weapon = new Haxe();
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
