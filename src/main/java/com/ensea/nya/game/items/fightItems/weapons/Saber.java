package com.ensea.nya.game.items.fightItems.weapons;

public class Saber extends Weapon {

	public static final short[] ID = { 12, 13, 14, 15, 18 };
	private static float[] chance = { 0, 0.1F, 0.15F, 0.2F };
	private static final String SABER_PATH = PATH + "saber/";

	public Saber() {
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
		return SABER_PATH + level + ".png";
	}
}
