package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners;

import org.newdawn.slick.SlickException;

public interface SimpleMouseListener {
	public void clickInside() throws SlickException;
	public void clickOutside() throws SlickException;
	public void entered() throws SlickException;
	public void enteredOnce() throws SlickException;
	public void exited() throws SlickException;
	public void exitedOnce() throws SlickException;
	public void dragged(int changeX, int changeY) throws SlickException;
	public void holdClick() throws SlickException;
}
