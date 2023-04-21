package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public abstract class Clickable extends Component {

	protected int mouseX, mouseY;
	protected SimpleMouseListener mouse;
	private boolean clickOGeted, dragged, unclickable, entered;

	public Clickable() {
		super();
	}

	public Clickable(float x, float y) {
		super();
		setPosition(x, y);
	}

	protected Clickable(float x, float y, float w, float h) {
		super();
		setPosition(x, y);
		setDimension(w, h);
	}

	public void componentClose() throws SlickException {
		mouse.exited();
		mouse.exitedOnce();
	}
	
	public boolean isDragging() {
		return dragged;
	}

	public void verif() throws SlickException {
		int mouseX = input.getMouseX(), mouseY = input.getMouseY();
		boolean clickDown = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		boolean entered = isEntered();

		if ((!clickDown) && (dragged))
			dragged = false;
		if (entered) {
			dragged = clickDown;
			mouse.entered();
			clickOGeted = false;
			if (entered != this.entered)
				mouse.enteredOnce();
			if (!unclickable)
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					mouse.clickInside();
		} else {
			if (entered != this.entered)
				mouse.exitedOnce();
			mouse.exited();
			if (clickDown) {
				if (!clickOGeted) {
					clickOGeted = true;
					mouse.clickOutside();
				}
			}
		}
		
		if (dragged) {
			mouse.holdClick();
			mouse.dragged(mouseX - this.mouseX, mouseY - this.mouseY);
		}
		
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.entered = entered;
	}
	
	@Override
	public final void render(GUIContext container, Graphics g) throws SlickException {
		verif();
		super.render(container, g);
	}

	// Geters
	public float getRelativeMouseX() {
		return mouseX - x;
	}

	public float getRelativeMouseY() {
		return mouseY - y;
	}

	// Seters
	public void setUnclickable(boolean click) {
		this.unclickable = click;
	}

	public void setMouseListener(SimpleMouseListener mouse) {
		this.mouse = mouse;
	}
}
