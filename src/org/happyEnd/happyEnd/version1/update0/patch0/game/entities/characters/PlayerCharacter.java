package org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.Fighter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.ghouls.Ghoul;
import org.happyEnd.happyEnd.version1.update0.patch0.game.game.Party;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.Shield;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.Slingshot;
import org.happyEnd.happyEnd.version1.update0.patch0.game.items.fightItems.weapons.Weapon;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.Component;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.TextZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.MapZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.NormalZone;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public abstract class PlayerCharacter extends Fighter {

	// Class Fields
	protected Weapon weapon;
	protected Shield shield;
	private MapZone currentZone;
	public Slingshot slingshot;
	public String history;

	// Animations
	protected Graphics g;
	
	public SelectionState selection;

	// Class Constructor
	public PlayerCharacter() {
		shield = new Shield();
		slingshot = new Slingshot();
	}

	// Class Methods
	public Weapon getWeapon() {
		return weapon;
	}

	public Shield getShield() {
		return shield;
	}

	@Override
	public void defeat(Fighter winner) {
		switch (winner.getID()) {
		case Ghoul.ID:
			currentZone = Party.getMap().moveZone(this, currentZone, (short) -2);
			break;
		}
	}

	public void yourTurn() {

	}
	
	public void initSelectionState(GameContainer container) throws SlickException {
		selection = new SelectionState(container);
	}

	// Seters
	public void setMapZone(NormalZone zone) {
		currentZone = zone;
	}

	public void setShieldState(short state) {
		if (state == shield.getState())
			return;

		if (state == Shield.NORMAL) {
			// TODO new Shield !
		} else if (state == Shield.BROKEN) {
			// TODO shield broked up !
		} else {
			// TODO Sell Shield or bullshit !
		}

		shield.setState(state);
	}

	// Geters
	public boolean haveShield() {
		return shield.getState() == Shield.NORMAL;
	}

	public MapZone getZone() {
		return currentZone;
	}

	public String getHistory() {
		return history;
	}

	public class SelectionState extends Component {
		// Default Paths
		protected static final String SELECTION_PATH = "textures/gui/characterSelectionMenu/";

		// Selection Game State
		private Image characterPaint;
		public boolean alreadySelected = false;
		private TextZone historyZone;
		
		public SelectionState(GameContainer container) throws SlickException {
			characterPaint = new Image(SELECTION_PATH + getNamePath() + ".png");
			historyZone = new TextZone(history, 300, 484);
			historyZone.setColor(Color.yellow);
			add(historyZone, 123, 470);
		}

		@Override
		protected void paint(GUIContext container, Graphics g) throws SlickException {
			characterPaint.draw(1047, 20);
		}
	}

	// Graphics Geters
	public abstract String getNamePath();
}
