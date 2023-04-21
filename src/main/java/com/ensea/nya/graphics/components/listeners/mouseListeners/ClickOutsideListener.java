package com.ensea.nya.graphics.components.listeners.mouseListeners;

import org.newdawn.slick.SlickException;

public interface ClickOutsideListener extends SimpleMouseListener {
	default void clickInside() throws SlickException {
	}
	public void clickOutside() throws SlickException;
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
