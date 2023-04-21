package com.ensea.nya.app;

import com.ensea.nya.game.game.Party;
import com.ensea.nya.graphics.gamesStates.CharacterSelectionState;
import com.ensea.nya.graphics.gamesStates.MainMenuState;
import com.ensea.nya.graphics.gamesStates.MapState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	private Party currentParty;

	public static final short MAIN_MENU = 0;
	public static final short MAP_DISPLAY = 2;
	public static final short CHAMP_SELECT = 1;
	public static final short BATTLE = 3;
	public static final short ANIM_EDITOR = 4;

	private MapState mapDisplayState;
	private MainMenuState mainMenu;
	private CharacterSelectionState selection;

	// Game Constructor
	public Game(String name) {
		super(name);

		mapDisplayState = new MapState(MAP_DISPLAY);
		selection = new CharacterSelectionState(CHAMP_SELECT);
		mainMenu = new MainMenuState(MAIN_MENU);
		addState(mainMenu);
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		enterState(MAIN_MENU);
	}

	// Geters
	public Party getParty() {
		return currentParty;
	}

	public MapState getMapState() {
		return mapDisplayState;
	}

	public MainMenuState getMainMenuState() {
		return mainMenu;
	}

	public CharacterSelectionState getCharacterSelectionState() {
		return selection;
	}
}
