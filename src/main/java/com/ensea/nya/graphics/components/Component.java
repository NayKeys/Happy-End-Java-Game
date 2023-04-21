package com.ensea.nya.graphics.components;

import java.util.ArrayList;

import com.ensea.nya.App;
import com.ensea.nya.utilities.HitBoxRectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public abstract class Component extends AbstractComponent {
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

	public void setDeltaTime(int delta) {
		timeElapsed += delta;
		timeGeted = false;
		for (Component component : components)
			component.setDeltaTime(delta);
	}

	protected float x, y, w, h;
	public Input input;
	protected boolean showHitBox, showComponents;
	private boolean entered;
	protected static Graphics hitBoxDrawer;
	protected Shape hitBox;

	static {
		hitBoxDrawer = new Graphics();
		hitBoxDrawer.setColor(Color.red);
	}

	public Component() {
		super(App.getGameContainer());
		this.input = App.getGameContainer().getInput();
		showComponents = true;
		hitBox = new Rectangle(0, 0, 0, 0);
	}

	public boolean isEntered() {
		if (entered) {
			if (showComponents) {
				ArrayList<Component> components = App.getCurrentState().components.getAllComponents();
				for (int i = components.indexOf(this) + 1; i < components.size(); i++)
					if (components.get(i).isEntered())
						return false;
			}
			return true;
		}
		return false;
	}

	public void showHitBox(boolean show) {
		this.showHitBox = show;
		for (Component component : components)
			component.showHitBox(show);
	}

	public void setPosition(float x, float y) {
		for (Component c : components)
			c.setPosition(c.x + x-this.x, c.y + y-this.y);
		this.x = x;
		this.y = y;
		setHitboxPosition(x, y);
	}

	private void setHitboxPosition(float x, float y) {
		hitBox.setLocation(x, y);
		refreshPosition();
	}

	public void setDimension(float w, float h) {
		this.w = w;
		this.h = h;
		setHitboxSize(w, h);
	}
 	private void setHitboxSize(float w, float h) {
		if (hitBox instanceof Rectangle) {
			((Rectangle) hitBox).setSize(w, h);
		} else if (hitBox instanceof Circle)
			if (w < h)
				((Circle) hitBox).radius = w;
			else
				((Circle) hitBox).radius = h;
		else if (hitBox instanceof RoundedRectangle)
			((RoundedRectangle) hitBox).setSize(w, h);
		else if (hitBox instanceof Ellipse) {
			((Ellipse) hitBox).setRadius1(w);
			((Ellipse) hitBox).setRadius2(h);
		} else throw new IllegalArgumentException("Unknown Shape");
		refreshSize();
	}

 	private void refreshPosition() {
		oldX = x;
		oldY = y;
	}

	private void refreshSize() {
		oldW = w;
		oldH = h;
	}

	public void setBounds(float x, float y, float w, float h) {
		setPosition(x, y);
		setDimension(w, h);
	}

	public void setHitBox(Shape hitBox) {
		this.hitBox = hitBox;
	}

	public void setBounds(HitBoxRectangle hitBox) {
		setBounds(hitBox.getPositionX(), hitBox.getPositionY(), hitBox.getWidth(), hitBox.getHeight());
	}

	private float oldX, oldY, oldW, oldH;

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException {
		if (oldX != x || oldY != y) {
			setHitboxPosition(x, y);
		} if (oldW != w || oldH != h) {
			setHitboxSize(w, h);
		}

		checkEntered();

		paint(container, g);
		if (showComponents)
			renderComponents(container, g);
		if (showHitBox) {
			hitBoxDrawer.draw(hitBox);
		}
	}

	public void checkEntered() {
		entered = hitBox.contains(input.getMouseX(), input.getMouseY());
	}

	protected abstract void paint(GUIContext container, Graphics g) throws SlickException;

	protected ArrayList<Component> components = new ArrayList<>();

	protected void renderComponents(GUIContext container, Graphics g) throws SlickException {
		for (Component component : components)
			component.render(container, g);
	}

	public ArrayList<Component> getAllComponents() {
		ArrayList<Component> output = new ArrayList<>(components);
		for (Component component : components)
			output.addAll(component.getAllComponents());
		return output;
	}

	public boolean isShowingComponents() {
		return showComponents;
	}

	public void showComponent(boolean show) {
		showComponents = show;
	}

	public void add(Component component, float x, float y) {
		add(component);
		component.setPosition(x, y);
	}

	public void add(Component component, float x, float y, int classement) {
		components.add(classement, component);
		component.setPosition(x, y);
	}

	public void add(Component component) {
		components.add(component);
	}

	@Override
	public int getX() {
		return (int) x;
	}

	@Override
	public int getY() {
		return (int) y;
	}

	@Override
	public int getWidth() {
		return (int) w;
	}

	@Override
	public int getHeight() {
		return (int) h;
	}

	@Override
	public void setLocation(int x, int y) {
		if (components != null)
			setPosition(x, y);
	}
}
