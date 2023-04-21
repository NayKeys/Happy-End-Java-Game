package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 * A single text field supporting text entry
 * 
 * @author kevin
 */
public class TextField extends Clickable {
	/** The key repeat interval */
	private static final int INITIAL_KEY_REPEAT_INTERVAL = 400;
	/** The key repeat interval */
	private static final int KEY_REPEAT_INTERVAL = 50;

	/** The maximum number of characters allowed to be input */
	private int maxCharacter = 10000;

	/** The value stored in the text field */
	private String valueHided = "";

	/** The font used to render text in the field */
	private Font font;

	/** The border color - null if no border */
	private Color border = Color.white;

	/** The text color */
	private Color text = Color.white;

	/** The background color - null if no background */
	private Color background = new Color(0, 0, 0, 0.5f);

	/** The current cursor position */
	private int cursorPos;

	/** True if the cursor should be visible */
	private boolean visibleCursor = true;

	/** The last key pressed */
	private int lastKey = -1;

	/** The last character pressed */
	private char lastChar = 0;

	/** The time since last key repeat */
	private long repeatTimer;

	/** The text before the paste in */
	private String oldText;

	/** The cursor position before the paste */
	private int oldCursorPos;

	/** True if events should be consumed by the field */
	private boolean consume = true;
	
	private String cursor = "_";

	/**
	 * Create a new text field
	 * 
	 * @param container The container rendering this field
	 * @param font The font to use in the text field
	 * @param x The x coordinate of the top left corner of the text field
	 * @param y The y coordinate of the top left corner of the text field
	 * @param width The width of the text field
	 * @param height The height of the text field
	 * @param listener The listener to add to the text field
	 */
	private ComponentListener defaultListener;
	
	public TextField(GUIContext container, int x, int y, int width, int height, ComponentListener listener) {
		this(container, x, y, width, height);
		addListener(listener);
		this.defaultListener = listener;
		g = new Graphics();
		mouse = new SimpleMouseListener() {
			@Override
			public void clickInside() throws SlickException {
				setFocus(true);
			}
			@Override
			public void clickOutside() throws SlickException {
				
			}
			@Override
			public void entered() throws SlickException {
				
			}
			@Override
			public void enteredOnce() throws SlickException {
				Launcher_HappyEnd.cursorText();
			}
			@Override
			public void exited() throws SlickException {
				
			}
			@Override
			public void exitedOnce() throws SlickException {
				Launcher_HappyEnd.cursorDefault();
			}
			@Override
			public void dragged(int changeX, int changeY) throws SlickException {
				
			}
			@Override
			public void holdClick() throws SlickException {
				
			}
		};
	}
	
	public ComponentListener getDefaultListener() {
		return defaultListener;
	}
	
	public void setDefaultListener(ComponentListener listener) {
		this.defaultListener = listener;
	}
	
	public boolean haveOtherListener() {
		return listeners.size() > 1;
	}

	/**
	 * Create a new text field
	 * 
	 * @param container
	 *            The container rendering this field
	 * @param font
	 *            The font to use in the text field
	 * @param x
	 *            The x coordinate of the top left corner of the text field
	 * @param y
	 *            The y coordinate of the top left corner of the text field
	 * @param width
	 *            The width of the text field
	 * @param height
	 *            The height of the text field
	 */
	private TextField(GUIContext container, int x, int y, int width, int height) {
		super();

		this.font = Launcher_HappyEnd.defaultFont;

		background = null;
		border = null;
		setLocation(x, y);
		this.w = width;
		this.h = height;
	}

	/**
	 * Indicate if the input events should be consumed by this field
	 * 
	 * @param consume
	 *            True if events should be consumed by this field
	 */
	public void setConsumeEvents(boolean consume) {
		this.consume = consume;
	}

	/**
	 * Deactivate the key input handling for this field
	 */
	public void deactivate() {
		setFocus(false);
	}

