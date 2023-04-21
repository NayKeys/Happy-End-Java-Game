package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.InventoryItem;

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
