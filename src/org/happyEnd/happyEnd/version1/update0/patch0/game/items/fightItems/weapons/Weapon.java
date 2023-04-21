package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.weapons;

import org.happyEnd.happyEnd.version1.update0.patch0.app.appText.SessionLanguage;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public abstract class Weapon extends BattleItem {
	
	protected short level;
	protected float[] chance = {0, 0.1F, 0.15F, 0.2F};
	
	public Weapon() {
		name = SessionLanguage.getName(getID());
		desc = SessionLanguage.getDesc(getID());
	}

	public void setLevel(short level, GameContainer container) throws SlickException {
		if(level < 0 || level > 3)
			throw new IllegalArgumentException();
		this.level = level;
		name = SessionLanguage.getName(getID());
		desc = SessionLanguage.getDesc(getID());
		init(container);
	}
}
