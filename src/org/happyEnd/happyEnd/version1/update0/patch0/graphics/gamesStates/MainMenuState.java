package org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates;

import static org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd.changeState;
import static org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd.exitGame;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.commands.ConsoleCommands;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.SmoothButton;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.ClickListener;
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

		Launcher_HappyEnd.init();
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
						changeState(Launcher_HappyEnd.getCharacterSelectionState());
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
						SimpleGameState.console.println("{Auto-Generated Credits} :\nGameDisgner : Benoi (nom de famille inconnu) @Mäjesté\nDeveloper : Yan Regojo @NayKeys\n");
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
