package com.ensea.nya.game.items.fightItems;

import com.ensea.nya.game.items.InventoryItem;

public abstract class BattleItem extends InventoryItem {

	public static final String PATH = "textures/items/combat/";

	protected float chance;

	public float getChance() {
		return chance;
	}

	@Override
	public boolean isBattleItem() {
		return true;
	}
}
