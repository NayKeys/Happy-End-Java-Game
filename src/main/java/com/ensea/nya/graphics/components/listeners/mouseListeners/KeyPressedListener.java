package com.ensea.nya.graphics.components.listeners.mouseListeners;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public interface KeyPressedListener extends KeyListener {
	@Override
	default void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	default void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	default boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	default void setInput(Input input) {
		// TODO Auto-generated method stub

	}
}
