package org.happyEnd.happyEnd.version1.update0.patch0.map.zone;

import org.newdawn.slick.SlickException;

public abstract class NormalZone extends MapZone {
	private int index;
	
	public NormalZone(String path, float mapX, float mapY, int index) throws SlickException {
		super(path, mapX, mapY);
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
}
