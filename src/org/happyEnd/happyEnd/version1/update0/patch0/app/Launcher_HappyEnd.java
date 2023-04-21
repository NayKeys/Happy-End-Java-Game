package org.happyEnd.happyEnd.version1.update0.patch0.app;

import java.awt.Font;
import java.io.IOException;

import org.happyEnd.happyEnd.version1.update0.patch0.commands.ConsoleCommands;
import org.happyEnd.happyEnd.version1.update0.patch0.game.game.Party;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.CharacterSelectionState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.MainMenuState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.MapState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.SimpleGameState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud.DevConsoleWindow;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.CursorLoader;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.Transition;

public class Launcher_HappyEnd {

	// Game Elements
	public static final String GAME_NAME = "Happy End";

	// Default Paths
	public static final String CURSOR_PATH = "textures/cursors/";

	// Game
	private static Game game;
	private static AppGameContainer defaultContainer;

	// Fonts
	public static TrueTypeFont defaultFont;
	public static int defaultFontSize = 18;

	public static void changeState(SimpleGameState state) {
		changeState(state, new EmptyTransition(), new EmptyTransition());
	}

	public static void changeState(SimpleGameState state, Transition leave, Transition enter) {
		try {
			cursorLoading();
			game.addState(state);
			game.enterState(state.getID(), leave, enter);
			state.init(defaultContainer, game);
			state.showHitBox(hideBoxVisible);
			cursorDefault();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private static boolean hideBoxVisible;

	public static boolean isHitBoxesVisibles() {
		return hideBoxVisible;
	}

	public static void showHideBox(boolean show) {
		hideBoxVisible = show;
		getCurrentState().showHitBox(show);
	}

	public static void exitGame() {
		defaultContainer.exit();
	}

	// Geters
	public static AppGameContainer getGameContainer() {
		return defaultContainer;
	}

	public static Party getParty() {
		return game.getParty();
	}

	public static MapState getMapState() {
		return game.getMapState();
	}

	public static MainMenuState getMainMenuState() {
		return game.getMainMenuState();
	}

	public static CharacterSelectionState getCharacterSelectionState() {
		return game.getCharacterSelectionState();
	}

	public static SimpleGameState getCurrentState() {
		return (SimpleGameState) game.getCurrentState();
	}
	
	// Cursors
	private static Cursor DEFAULT_CURSOR, HAND_CURSOR, LOADING_CURSOR, MOVE_CURSOR, TEXT_CURSOR, RESIZE_LEFT_CURSOR, RESIZE_TOP_CURSOR, RESIZE_TOP_RIGHT_CURSOR, RESIZE_TOP_LEFT_CURSOR;
	
	public static void changeMouseCursor(Cursor cursor) throws SlickException {
		try {
			Mouse.setNativeCursor(cursor);
			Mouse.updateCursor();
		} catch (LWJGLException e) {
			throw new SlickException("An error as occured while changing mouse cursor", e);
		}
	}
	
	public static void cursorDefault() throws SlickException {
		changeMouseCursor(DEFAULT_CURSOR);
	}
	
	public static void cursorHand() throws SlickException {
		changeMouseCursor(HAND_CURSOR);
	}
	
	public static void cursorLoading() throws SlickException {
		changeMouseCursor(LOADING_CURSOR);
	}
	
	public static void cursorText() throws SlickException {
		changeMouseCursor(TEXT_CURSOR);
	}
	
	public static void cursorMove() throws SlickException {
		changeMouseCursor(MOVE_CURSOR);
	}
	
	public static void cursorResizeTop() throws SlickException {
		changeMouseCursor(RESIZE_TOP_CURSOR);
	}
	
	public static void cursorResizeLeft() throws SlickException {
		changeMouseCursor(RESIZE_LEFT_CURSOR);
	}
	
	public static void cursorResizeTOP_RIGHT() throws SlickException {
		changeMouseCursor(RESIZE_TOP_RIGHT_CURSOR);
	}
	
	public static void cursorResizeTOP_LEFT() throws SlickException {
		changeMouseCursor(RESIZE_TOP_LEFT_CURSOR);
	}

	public static void init() throws SlickException {
		try {
			CursorLoader loader = CursorLoader.get();
			defaultFont = new TrueTypeFont(new Font("Consolas 12", 0, defaultFontSize), true);
			TEXT_CURSOR = loader.getCursor(CURSOR_PATH + "text.png", 7, 14);
			DEFAULT_CURSOR = loader.getCursor(CURSOR_PATH + "normal.png", 0, 0);
			HAND_CURSOR = loader.getCursor(CURSOR_PATH + "link.png", 10, 0);
			LOADING_CURSOR = loader.getCursor(CURSOR_PATH + "loading/0.png", 0, 0);
			MOVE_CURSOR = loader.getCursor(CURSOR_PATH + "move.png", 9, 9);
			RESIZE_TOP_CURSOR = loader.getCursor(CURSOR_PATH + "resizeTop.png", 8, 8);
			RESIZE_LEFT_CURSOR = loader.getCursor(CURSOR_PATH + "resizeLeft.png", 8, 8);
			RESIZE_TOP_RIGHT_CURSOR = loader.getCursor(CURSOR_PATH + "resizeTopRight.png", 8, 8);
			RESIZE_TOP_LEFT_CURSOR = loader.getCursor(CURSOR_PATH + "resizeTopLeft.png", 8, 8);
			SimpleGameState.console = new DevConsoleWindow();
			Mouse.setNativeCursor(DEFAULT_CURSOR);
		} catch (IOException | LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] s) {
		try {
			// ScalableGame scalable = new ScalableGame(game, 1000, 1000, false);
			game = new Game(GAME_NAME);
			defaultContainer = new AppGameContainer(game);
			defaultContainer.setDisplayMode(1920, 1080, true);
			defaultContainer.setShowFPS(false);
			// Display.setResizable(true);
			new ConsoleCommands();
			defaultContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
			exitGame();
		}
	}
}
