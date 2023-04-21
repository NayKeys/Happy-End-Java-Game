package org.happyEnd.happyEnd.version1.update0.patch0.commands;

import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.KEYS;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_DOWN;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_LEFT;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_RIGHT;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_UP;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_ZOOM_IN;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.MAP_ZOOM_OUT;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.MapCommands.CommandList.PAUSE_MENU;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class MapCommands {

	private Input input;
	private MouseListener mouse;
	private float percentW, percentH, screenW, screenH, zoom;
	private boolean zoomChangeGeted;

	// User Settings
	private float screenPercentCameraMovement = 0.05f;

	/** zoomSensitivity, more it's higher more zoomSensitivity is weak*/
	private float zoomSensitivity = 1000f;

	public MapCommands(GameContainer container) {
		this.screenW = container.getWidth();
		this.screenH = container.getHeight();
		this.percentW = screenW * screenPercentCameraMovement;
		this.percentH = screenH * screenPercentCameraMovement;
		this.input = container.getInput();
		initMouseListener();
		input.addMouseListener(mouse);
	}

	public boolean goLeft() {
		return input.getMouseX() <= percentW || input.isKeyDown(KEYS.get(MAP_LEFT));
	}

	public boolean goRight() {
		return input.getMouseX() >= screenW - percentW || input.isKeyDown(KEYS.get(MAP_RIGHT));
	}

	public boolean goUp() {
		return input.getMouseY() <= percentH || input.isKeyDown(KEYS.get(MAP_UP));
	}

	public boolean goDown() {
		return input.getMouseY() >= screenH - percentH || input.isKeyDown(KEYS.get(MAP_DOWN));
	}
	
	public boolean pauseMenu() {
		return input.isKeyPressed(KEYS.get(PAUSE_MENU));
	}

	public float changeZoom() {
		if (input.isKeyPressed(KEYS.get(MAP_ZOOM_IN)))
			return 0.1f;
		if (input.isKeyPressed(KEYS.get(MAP_ZOOM_OUT)))
			return -0.1f;
		float output = zoomChangeGeted ? 0 : zoom;
		zoomChangeGeted = true;
		return output;
	}

	public static class CommandList {

		public static final int MAP_UP = 0, MAP_DOWN = 2, MAP_LEFT = 3, MAP_RIGHT = 4, MAP_ZOOM_IN = 5, MAP_ZOOM_OUT = 6, PAUSE_MENU = 7;

		public static final HashMap<Integer, Integer> KEYS = initKeysList();

		private static HashMap<Integer, Integer> initKeysList() {
			HashMap<Integer, Integer> output = new HashMap<>();
			output.put(MAP_DOWN, Input.KEY_DOWN);
			output.put(MAP_UP, Input.KEY_UP);
			output.put(MAP_LEFT, Input.KEY_LEFT);
			output.put(MAP_ZOOM_IN, Input.KEY_ADD);
			output.put(MAP_ZOOM_OUT, Input.KEY_SUBTRACT);
			output.put(MAP_RIGHT, Input.KEY_RIGHT);
			output.put(PAUSE_MENU, Input.KEY_ESCAPE);
			return output;
		}
	}

	private void initMouseListener() {
		mouse = new MouseListener() {

			@Override
			public void setInput(Input input) {
			}

			@Override
			public boolean isAcceptingInput() {
				return true;
			}

			@Override
			public void inputStarted() {
			}

			@Override
			public void inputEnded() {
			}

			@Override
			public void mouseWheelMoved(int change) {
				zoom = (float) change / zoomSensitivity;
				zoomChangeGeted = false;
			}

			@Override
			public void mouseReleased(int button, int x, int y) {
			}

			@Override
			public void mousePressed(int button, int x, int y) {
			}

			@Override
			public void mouseMoved(int oldx, int oldy, int newx, int newy) {
			}

			@Override
			public void mouseDragged(int oldx, int oldy, int newx, int newy) {
			}

			@Override
			public void mouseClicked(int button, int x, int y, int clickCount) {
			}
		};
	}
}
