package com.ensea.nya.graphics.gamesStates;

import static com.ensea.nya.App.changeState;
import static com.ensea.nya.App.getMapState;

import com.ensea.nya.game.entities.characters.AzunaCharacter;
import com.ensea.nya.game.entities.characters.CeliaCharacter;
import com.ensea.nya.game.entities.characters.GreedCharacter;
import com.ensea.nya.game.entities.characters.PlayerCharacter;
import com.ensea.nya.graphics.components.SmoothButton;
import com.ensea.nya.graphics.components.listeners.mouseListeners.ClickListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class CharacterSelectionState extends SimpleGameState {

	public static final String SELECTION_PATH = "textures/gui/characterSelectionMenu/";
	private Image background, presentation, banner;
	private SmoothButton next, previous, select;

	private CharacterOrder characters;
	private PlayerCharacter selected;

	public CharacterSelectionState(short ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Log.info(" GameState init : CharacterSelection");

		input = container.getInput();
		characters = new CharacterOrder(container, new AzunaCharacter(), new GreedCharacter(), new CeliaCharacter());
		selected = characters.init();

		background = new Image(SELECTION_PATH + "backGround.png");
		next = new SmoothButton(SELECTION_PATH + "nextButton", false, new ClickListener() {
					@Override
					public void clicked() throws SlickException {
						selected = characters.next();
					}
				});
		previous = new SmoothButton(SELECTION_PATH + "beforeButton", false, new ClickListener() {
					@Override
					public void clicked() throws SlickException {
						selected = characters.previous();
					}
				});
		select = new SmoothButton(SELECTION_PATH + "selectButton", false, 0.10f, new ClickListener() {
					@Override
					public void clicked() throws SlickException {
						nextState();
					}
				});
		presentation = new Image(SELECTION_PATH + "characterPresentation.png");
		banner = new Image(SELECTION_PATH + "banner.png");
		add(next, 1640, 276);
		add(previous, 799, 276);
		add(select, 990, 919);

		Log.info(" Character Selection inited !\n");
	}

	@Override
	public void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw();
		presentation.draw(-17, 4);
		banner.draw(0, 367);
		selected.selection.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		next.setDeltaTime(delta);
		previous.setDeltaTime(delta);
		select.setDeltaTime(delta);
	}

	private void nextState() {
		getMapState().setCharacter(selected);
		changeState(getMapState());
	}

	public PlayerCharacter[] getCharacters() {
		return characters.getCharacters();
	}

	private class CharacterOrder {
		private PlayerCharacter[] players;
		private short index;

		private CharacterOrder(GameContainer container, PlayerCharacter... characters) throws SlickException {
			players = characters;
			for (PlayerCharacter player : players) {
				player.initSelectionState(container);
			}
		}

		private PlayerCharacter init() {
			index = 0;
			return players[0];
		}

		private PlayerCharacter next() {
			if (++index >= players.length)
				index = 0;
			return players[index];
		}

		private PlayerCharacter previous() {
			if (--index < 0)
				index = (short) (players.length - 1);
			return players[index];
		}

		private PlayerCharacter[] getCharacters() {
			return players;
		}
	}
}
