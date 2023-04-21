package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;

public class FourLeavesClover extends BattleItem {
	
public static final short ID = 4;
	
	public FourLeavesClover() {
		super();
		chance = 0.04F;
	}

	@Override
	public short getID() {
		return ID;
	}

	@Override
	public String getItemImagePath() {
		// TODO Auto-generated method stub
		return null;
	}
}
