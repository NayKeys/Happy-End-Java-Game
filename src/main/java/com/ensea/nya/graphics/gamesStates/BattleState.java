package com.ensea.nya.graphics.gamesStates;

import com.ensea.nya.game.fights.Fight;
import com.ensea.nya.graphics.hud.MapHud;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BattleState extends SimpleGameState {

	private MapHud hud;
	private int deltaTime;
	private Image backGround;
	private Fight fight;

	public BattleState(short ID, Fight fight) {
		super(ID);
		this.fight = fight;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		hud = new MapHud(container, fight.player);
		backGround = new Image(CharacterSelectionState.SELECTION_PATH + "backGround.png");
	}

	@Override
	public void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		backGround.draw();
		fight.draw(container);
		hud.setFps(container.getFPS());
		hud.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.deltaTime = delta;
		hud.setDeltaTime(deltaTime);
	}
}
