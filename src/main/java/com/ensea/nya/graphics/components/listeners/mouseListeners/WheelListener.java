package com.ensea.nya.graphics.components.listeners.mouseListeners;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public interface WheelListener extends MouseListener {
	public void mouseWheelMoved(int change);

	@Override
	default void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	default boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	default void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	default void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	default void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub

	}

	@Override
	default void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	default void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	default void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	default void setInput(Input input) {
		// TODO Auto-generated method stub

	}

}
