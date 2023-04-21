package com.ensea.nya.graphics.menus.mapState;

import com.ensea.nya.graphics.components.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public abstract class HudComponent extends Component {
	public HudComponent() {
	}

	public HudComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public HudComponent(float x, float y, GameContainer container) throws SlickException {
		this(x, y);
		init(container);
	}

	public HudComponent(GameContainer container) throws SlickException {
		init(container);
	}

	public abstract void init(GameContainer container) throws SlickException;
}
