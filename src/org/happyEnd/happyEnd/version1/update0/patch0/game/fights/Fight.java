package org.happyEnd.happyEnd.version1.update0.patch0.game.fights;

import java.util.Random;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.Fighter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.PlayerCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.BattleItem;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.Shield;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.exceptions.NotEnoughMunitions;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Fight {

	public Fighter fighter;
	public PlayerCharacter player;
	private float playerDodgeChance;
	private float fighterDodgeChance;
	private Fighter currentAnimation, looser, winner;

	private boolean firstPartEnd;

	public Fight(PlayerCharacter player, Fighter fighter) throws SlickException {
		this.player = player;
		this.fighter = fighter;

		initBattle();

		currentAnimation = player;
		if (!(new Random().nextFloat() <= fighterDodgeChance)) {
			// TODO Player Attack Animation
			looser = fighter;
			winner = player;
		}
	}

	private void sedondPart() {
		firstPartEnd = true;
		if (winner != null)
			end();
		else {
			currentAnimation = fighter;
			if (!(new Random().nextFloat() <= playerDodgeChance)) {
				if (!player.haveShield()) {
					looser = player;
					winner = fighter;
				} else
					player.setShieldState(Shield.BROKEN);
			}
		}
		end();
	}

	private void end() {
		if (winner != null)
			looser.defeat(winner);
		else {
			//TODO pas de gagnant
		}
	}

	public void draw(GameContainer container) {
		if (!currentAnimation.attackAnimation(container)) {
			if (!firstPartEnd)
				sedondPart();
			else
				end();
		}
	}

	private void initBattle() throws SlickException {
		player.initBattle();
		fighter.initBattle();

		if (player.slingshot.isEquiped())
			// TODO Ask shoot ?
			if (true)
				try {
					player.slingshot.shoot();
				} catch (NotEnoughMunitions e) {
					e.showMessage();
				}

		playerDodgeChance = player.getDodgeChance();
		fighterDodgeChance = fighter.getDodgeChance();

		for (BattleItem item : player.getInventory().getBattleItems())
			fighterDodgeChance += -(fighterDodgeChance * item.getChance());

		for (BattleItem item : fighter.getInventory().getBattleItems())
			playerDodgeChance += -(fighterDodgeChance * item.getChance());

		fighterDodgeChance += (fighterDodgeChance * player.getWeapon().getChance());
	}
}
