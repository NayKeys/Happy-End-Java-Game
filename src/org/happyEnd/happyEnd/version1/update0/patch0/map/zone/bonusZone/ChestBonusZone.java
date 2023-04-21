package org.happyEnd.happyEnd.version1.update0.patch0.map.zone.bonusZone;

import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.MapZone;
import org.newdawn.slick.SlickException;

public class ChestBonusZone extends MapZone implements BonusZone {
	
	private int before, after;

	public ChestBonusZone(String path, float mapX, float mapY, int befeforeIndex, int afterIndex)
			throws SlickException {
		super(path, mapX, mapY);
		this.before = befeforeIndex;
		this.after = afterIndex;
	}

	@Override
	public int getBeforeIndex() {
		return before;
	}

	@Override
	public int getAfterIndex() {
		return after;
	}
}
