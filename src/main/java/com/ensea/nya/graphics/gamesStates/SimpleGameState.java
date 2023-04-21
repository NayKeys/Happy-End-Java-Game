package com.ensea.nya.graphics.gamesStates;

import com.ensea.nya.graphics.components.Component;
import com.ensea.nya.graphics.components.ComponentHierarchy;
import com.ensea.nya.graphics.hud.DevConsoleWindow;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class SimpleGameState extends BasicGameState {

	private short ID;
	protected Input input;

	@Override
	public int getID() {
		return ID;
	}

	public void exit() {
		input.removeAllListeners();
	}

	public ComponentHierarchy components;

	public SimpleGameState(short ID) {
		this.ID = ID;
		components = new ComponentHierarchy();
	}

	public void add(Component component) {
		components.add(component);
	}

	public void add(Component component, int classement) {
		components.add(classement, component);
	}

	public void add(Component component, float x, float y) {
		add(component);
		component.setPosition(x, y);
	}

	public void add(Component component, float x, float y, int classement) {
		components.add(classement, component);
		component.setPosition(x, y);
	}

	protected boolean hidesBoxVisible;

	public void showHitBox(boolean show) {
		hidesBoxVisible = show;
		for (Component component : components)
			component.showHitBox(show);
		if (console != null)
			console.showHitBox(show);
	}

	public boolean isHitBoxesVisibles() {
		return hidesBoxVisible;
	}

	private void renderComponents(GUIContext container, Graphics g) throws SlickException {
		for (Component component : components)
			component.render(container, g);
	}

	@Override
	public final void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.input = container.getInput();
		init(container);
	}

	public abstract void init(GameContainer container) throws SlickException;

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException {
		timeElapsed += deltaTime;
		timeGeted = false;
		for (Component component : components)
			component.setDeltaTime(deltaTime);
		if (console != null)
			console.setDeltaTime(deltaTime);
	}


	public static DevConsoleWindow console;

	@Override
	public final void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		paint(container, game, g);
		renderComponents(container, g);
		if (console != null)
			console.render(container, g);
	}

	public abstract void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;


	private int timeElapsed;
	private boolean timeGeted;

	protected int getTime() {
		int output = timeElapsed;
		if (timeGeted)
			output = 0;
		timeGeted = true;
		timeElapsed = 0;
		return output;
	}
}
