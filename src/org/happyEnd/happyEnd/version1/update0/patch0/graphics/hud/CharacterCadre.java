package org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.PlayerCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.menus.mapState.HudComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class CharacterCadre extends HudComponent {
	
	private static final float MAX_SIZE= 86;
	
	private Image cadre;
	private PlayerCharacter player;

	public CharacterCadre(float x, float y, PlayerCharacter player) {
		super(x, y);
		this.player = player;
	}
	
	public CharacterCadre(float x, float y, PlayerCharacter player, GameContainer container) throws SlickException {
		this(x, y, player);
		init(container);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		cadre = new Image(MapHud.HUD_PATH + "characterMain.png");
		player.getWeapon().init(container);
		player.getWeapon().setMax(MAX_SIZE);
		player.getShield().init(container);
		player.getShield().setMax(MAX_SIZE);
	}

	@Override
	public void paint(GUIContext container, Graphics g) throws SlickException {
		cadre.draw(x, y);
		player.getShield().draw(x + 369, y+28);
		player.getWeapon().draw(x + 265, y+28);
	}
}
