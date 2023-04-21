package org.happyEnd.happyEnd.version1.update0.patch0.graphics.hud;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.TextZone;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.menus.mapState.HudComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class ChatWindow extends HudComponent {
	
	public static final int START_X = 1469, START_Y = 602;

	public ChatWindow() {
		super(START_X, START_Y);
	}
	
	private TextZone chat;
	private TextField input;

	@Override
	public void init(GameContainer container) throws SlickException {
		chat = new TextZone("", 385, 185);
		input = new TextField(container, Launcher_HappyEnd.defaultFont, 11, 216, 393, 33, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent source) {
				print(input.getText());
				input.setText("");
			}
		});
		add(chat, 14, 14);
	}
	
	public void print(String line) {
		chat.addLine(line);
	}

	@Override
	public void paint(GUIContext container, Graphics g) throws SlickException {
	}
}
