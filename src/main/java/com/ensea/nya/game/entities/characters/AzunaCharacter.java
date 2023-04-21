package com.ensea.nya.game.entities.characters;

import com.ensea.nya.app.appText.SessionLanguage;
import com.ensea.nya.game.entities.Fighter;
import com.ensea.nya.game.items.fightItems.weapons.Saber;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AzunaCharacter extends PlayerCharacter {

	public static final short ID = 16;
	public static final String NAME = "Azuna"; // Used for files Only

	public AzunaCharacter() {
		super();
		history = SessionLanguage.getHistory(ID);
		weapon = new Saber();
	}

	@Override
	public short getID() {
		return ID;
	}

	@Override
	public String getNamePath() {
		return NAME;
	}

	// Attack Animation
	protected Animation[] attackAnimations;
	private boolean attack1End, light;
	private float[][] positions = new float[][] { { 0, 180 }, { 816, 50 }, {816, 300} };
	private int[] durations = new int[] { 200, 200, 250, 300, 800, 100, 100, 100, 100, 100, 100, 900 };

	@Override
	public void initBattle() throws SlickException {
		String path = Fighter.BATTLE_PATH + "attack/" + name + "/";
		int index;
		attackAnimations = new Animation[2];

		attackAnimations[0] = new Animation();
		attackAnimations[1] = new Animation();

		for (int i = 0; i < durations.length; i++) {
			index = i <= 4 ? 0 : 1;
			attackAnimations[index].addFrame(new Image(path + i + ".png", true, Image.FILTER_NEAREST), durations[i]);
		}
		attackAnimations[1].stop();
		g = new Graphics();
		attackAnimations[0].setLooping(false);
		attackAnimations[1].setLooping(false);
	}

	@Override
	public boolean attackAnimation(GameContainer container) {
		if (light)
			g.fillRect(0, 0, container.getWidth(), container.getHeight());
		if (!attack1End)
			if (!attackAnimations[0].isStopped())
				attackAnimations[0].draw(positions[FIRST][X], positions[FIRST][Y], WITDTH, HEIGHT);
			else {
				attackAnimations[1].restart();
				attack1End = true;
				// light = true;
			}
		else if (!attackAnimations[1].isStopped())
			if (attackAnimations[1].getFrame() != attackAnimations[1].getFrameCount()-1)
				attackAnimations[1].draw(positions[SECOND][X], positions[SECOND][Y], WITDTH, HEIGHT);
			else
				attackAnimations[1].draw(positions[THIRD][X], positions[THIRD][Y], WITDTH, HEIGHT);
		else {
			attack1End = false;
			attackAnimations[0].restart();
			return true;
		}
		return true;
	}

	private static final int X = 0, Y = 1, FIRST = 0, SECOND = 1, THIRD = 2, WITDTH = 960, HEIGHT = 640;
}
