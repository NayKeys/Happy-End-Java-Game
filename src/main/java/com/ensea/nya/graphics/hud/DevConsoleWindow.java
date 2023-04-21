package com.ensea.nya.graphics.hud;

import static com.ensea.nya.app.accounts.Account.connectedAccount;

import com.ensea.nya.commands.ConsoleCommands;
import com.ensea.nya.graphics.components.Clickable;
import com.ensea.nya.graphics.components.TextField;
import com.ensea.nya.graphics.components.TextZone;
import com.ensea.nya.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

public class DevConsoleWindow extends Clickable {

	public static final String PATH = "textures/gui/component/devConsoleWindow/";

	private TextZone zoneText;
	public TextField input;
	private Image background;

	public DevConsoleWindow() throws SlickException {
		x = ChatWindow.START_X;
		y = ChatWindow.START_Y;
		w = 413;
		h = 28;
		zoneText = new TextZone("", 392, 193);
		background = new Image(PATH + "hud.png");
		input = new TextField(container, (int) (x + 38), (int) (y + 237), 365, 29, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent source) {
				if (!input.haveOtherListener()) {
					String message = input.getText();
					if (!ConsoleCommands.execute(message))
						if (connectedAccount.isRegistered()) {
							println("<" + connectedAccount.getName() + "> : " + message);
						} else
							printlnErr("Vous devez vous connectez pour envoyez des messages : \"/connect\"");
				}
			}
		});
		input.deleteTextAfterGet(true);
		add(zoneText, x + 11, y + 29);
		add(input, x + 38, y + 237);
		initListeners();
	}

	public void addListener(ComponentListener listener) {
		input.addListener(listener);
	}

	public void removeListener(ComponentListener listener) {
		input.removeListener(listener);
	}

	public String getText() {
		return input.getText();
	}

	public void println(String toPrint) {
		System.out.println(toPrint);
		zoneText.addLine(toPrint);
	}

	public void printlnErr(String errorMsg) {
		System.err.println(errorMsg);
		zoneText.addLine(errorMsg);
	}

	@Override
	protected void paint(GUIContext container, Graphics g) throws SlickException {
		background.draw(x, y);
	}

	private void initListeners() {
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
			public void enteredOnce() throws SlickException {

			}

			@Override
			public void exited() throws SlickException {

			}

			@Override
			public void exitedOnce() throws SlickException {

			}

			@Override
			public void dragged(int changeX, int changeY) throws SlickException {
				setPosition(x + changeX, y + changeY);
			}

			@Override
			public void holdClick() throws SlickException {

			}
		};
	}
}
