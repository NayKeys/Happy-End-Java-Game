package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class InvisibleClickableZone extends Clickable {
	
	public InvisibleClickableZone() {
	}
	
	public InvisibleClickableZone(float x, float y, float w, float h) {
		super(x, y, w, h);
	}
	
	@Override
	public void verif() throws SlickException {
		checkEntered();
		super.verif();
	}

	@Override
	@Deprecated
	public void paint(GUIContext container, Graphics g) throws SlickException {
	}
}
