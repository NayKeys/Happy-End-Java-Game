package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;

public class TwoLeavesClover extends BattleItem {

	public static final short ID = 2;

	public TwoLeavesClover() {
		super();
		chance = -0.02F;
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
