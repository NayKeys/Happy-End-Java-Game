package org.happyEnd.happyEnd.version1.update0.patch0.game.items;

import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;

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
