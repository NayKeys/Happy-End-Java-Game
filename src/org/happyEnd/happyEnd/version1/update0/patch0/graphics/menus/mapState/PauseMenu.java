package org.happyEnd.happyEnd.version1.update0.patch0.graphics.menus.mapState;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Game;
import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.commands.ConsoleCommands;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.AzunaCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.ghouls.Ghoul;
import org.happyEnd.happyEnd.version1.update0.patch0.game.fights.Fight;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.Clickable;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.SmoothButton;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.ClickListener;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.ClickOutsideListener;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.BattleState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud.MapHud;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.util.Log;

public class PauseMenu extends Clickable {

	private static final String PATH = MapHud.HUD_PATH + "pauseMenu/";

	private Image hud;
	private SmoothButton quit, options, returnButton, devTools;
	public SmoothButton menuButton;

	private float menuX, menuY;

	public PauseMenu() {
		setUnclickable(true);
		showComponents = false;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		menuX = x + 5;
		menuY = y + 5;
	}

	public void init(GameContainer container) throws SlickException {
		hud = new Image(PATH + "pauseMenu.png");
		w = hud.getWidth();
		h = hud.getHeight();
		initMouseListener();
		menuButton = new SmoothButton(PATH + "menuButton", true, 0.10f, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				showPauseMenu(!showComponents);
			}
		});
		quit = new SmoothButton(PATH + "quitButton", false, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				Log.info("{Game Exit} executed from GameState : <MapState>, pauseMenu, exit Button");
				Launcher_HappyEnd.exitGame();
			}
		});
		options = new SmoothButton(PATH + "optionsButton", false, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				ConsoleCommands.showOptions();
			}
		});
		returnButton = new SmoothButton(PATH + "returnButton", false, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				showPauseMenu(false);
			}
		});
		devTools = new SmoothButton(PATH + "devToolsButton", false, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				Launcher_HappyEnd.changeState(new BattleState(Game.BATTLE, new Fight(new AzunaCharacter(), new Ghoul())));
			}
		});
		add(quit, menuX + 54, menuY + 280);
		add(options, menuX + 54, menuY + 202);
		add(devTools, menuX + 54, menuY + 124);
		add(returnButton, menuX + 459, menuY + 333);
		menuButton.setPosition(x + 10, y + 10);
	}

	@Override
	public void paint(GUIContext container, Graphics g) throws SlickException {
		if (showComponents) {
			verif();
			hud.draw(menuX, menuY);
		}
		menuButton.render(container, g);
	}
	
	@Override
	public void setDeltaTime(int delta) {
		if (showComponents) {
			super.setDeltaTime(delta);
		}
		menuButton.setDeltaTime(delta);
	}

	public void showPauseMenu(boolean show) throws SlickException {
		showComponents = show;
		if (!show) {
			quit.componentClose();
			devTools.componentClose();
			options.componentClose();
			returnButton.componentClose();
		}
	}

	private void initMouseListener() {
		mouse = new ClickOutsideListener() {
			@Override
			public void clickOutside() throws SlickException {
				showPauseMenu(false);
			}
		};
	}
}
