package org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.Fighter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.InventoryItem;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers.FiveLeavesClover;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers.FourLeavesClover;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.clovers.TwoLeavesClover;
import org.happyEnd.happyEnd.version1.update0.patch0.utilities.Randomizer;

public abstract class Mob extends Fighter {

	protected InventoryItem[] possiblesItems;

	public Mob() {
		super();

		possiblesItems = new InventoryItem[3];
		possiblesItems[0] = new FiveLeavesClover();
		possiblesItems[1] = new FourLeavesClover();
		possiblesItems[2] = new TwoLeavesClover();

		// 20% trèfle à 5 feuilles
		// 30% trèfle à 4 feuilles; tot = 50%
		// 15% trèfle à 2 feuilles, tot = 65%
		short[] chance = { 20, 30, 15 };

		InventoryItem item;
		if ((item = new Randomizer<InventoryItem>(possiblesItems, chance).getValue()) != null)
			inventory.add(item);
	}

	@Override
	public void defeat(Fighter winner) {
		winner.giveItem(inventory.get(0));
		winner.giveMoney(money);
	}
}
