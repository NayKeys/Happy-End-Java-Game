package com.ensea.nya.game.items;

import java.util.ArrayList;

import com.ensea.nya.game.items.fightItems.BattleItem;

public class Inventory extends ArrayList<InventoryItem> {

	/**
	 *
	 */
	private static final long serialVersionUID = 201805201634L;

	public ArrayList<BattleItem> getBattleItems() {
		ArrayList<BattleItem> output = new ArrayList<>();

		for(InventoryItem item : this) {
			if(item.isBattleItem()) {
				output.add((BattleItem) item);
			}
		}
		return output;
	}
}