	/**
	 * Moves the component.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the background color. Set to null to disable the background
	 * 
	 * @param color
	 *            The color to use for the background
	 */
	public void setBackgroundColor(Color color) {
		background = color;
	}

	/**
	 * Set the border color. Set to null to disable the border
	 * 
	 * @param color
	 *            The color to use for the border
	 */
	public void setBorderColor(Color color) {
		border = color;
	}

	/**
	 * Set the text color.
	 * 
	 * @param color
	 *            The color to use for the text
	 */
	public void setTextColor(Color color) {
		text = color;
	}
	
	private int time;
	private Graphics g;
	public static final int TIME = 550;
	@Override
	public void paint(GUIContext container, Graphics g2) {
		if (hideText) {
			value = "";
			for (int i = 0; i < valueHided.length(); i++)
				value += hidingChar;
		} else
			value = valueHided;
		
		time += getTime();
		if (time > TIME)
			if (time > 2*TIME)
				time = 0;
			else
				cursor = "  ";
		else
			cursor = "_";
		
		if (lastKey != -1) {
			if (input.isKeyDown(lastKey)) {
				if (repeatTimer < System.currentTimeMillis()) {
					repeatTimer = System.currentTimeMillis() + KEY_REPEAT_INTERVAL;
					keyPressed(lastKey, lastChar);
				}
			} else {
				lastKey = -1;
			}
		}
		Rectangle oldClip = g.getClip();
		g.setWorldClip(x, y, w, h);

//		 Someone could have set a color for me to blend...
		Color clr = g.getColor();

		if (background != null) {
			g.setColor(background.multiply(clr));
			g.fillRect(x, y, w, h);
		}
		g.setColor(text.multiply(clr));
		Font temp = g.getFont();

		int cpos = font.getWidth(value.substring(0, cursorPos));
		int tx = 0;
		if (cpos > w) {
			tx = (int) (w - cpos - font.getWidth(cursor));
		}

		g.translate(tx + 2, 0);
		g.setFont(font);
		g.drawString(value, x + 1, y + 1);

		if (hasFocus() && visibleCursor) {
			g.drawString(cursor, x + 1 + cpos + 2, y + 1);
		}

		g.translate(-tx - 2, 0);

		if (border != null) {
			g.setColor(border.multiply(clr));
			g.drawRect(x, y, w, h);
		}
		g.setColor(clr);
		g.setFont(temp);
		g.clearWorldClip();
		g.setClip(oldClip);
	}

	private boolean deleteText;
	
	public void deleteTextAfterGet(boolean delete) {
		this.deleteText = delete;
	}
	
	public String getText() {
		String output = valueHided;
		if (deleteText)
			setText("");
		return output;
	}

	/**
	 * Set the value to be displayed in the text field
	 * 
	 * @param value
	 *            The value to be displayed in the text field
	 */
	public void setText(String value) {
		this.valueHided = value;
		if (cursorPos > value.length()) {
			cursorPos = value.length();
		}
	}

	/**
	 * Set the position of the cursor
	 * 
	 * @param pos
	 *            The new position of the cursor
	 */
	public void setCursorPos(int pos) {
		cursorPos = pos;
		if (cursorPos > valueHided.length()) {
			cursorPos = valueHided.length();
		}
	}

	/**
	 * Indicate whether the mouse cursor should be visible or not
	 * 
	 * @param visibleCursor
	 *            True if the mouse cursor should be visible
	 */
	public void setCursorVisible(boolean visibleCursor) {
		this.visibleCursor = visibleCursor;
	}

	/**
	 * Set the length of the allowed input
	 * 
	 * @param length
	 *            The length of the allowed input
	 */
	public void setMaxLength(int length) {
		maxCharacter = length;
		if (valueHided.length() > maxCharacter) {
			valueHided = valueHided.substring(0, maxCharacter);
		}
	}

