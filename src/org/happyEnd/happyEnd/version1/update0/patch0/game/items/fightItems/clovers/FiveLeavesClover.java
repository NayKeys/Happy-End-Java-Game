package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;

public class FiveLeavesClover extends BattleItem {
	
	public static final short ID = 5;
	
	public FiveLeavesClover() {
		super();
		chance = 0.05F;
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
