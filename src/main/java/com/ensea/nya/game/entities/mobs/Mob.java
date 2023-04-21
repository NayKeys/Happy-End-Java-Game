package com.ensea.nya.game.entities.mobs;

import com.ensea.nya.game.entities.Fighter;
import com.ensea.nya.game.items.InventoryItem;
import com.ensea.nya.game.items.fightItems.clovers.FiveLeavesClover;
import com.ensea.nya.game.items.fightItems.clovers.FourLeavesClover;
import com.ensea.nya.game.items.fightItems.clovers.TwoLeavesClover;
import com.ensea.nya.utilities.Randomizer;

public abstract class Mob extends Fighter {

	protected InventoryItem[] possiblesItems;

	public Mob() {
		super();

		possiblesItems = new InventoryItem[3];
		possiblesItems[0] = new FiveLeavesClover();
		possiblesItems[1] = new FourLeavesClover();
		possiblesItems[2] = new TwoLeavesClover();

		// 20% tr�fle � 5 feuilles
		// 30% tr�fle � 4 feuilles; tot = 50%
		// 15% tr�fle � 2 feuilles, tot = 65%
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
