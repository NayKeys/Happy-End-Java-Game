package com.ensea.nya.graphics.components;

import com.ensea.nya.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Element {

	protected float mapX, mapY, w, h;
	protected Image element;
	protected SimpleMouseListener mouse;
	protected Input input;

	/**
	 * @param path,
	 *            the path of the element's picture (use a png, is recommanded)
	 * @param mapX,
	 *            the position of the element on the map based on absolute map
	 *            coordinates
	 * @param mapY,
	 *            the position of the element on the map based on absolute map
	 *            coordinates
	 */
	public Element(String path, float mapX, float mapY) throws SlickException {
		element = new Image(path);
		this.mapX = mapX;
		this.mapY = mapY;
		this.w = element.getWidth();
		this.h = element.getHeight();
	}

	public void draw(float mapPositionX, float mapPositionY, float a, float b,
			float zoom, float mouseMapX, float mouseMapY)
			throws SlickException {
		element.draw(mapPositionX - mapX + a, mapPositionY - mapY + b, w / zoom,
				h / zoom);
		if (mouseMapX >= mapX && mouseMapX <= mapX + w && mouseMapY >= mapY && mouseMapY <= mapY + h) {
			mouse.entered();
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				mouse.clickInside();
		} else
			mouse.exited();
	}

	public void setMouseListener(SimpleMouseListener mouse) {
		this.mouse = mouse;
	}

	public void setInput(Input input) {
		this.input = input;
	}
}
