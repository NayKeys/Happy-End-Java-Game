package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners;

import org.newdawn.slick.SlickException;

public interface ClickInsideListener extends SimpleMouseListener {
	public void clickInside() throws SlickException;
	default void clickOutside() throws SlickException {
	}
	default void entered() throws SlickException {
	}
	default void enteredOnce() throws SlickException {
	}
	default void exited() throws SlickException {
	}
	default void exitedOnce() throws SlickException {
	}
	default void dragged(int changeX, int changeY) throws SlickException {
	}
	default void holdClick() throws SlickException {}
}
