package org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.weapons;

public class Amulet extends Weapon {
	public static final short[] ID = { 25, 26, 27, 28, 29 };
	private static float[] chance = { 0, 0.1F, 0.15F, 0.2F };
	private static final String AMULET_PATH = PATH + "amulet/";

	public Amulet() {
		level = 0;
	}

	@Override
	public float getChance() {
		return chance[level];
	}

	@Override
	public short getID() {
		return ID[level];
	}

	@Override
	public String getItemImagePath() {
		return AMULET_PATH + level + ".png";
	}
}
