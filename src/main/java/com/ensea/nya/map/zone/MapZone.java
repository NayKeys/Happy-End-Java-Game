package com.ensea.nya.map.zone;

import com.ensea.nya.graphics.components.Element;
import com.ensea.nya.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.newdawn.slick.SlickException;

public abstract class MapZone extends Element {

	public MapZone(String path, float mapX, float mapY) throws SlickException {
		super(path, mapX, mapY);
		mouse = new SimpleMouseListener() {

			@Override
			public void clickInside() throws SlickException {

			}

			@Override
			public void clickOutside() throws SlickException {

			}

			@Override
			public void entered() throws SlickException {

			}

			@Override
			public void exited() throws SlickException {

			}

			@Override
			public void dragged(int changeX, int changeY) throws SlickException {

			}

			@Override
			public void enteredOnce() throws SlickException {
				// TODO Auto-generated method stub

			}

			@Override
			public void exitedOnce() throws SlickException {
				// TODO Auto-generated method stub

			}

			@Override
			public void holdClick() throws SlickException {
				// TODO Auto-generated method stub

			}
		};
	}
}
