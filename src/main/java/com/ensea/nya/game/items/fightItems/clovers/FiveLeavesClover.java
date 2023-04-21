package com.ensea.nya.game.items.fightItems.clovers;

import com.ensea.nya.game.items.fightItems.BattleItem;

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
