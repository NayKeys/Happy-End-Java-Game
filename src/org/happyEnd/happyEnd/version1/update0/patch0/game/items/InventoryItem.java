package org.happyEnd.happyEnd.version1.update0.patch0.game.items;

import org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud.InventoryBar;

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
