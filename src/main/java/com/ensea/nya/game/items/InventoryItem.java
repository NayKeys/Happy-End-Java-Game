package com.ensea.nya.game.items;

import com.ensea.nya.graphics.hud.InventoryBar;

public abstract class InventoryItem extends Item {

	public InventoryItem() {
		super();
		setMax(InventoryBar.ITEM_SIZE);
	}
	 @Override
	public boolean isInventoryItem() {
		return true;
	}
}
