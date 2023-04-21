package org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud;

import java.awt.Font;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.PlayerCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.Component;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.menus.mapState.TopHud;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;

public class MapHud extends Component {

	public static final String HUD_PATH = "textures/gui/mapHUD/";
	private Image circle, minimap;
	private TrueTypeFont defaultFont, goldFont;
	private float screenW, screenH, mainY, mainPercent = 4;
	private PlayerCharacter player;

	// Menus
	private InventoryBar inventoryBar;
	private TopHud topHud;
	private CharacterCadre cadre;

	public MapHud(GameContainer container, PlayerCharacter player) throws SlickException {
		this.screenW = container.getWidth();
		this.screenH = container.getHeight();
		this.player = player;
		init(container);
	}

	private void init(GameContainer container) throws SlickException {
		defaultFont = new TrueTypeFont(new Font("Arial Rounded", 0, 20), true);
		goldFont = new TrueTypeFont(new Font("Eras Bold ITC", 0, 36), true);
		circle = new Image(HUD_PATH + "characterCircle.png");
		minimap = new Image(HUD_PATH + "minimap.png");
		mainY = screenH - screenH / mainPercent;
		inventoryBar = new InventoryBar(279, mainY + 150, player);
		inventoryBar.init(container);
		topHud = new TopHud();
		add(topHud, 28, 28);
		topHud.init(container);
		topHud.showInfo(true);

		cadre = new CharacterCadre(8, mainY, player, container);
	}
	
	@Override
	public void setDeltaTime(int deltaTime) {
		super.setDeltaTime(deltaTime);
		inventoryBar.setDeltaTime(deltaTime);
		cadre.setDeltaTime(deltaTime);
	}
	
	private int fps;
	
	public void setFps(int fps) {
		this.fps = fps;
	}

	public void paint(GUIContext container, Graphics g) throws SlickException {
		inventoryBar.render(container, g);
		topHud.setInfo(mouseX, mouseY, fps, zoom, mapX, mapY, mapMX, mapMY);
		cadre.render(container, g);
		g.setFont(goldFont);
		g.setColor(Color.yellow);
		g.drawString(String.valueOf(player.getMoney()), 110, mainY + 200);
		g.setFont(defaultFont);
	}

	public void drawCharCircles() {
		float w = circle.getWidth(), x = screenW - 20 - w, y = screenH - 20 - w;
		for (int i = 0; i < 3; i++) {
			circle.draw(x, y);
			x += -w - 20;
		}
	}

	public void showPauseMenu() throws SlickException {
		topHud.pause.menuButton.clickAnimation();
		topHud.pause.showPauseMenu(!topHud.pause.isShowingComponents());
	}

	private float mouseX, mouseY, zoom, mapX, mapY, mapMX, mapMY;

	public void setInfo(float mouseX, float mouseY, float zoom, float mapX, float mapY, float mapMX, float mapMY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.zoom = zoom;
		this.mapX = mapX;
		this.mapY = mapY;
		this.mapMX = mapMX;
		this.mapMY = mapMY;
	}

	public void drawMinimap(float minimapX, float minimapY, float minimapW, float minimapH) throws SlickException {
		minimap.draw(minimapX, minimapY, minimapW, minimapH);
	}
	
	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}
}
