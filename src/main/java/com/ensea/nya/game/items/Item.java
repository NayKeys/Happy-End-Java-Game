package com.ensea.nya.game.items;

import com.ensea.nya.game.HaveTextsElements;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Item extends HaveTextsElements {

	private Image item;
	private float max;

	public void draw(float x, float y) {
		item.draw(x, y, max, max);
	}

	public void setMax(float max) {
		this.max = max;
	}

	public void init(GameContainer container) throws SlickException {
		item = new Image(getItemImagePath(), false, Image.FILTER_NEAREST);
	}

	public abstract String getItemImagePath();

	public boolean isBattleItem() {
		return false;
	}

	public boolean isInventoryItem() {
		return false;
	}
}
