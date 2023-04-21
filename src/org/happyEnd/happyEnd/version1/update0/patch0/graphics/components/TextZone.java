package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

import static org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd.defaultFontSize;

import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.MouseDragListener;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.WheelListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class TextZone extends Component {

	// Default Paths
	private static final String SCROLL_BAR_PATH = "textures/gui/component/textZone/";

	public TextZone(String text, float w, float h) throws SlickException {
		this.text = convertInLines(text, w);
		this.w = w;
		this.h = h;
		scrollBar = new Image(SCROLL_BAR_PATH + "scrollBar.png", false, Image.FILTER_NEAREST);
		bar = new Image(SCROLL_BAR_PATH + "bar.png", false, Image.FILTER_NEAREST);
		barW = scrollBar.getWidth();
		scrollBarZone = new InvisibleClickableZone();
		initMouseListener();
		g = new Graphics();
		g.setFont(textFont);
		this.input.addMouseListener(mouse);
		resizeText();
	}
	
	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		resizeText();
	}
	
	@Override
	public void setDimension(float w, float h) {
		this.w = w;
		this.h = h;
		this.text = convertInLines(getText(), w);
		resizeText();
	}
	
	@Override
	public void setBounds(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.text = convertInLines(getText(), w);
		resizeText();
	}

	// Graphics Elements
	private Image scrollBar, bar;

	// Class Fields
	private ArrayList<String> text;
	private float xBar, barY, barW, barH, maxY, minY, textY, div, lineH, textH;
	private MouseListener mouse;
	private Graphics g;
	private boolean canScroll, visible, showScrollBar = true;
	private InvisibleClickableZone scrollBarZone;

	// User Setting
	public static float scrollSensitivity = 12;
	public short lineSpace = 0;
	public static TrueTypeFont textFont = Launcher_HappyEnd.defaultFont;
	
	@Override
	protected void paint(GUIContext container, Graphics g2) throws SlickException {
		scrollBarZone.setBounds(xBar, barY, barW, barH);
		scrollBarZone.verif();
		visible = true;
		Rectangle oldClip = g.getClip();
		g.setWorldClip(x, y, w, h);
		float y = textY;
		for (String line : text) {
			g.drawString(line, x, y);
			y += lineH;
		}
		g.clearWorldClip();
		g.setClip(oldClip);
		if (canScroll && showScrollBar) {
			scrollBar.draw(xBar, barY, barW, barH);
			bar.draw(xBar, this.y, barW, h);
		}
	}
	
	public void setColor(Color color) {
		g.setColor(color);
	}

	public void addLine(String line) {
		text.addAll(convertInLines(line, w));
		resizeText();
	}
	
	public void newLine() {
		text.add("");
	}
	
	public String getLastLine() {
		if (text.size() == 0)
			return "";
		return text.get(text.size()-1);
	}
	
	public void setLine(int lineIndex, String newLastLine) {
		if (lineIndex >= text.size())
			return;
		text.addAll(lineIndex, convertInLines(newLastLine, w));
		resizeText();
	}
	
	public void addToLine(int lineIndex, String toAdd) {
		if (lineIndex >= text.size())
			return;
		setLine(lineIndex, text.get(lineIndex)+toAdd);
	}
	
	public void addToLastLine(String toAdd) {
		setLastLine(getLastLine()+toAdd);
	}
	
	public void setLastLine(String newLastLine) {
		if (text.size() == 0)
			return;
		setLine(text.size()-1, newLastLine);
	}
	
	public String getLine(int index) {
		if (index >= text.size())
			return "";
		return text.get(index);
	}
	
	public String[] getAllLines()  {
		return text.toArray(new String[text.size()]);
	}
	
	public int getLinesNumber() {
		return text.size();
	}

	public void setText(String text) {
		this.text = convertInLines(text, w);
		resizeText();
	}

	public String getText() {
		String output = "";
		for (String line : text)
			output += line + "\n";
		if (output.length() > 0)
			output.substring(0, output.length()-2);
		return output;
	}
	
	public void subLastChar() {
		String text = getText(), newText;
		if (text.length() > 0) {
			newText = text.substring(0, text.length()-1);
			setText(newText);
		}
	}
	
	public void addChar(char c) {
		setText(getText() + String.valueOf(c));
	}

	private void resizeText() {
		xBar = w - 12 + x;
		minY = y + 1;
		textY = y;
		lineH = defaultFontSize + lineSpace;
		textH = lineH * text.size() + lineH;
		barH = h * h / textH;
		div = (textH - h) / (h - barH);
		canScroll = div > 1;
		maxY = h - barH + y;
		barY = maxY;
		if (canScroll)
			textY = -(barY - minY) * div + y;
	}
	
	public void showScrollbar(boolean show) {
		this.showScrollBar = show;
	}
	
	public boolean isShowScrollbar() {
		return showScrollBar;
	}

	private ArrayList<String> convertInLines(String text, float w) {
		int textWidth = 0;
		ArrayList<String> output = new ArrayList<>();
		String line = "";
		String[] words = text.split(" ");

		for (String word : words)
			if ((textWidth += textFont.getWidth(" " + word)) < w && !word.contains("\n"))
				line += word + " ";
			else {
				if (word.contains("\n"))
					for (String word2 : word.split("\n"))
						line += word2 + " ";
				if (line.length() > 0)
					line.substring(0, line.length()-1);
				output.add(line);
				textWidth = textFont.getWidth(word);
				line = word;
			}
		output.add(line);
		return output;
	}
	
	private void scrollText(float change) {
		if (canScroll) {
			if (barY - change > maxY)
				barY = maxY;
			else if (barY - change < minY)
				barY = minY;
			else
				barY += -change;
			textY = -(barY - minY) * div + y;
		}
	}

	private void initMouseListener() {
		mouse = new WheelListener() {
			@Override
			public void mouseWheelMoved(int wheel) {
				if (visible) {
					int mouseX = input.getMouseX(), mouseY = input.getMouseY();
					if ((mouseX >= x && mouseX <= w + x) && (mouseY >= y && mouseY <= h + y))
						scrollText(wheel / scrollSensitivity);
					visible = false;
				}
			}
		};
		scrollBarZone.setMouseListener(new MouseDragListener() {
			@Override
			public void dragged(int changeX, int changeY) throws SlickException {
				if (visible)
					scrollText(-changeY);
				visible = false;
			}
		});
	}
}