	/**
	 * Do the paste into the field, overrideable for custom behaviour
	 * 
	 * @param text
	 *            The text to be pasted in
	 */
	protected void doPaste(String text) {
		recordOldPosition();

		for (int i = 0; i < text.length(); i++) {
			keyPressed(-1, text.charAt(i));
		}
	}

	/**
	 * Record the old position and content
	 */
	protected void recordOldPosition() {
		oldText = getText();
		oldCursorPos = cursorPos;
	}

	/**
	 * Do the undo of the paste, overrideable for custom behaviour
	 * 
	 * @param oldCursorPos
	 *            before the paste
	 * @param oldText
	 *            The text before the last paste
	 */
	protected void doUndo(int oldCursorPos, String oldText) {
		if (oldText != null) {
			setText(oldText);
			setCursorPos(oldCursorPos);
		}
	}

	private boolean hideText;
	private String value = "";
	private String hidingChar = "*";
	
	public String getHidingChar() {
		return hidingChar;
	}
	
	public void setHidingChar(String hider) {
		this.hidingChar = hider;
	}
	
	public void hideText(boolean hide) {
		this.hideText = hide;
	}
	
	public boolean isHidingText() {
		return hideText;
	}
	
	public void keyPressed(int key, char c) {
		if (hasFocus()) {
			if (key != -1) {
				if ((key == Input.KEY_V) && ((input.isKeyDown(Input.KEY_LCONTROL)) || (input.isKeyDown(Input.KEY_RCONTROL)))) {
					String text = Sys.getClipboard();
					if (text != null) {
						doPaste(text);
					}
					return;
				}
				if ((key == Input.KEY_Z) && ((input.isKeyDown(Input.KEY_LCONTROL)) || (input.isKeyDown(Input.KEY_RCONTROL)))) {
					if (oldText != null) {
						doUndo(oldCursorPos, oldText);
					}
					return;
				}

				// alt and control keys don't come through here
				if (input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)) {
					return;
				}
				if (input.isKeyDown(Input.KEY_LALT) || input.isKeyDown(Input.KEY_RALT)) {
					return;
				}
			}

			if (lastKey != key) {
				lastKey = key;
				repeatTimer = System.currentTimeMillis() + INITIAL_KEY_REPEAT_INTERVAL;
			} else {
				repeatTimer = System.currentTimeMillis() + KEY_REPEAT_INTERVAL;
			}
			lastChar = c;

			if (key == Input.KEY_LEFT) {
				if (cursorPos > 0) {
					cursorPos--;
				}
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			} else if (key == Input.KEY_RIGHT) {
				if (cursorPos < valueHided.length()) {
					cursorPos++;
				}
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			} else if (key == Input.KEY_BACK) {
				if ((cursorPos > 0) && (valueHided.length() > 0)) {
					if (cursorPos < valueHided.length()) {
						valueHided = valueHided.substring(0, cursorPos - 1) + valueHided.substring(cursorPos);
					} else {
						valueHided = valueHided.substring(0, cursorPos - 1);
					}
					cursorPos--;
				}
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			} else if (key == Input.KEY_DELETE) {
				if (valueHided.length() > cursorPos) {
					valueHided = valueHided.substring(0, cursorPos) + valueHided.substring(cursorPos + 1);
				}
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			} else if ((c < 300) && (c > 31) && (valueHided.length() < maxCharacter)) {
				time = 0;
				if (cursorPos < valueHided.length()) {
					valueHided = valueHided.substring(0, cursorPos) + c + valueHided.substring(cursorPos);
				} else {
					valueHided = valueHided.substring(0, cursorPos) + c;
				}
				cursorPos++;
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			} else if (key == Input.KEY_RETURN) {
				notifyListeners();
				// Nobody more will be notified
				if (consume) {
					container.getInput().consumeEvent();
				}
			}

		}
	}

	/**
	 * @see org.newdawn.slick.gui.AbstractComponent#setFocus(boolean)
	 */
	public void setFocus(boolean focus) {
		lastKey = -1;

		super.setFocus(focus);
	}
}
