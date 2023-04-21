package org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates;

import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.PlayerCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.Clickable;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.Element;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.InvisibleClickableZone;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.MouseDragListener;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud.MapHud;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class MapState extends SimpleGameState {

	private static final String MAP_PATH = "textures/map/mapV1";
	private float zoom;
	private Image map;
	private float pictureW, pictureH, a, b, mapX2, mapY2, mapX = 0, mapY = 0, minimapW = 300, minimapH, mapX1, mapY1, mapW, mapH, mapMX, mapMY;
	private MapHud hud;
	private PlayerCharacter player;
	private Graphics g;
	private ArrayList<Element> elements;
	private MapCommands commands;
	private static MapState loadedMapState;
	private Clickable miniMapClick;

	private float zoomMin = 1.03f, zoomMax = 3f;

	// User Settings
	private float moveSensitivity = 1.5f // More it's higher, more it's weak
			, screenW, screenH, minimapX, minimapY;

	public MapState(short ID) {
		super(ID);
	}

	public void setCharacter(PlayerCharacter player) {
		this.player = player;
		if (loadedMapState != null) {
			hud.setPlayer(player);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Log.info(" GameState init : Map");

		g = new Graphics();

		loadedMapState = Launcher_HappyEnd.getMapState();
		elements = new ArrayList<>();
		map = new Image(MAP_PATH + ".png", false, Image.FILTER_NEAREST);
		pictureW = map.getWidth();
		pictureH = map.getHeight();
		screenW = container.getWidth();
		screenH = container.getHeight();
		minimapH = minimapW * pictureH / pictureW;
		minimapY = 20;
		minimapX = screenW - 20 - minimapW;
		input = container.getInput();
		commands = new MapCommands(container);
		hud = new MapHud(container, player);

		changeZoom(2f);

		initMiniMap();

		Log.info(" Map inited !\n");
	}

	@Override
	public void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		map.draw(0, 0, screenW, screenH, mapX1, mapY1, mapX2, mapY2);
		for (Element element : elements)
			element.draw(mapX, mapY, a, b, zoom, mapMX, mapMY);
		hud.setFps(container.getFPS());
		hud.render(container, g);
		hud.drawCharCircles();
		drawMiniMap();
		if (hidesBoxVisible) {
			Color oldColor = g.getColor();
			g.setColor(Color.red);
			g.drawRect(minimapX, minimapY, minimapW, minimapH);
			g.setColor(oldColor);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException {
		super.update(container, game, deltaTime);
		hud.setDeltaTime(deltaTime);
		calculMap(container);
		if (commands.pauseMenu()) {
			hud.showPauseMenu();
		}
	}
	
	@Override
	public void showHitBox(boolean show) {
		super.showHitBox(show);
		hud.showHitBox(show);
	}

	private void calculMap(GameContainer container) {

		float delta = getTime() * zoom / moveSensitivity, mouseX = input.getMouseX(), mouseY = input.getMouseY();

		if (commands.goLeft() && mapX1 > 0)
			mapX += -delta;
		if (commands.goRight() && mapX2 < pictureW)
			mapX += delta;
		if (commands.goUp() && mapY1 > 0)
			mapY += -delta;
		if (commands.goDown() && mapY2 < pictureH)
			mapY += delta;
		changeZoom(commands.changeZoom());

		// Map top right hand corner coordinates drawed on screen
		mapX1 = mapX + a;
		mapY1 = mapY + b;

		// Collisions right and top sides
		if (mapX1 < -1) {
			mapX = -a;
			mapX1 = mapX + a;
		}
		if (mapY1 < -1) {
			mapY = -b;
			mapY1 = mapY + b;
		}

		// Map dimensions drawed on the screen
		mapW = pictureW / zoom;
		mapH = pictureH / zoom;

		// Map bottom left hand corner coordinates drawed on screen
		mapX2 = mapX1 + mapW; // max : pictureW
		mapY2 = mapY1 + mapH;

		// The position of the mouse on the map (on the all map picture)
		mapMX = mapX1 + mouseX * (mapW / screenW);
		mapMY = mapY1 + mouseY * (mapH / screenH);

		// Collisions left and bottom sides
		if (mapX2 > pictureW + 1) {
			mapX1 = pictureW - pictureW / zoom;
			mapX2 = mapX1 + pictureW / zoom;
		}
		if (mapY2 > pictureH + 1) {
			mapY1 = pictureH - pictureH / zoom;
			mapY2 = mapY1 + pictureH / zoom;
		}

		hud.setInfo(mouseX, mouseY, zoom, mapX1, mapY1, mapMX, mapMY);
	}

	private void drawMiniMap() throws SlickException {
		float x2 = minimapX + minimapW, y2 = minimapY + minimapH;
		map.draw(minimapX, minimapY, x2, y2, 0, 0, pictureW, pictureH);
		g.setColor(Color.white);
		g.drawRect(minimapX + mapX1 / pictureW * minimapW, minimapY + mapY1 / pictureH * (minimapW * pictureH / pictureW), (x2 - minimapX) / zoom - 2, (minimapH) / zoom - 2);
		hud.drawMinimap(minimapX - 4, minimapY - 4, minimapW + 8, minimapH + 8);
		miniMapClick.setPosition(minimapX, minimapY);
		miniMapClick.verif();
	}

	private void initMiniMap() {
		miniMapClick = new InvisibleClickableZone(minimapX, minimapY, minimapW, minimapH);
		miniMapClick.setMouseListener(new MouseDragListener() {
			@Override
			public void dragged(int changeX, int changeY) throws SlickException {
				minimapX += changeX;
				minimapY += changeY;
			}
		});
	}

	public void addElement(Element element) {
		elements.add(element);
	}

	// Seters
	private void changeZoom(float change) {
		if (change == 0)
			return;
		if ((change + zoom) < zoomMax && (change + zoom) > zoomMin) {
			zoom += change;
			a = ((pictureW * zoom) / 2 - (screenW) / 2) / zoom;
			b = ((pictureH * zoom) / 2 - (screenH) / 2) / zoom;
		}
	}

	public static void setMoveSensitivity(float move) {
		loadedMapState.moveSensitivity = move;
	}

	public static void setResolution(float width, float height) {
		loadedMapState.screenH = height;
		loadedMapState.screenW = width;
	}

	// Geters
	public static MapHud getMapHud() {
		return loadedMapState.hud;
	}

	public static float getMouseMapPointerX() {
		return loadedMapState.mapMX;
	}

	public static float getMouseMapPointerY() {
		return loadedMapState.mapMY;
	}

	public static float loadedMapState() {
		return loadedMapState.moveSensitivity;
	}

	public float[] getResolution() {
		return new float[] { loadedMapState.screenH, loadedMapState.screenW };
	}
}
