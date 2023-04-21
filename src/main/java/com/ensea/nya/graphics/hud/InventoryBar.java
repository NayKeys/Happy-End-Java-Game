package com.ensea.nya.graphics.hud;

import java.util.ArrayList;

import com.ensea.nya.game.entities.characters.PlayerCharacter;
import com.ensea.nya.game.items.InventoryItem;
import com.ensea.nya.graphics.components.SmoothButton;
import com.ensea.nya.graphics.components.listeners.mouseListeners.ClickListener;
import com.ensea.nya.graphics.menus.mapState.HudComponent;
import com.ensea.nya.utilities.Smooth;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class InventoryBar extends HudComponent {

	private static final String PATH = MapHud.HUD_PATH + "inventoryBar/";

	private SmoothButton button;
	private Image inventoryBar, itemCadre;
	private float invW, invMin, invMax, invY, invX, invH, itemY, itemX;
	private ArrayList<Float> function;
	private boolean out;
	private int index, speed2 = 5;
	private PlayerCharacter player;

	public InventoryBar(float x, float y, PlayerCharacter player) {
		super(x, y);
		this.player = player;
	}

	private static final float ITEM_RELATIVPOSTION_Y = 20, ITEM_RELATIVPOSTION_X = 98, ITEM_SLOT_DIFFERENCE_X = 58;
	public static final int ITEM_SIZE = 48;

	public void init(GameContainer container) throws SlickException {
		button = new SmoothButton(PATH + "inventoryButton", false, new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				out = !out;
			}
		});
		add(button, x, y);
		inventoryBar = new Image(PATH + "inventoryBar.png");
		itemCadre = new Image(PATH + "itemCadre.png");
		invMax = inventoryBar.getWidth();
		invMin = -1;
		invW = invMin;
		invY = y + 4;
		invX = x + 21;
		itemY = y + ITEM_RELATIVPOSTION_Y;
		itemX = x + ITEM_RELATIVPOSTION_X;
		invH = inventoryBar.getHeight();
		function = Smooth.FAST_TO_SLOW_SMOOTH.getPoints(1300, invMin, invMax, 2f);
	}

	private void animations() {
		int i = index, deltaTime = getTime();
		loop : while (index < i + deltaTime)
			if (index < function.size() && index >= 0)
				if (out)
					if (invW + function.get(index) <= invMax) {
						invW += function.get(index);
						if (index + 1 < function.size())
							index++;
					} else
						break loop;
				else {
					if (index < i - deltaTime * speed2)
						break loop;
					if (invW - function.get(index) > invMin) {
						invW += -function.get(index);
						if (index - 1 >= 0)
							index--;
					} else
						break loop;
				}
			else
				break loop;
	}

	@Override
	public void paint(GUIContext container, Graphics g) throws SlickException {
		animations();
		inventoryBar.draw(invX, invY, invW, invH);
		float itemX = this.itemX;
		for (InventoryItem item : player.getInventory()) {
			item.draw(itemX, itemY);
			itemCadre.draw(itemX-3, itemY-3);
			itemX += ITEM_SLOT_DIFFERENCE_X;
		}
	}
}
