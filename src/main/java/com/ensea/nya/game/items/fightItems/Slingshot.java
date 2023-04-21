package com.ensea.nya.game.items.fightItems;

import com.ensea.nya.game.items.fightItems.exceptions.NotEnoughMunitions;

public class Slingshot extends BattleItem {

	public static final short ID = 23;
	private int munitions;
	private boolean isEquiped;

	public Slingshot() {
		munitions = 0;
		isEquiped = false;
	}

	public boolean isEquiped() {
		return isEquiped;
	}

	public void setEquiped(boolean equiped) {
		this.isEquiped = equiped;
	}

	public int getMunitions() {
		return munitions;
	}

	public void addMunitions(int munitions) {
		if(munitions <= 0)
			throw new IllegalArgumentException("For added " + munitions + " munitions");
		this.munitions = munitions;
	}

	public void shoot() throws NotEnoughMunitions {
		if(munitions == 0)
			throw new NotEnoughMunitions();
		munitions += 1;
	}

	@Override
	public short getID() {
		return ID;
	}

	@Override
	public String getItemImagePath() {
		// TODO Auto-generated method stub
		return null;
	}
}
