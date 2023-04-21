package org.happyEnd.happyEnd.version1.update0.patch0.game.entities;

import java.util.List;

import org.happyEnd.happyEnd.version1.update0.patch0.game.HaveTextsElements;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.Inventory;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.InventoryItem;

public abstract class Entity extends HaveTextsElements {

	protected Inventory inventory;
	protected short money = 0;

	public Entity() {
		super();
		inventory = new Inventory();
	}

	public short getMoney() {
		return money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void giveItem(InventoryItem item) {
		inventory.add(item);
	}

	public void giveItems(List<InventoryItem> items) {
		inventory.addAll(items);
	}

	public void giveMoney(short money) {
		if (money > 0)
			this.money += money;
		else throw new IllegalArgumentException("giving money : " + money);
	}
}
