package org.happyEnd.happyEnd.version1.update0.patch0.graphics.menus.mapState;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud.MapHud;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class TopHud extends HudComponent{
	
	private static final String PATH = MapHud.HUD_PATH + "topMenu/";
	
	private Image hud;
	public PauseMenu pause;
	private Graphics g;
	
	private boolean showInfo;
	
	public TopHud() throws SlickException {
	}

	public void init(GameContainer container) throws SlickException {
		hud = new Image(PATH + "hud.png");
		pause = new PauseMenu();
		g = new Graphics();
		g.setFont(Launcher_HappyEnd.defaultFont);
		add(pause, x+5, y+5);
		pause.init(container);
	}
	
	public void showInfo(boolean show) {
		showInfo = show;
	}
	
	@Override
	public void paint(GUIContext container, Graphics g2) throws SlickException {
		hud.draw(x, y);
		if (showInfo) {
			g.setColor(Color.white);
			g.drawString("mouse : ("+mouseX+", "+mouseY+")", 140, 90);
			g.drawString("Fps : "+fps, 140, 60);
			g.drawString("map : ("+String.format("%.0f", mapX)+", "+String.format("%.0f", mapY)+")", 370, 60);
			g.drawString("Zoom : "+String.format("%.4f", zoom), 600, 60);
			g.drawString("Picture pointing X : "+String.format("%.0f", mapMX) + " Y : "+String.format("%.0f", mapMY), 410, 90);
		}
	}
	
	private float mouseX, mouseY, fps, zoom, mapX, mapY, mapMX, mapMY;

	public void setInfo(float mouseX, float mouseY, int fps, float zoom, float mapX, float mapY, float mapMX, float mapMY) throws SlickException {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.fps = fps;
		this.zoom = zoom;
		this.mapX = mapX;
		this.mapY = mapY;
		this.mapMX = mapMX;
		this.mapMY = mapMY;
	}
}
