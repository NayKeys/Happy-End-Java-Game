package org.happyEnd.happyEnd.version1.update0.patch0.game.entities;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.exceptions.InterdictDodgeChanceException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public abstract class Fighter extends Entity {

	protected static float dodgeChance;
	public static final String BATTLE_PATH = "textures/entity/battle/";

	public abstract void defeat(Fighter winner);

	public Fighter() {
		super();
	}

	public float getDodgeChance() {
		return dodgeChance;
	}

	public void changeDodgeChance(float chance) {
		float expectedValue = dodgeChance + chance;
		if (expectedValue > 0F && expectedValue < 0.99F)
			dodgeChance += chance;
		else
			throw new InterdictDodgeChanceException(expectedValue);
	}

	public abstract boolean attackAnimation(GameContainer container);

	public abstract void initBattle() throws SlickException;
}
