package com.ensea.nya.graphics.gamesStates;

import static com.ensea.nya.App.changeState;
import static com.ensea.nya.App.exitGame;

import com.ensea.nya.App;
import com.ensea.nya.commands.ConsoleCommands;
import com.ensea.nya.graphics.components.SmoothButton;
import com.ensea.nya.graphics.components.listeners.mouseListeners.ClickListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class MainMenuState extends SimpleGameState {

	public static final String MAIN_MENU_PATH = "textures/gui/mainMenu/";

	// Graphisc elements
	private Image backGround;
	private SmoothButton quit, options, credits;
	private SmoothButton play;

	public MainMenuState(short ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Log.info(" GameState init : Main Menu");

		App.init();
		input = container.getInput();
		backGround = new Image(MAIN_MENU_PATH + "background.png");

		quit = new SmoothButton(MAIN_MENU_PATH + "quitButton", false, new ClickListener() {
					@Override
					public void clicked() {
						Log.info("{Game Exit} executed from GameState : <MainMenuState>, exitButton");
						exitGame();
					}
				});
		play = new SmoothButton(MAIN_MENU_PATH + "playButton", false, new ClickListener() {
					@Override
					public void clicked() {
						changeState(App.getCharacterSelectionState());
					}
				});
		options = new SmoothButton(MAIN_MENU_PATH + "optionsButton", false, new ClickListener() {
					@Override
					public void clicked() {
						try {
							ConsoleCommands.showOptions();
						} catch (SlickException e) {
							e.printStackTrace();
						}
					}
				});
		credits = new SmoothButton(MAIN_MENU_PATH + "creditsButton", false, new ClickListener() {
					@Override
					public void clicked() {
						SimpleGameState.console.println("{Auto-Generated Credits} :\nGameDisgner : Benoi (nom de famille inconnu) @M�jest�\nDeveloper : Yan Regojo @NayKeys\n");
					}
				});
		add(quit, 24, 801);
		add(play, 24, 99);
		add(options, 24, 333);
		add(credits, 24, 567);
		Log.info(" Main Menu inited !\n");
	}

	@Override
	public void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		backGround.draw();
	}
}
