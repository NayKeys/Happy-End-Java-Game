package com.ensea.nya.game.items.fightItems.weapons;

public class Haxe extends Weapon {

	public static final short[] ID = { 8, 9, 10, 11, 17 };
	private static final String HAXE_PATH = PATH + "haxe/";

	public Haxe() {
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
		return HAXE_PATH + level + ".png";
	}
}
