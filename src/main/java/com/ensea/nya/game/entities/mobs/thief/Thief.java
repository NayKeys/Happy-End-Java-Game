package com.ensea.nya.game.entities.mobs.thief;

import java.util.Random;

import com.ensea.nya.game.entities.mobs.Mob;
import org.newdawn.slick.GameContainer;

public class Thief extends Mob {

	public static final short ID = 20;

	public Thief() {
		super();
		dodgeChance = 0.3F;
	}

	public static boolean invoke() {
		return new Random().nextFloat() <= 0.05;
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
