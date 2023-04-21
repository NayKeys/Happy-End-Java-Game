package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.Item;

public class Shield extends Item {

	private static final String PATH = BattleItem.PATH + "shield/";
	public static final short ID = 30;

	public static final short NORMAL = 1, NULL = 0, BROKEN = 2;

	private short state;

	public Shield() {
		state = 0;
	}
	
	public void setState(short state) {
		this.state = state;
	}
	
	public short getState() {
		return state;
	}

	@Override
	public String getItemImagePath() {
		return PATH + state + ".png";
	}

	@Override
	public short getID() {
		return ID;
	}
}
