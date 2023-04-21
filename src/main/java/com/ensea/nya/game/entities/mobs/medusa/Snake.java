package com.ensea.nya.game.entities.mobs.medusa;

import com.ensea.nya.game.entities.mobs.Mob;
import org.newdawn.slick.GameContainer;

public class Snake extends Mob {

	public static final short ID = 19;

	public Snake() {
		super();
		dodgeChance = 0.3F;
	}

	@Override
	public short getID() {
		return ID;
	}

	@Override
	public void initBattle() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean attackAnimation(GameContainer container) {
		// TODO Auto-generated method stub
		return false;
	}
}
