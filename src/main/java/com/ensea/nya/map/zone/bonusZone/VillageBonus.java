package com.ensea.nya.map.zone.bonusZone;

import com.ensea.nya.map.zone.VillageZone;
import org.newdawn.slick.SlickException;

public class VillageBonus extends VillageZone implements BonusZone {

	private int before, after;

	public VillageBonus(String path, float mapX, float mapY, int befeforeIndex, int afterIndex)
			throws SlickException {
		super(path, mapX, mapY, -1);
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
