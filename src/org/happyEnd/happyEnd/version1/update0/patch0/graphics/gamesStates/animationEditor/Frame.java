package org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.animationEditor;

import java.io.Serializable;
import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import static org.happyEnd.happyEnd.version1.update0.patch0.commands.ConsoleCommands.getAnimationEditorState;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.InvisibleClickableZone;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import static org.happyEnd.happyEnd.version1.update0.patch0.graphics.gamesStates.animationEditor.AnimationEditor.*;

public class Frame extends Image implements Serializable {
	
	private static final long serialVersionUID = 201806132302l;

	private int duration;
	public int index;
	private float x, y, w, h;
	private Graphics g;
	private InvisibleClickableZone frameCadre, frameOfAnim;

	public Frame(String ref) throws SlickException {
		this(ref, 300);
	}

	public Frame(String ref, int duration) throws SlickException {
		this(ref, duration, 0, 0);
		this.duration = duration;
	}

	public Frame(String ref, int duration, float x, float y) throws SlickException {
		super(ref, false, FILTER_NEAREST);
		this.duration = duration;
		this.x = x;
		this.y = y;
		this.w = super.getWidth();
		this.h = super.getHeight();
		frameOfAnim = new InvisibleClickableZone(x, y, w, h);
		frameCadre = new InvisibleClickableZone(x, y, SIZE, SIZE);
		initMouseListener();
		g = new Graphics();
		g.setColor(Color.green);
		lastValues = new ArrayList<>();
	}

	public void setIndex(int index) {
		if (index < 0 || index > getAnimationEditorState().animation.size()-1)
			return;
		getAnimationEditorState().animation.changeIndexOf(this, index);
		this.index = index;
	}
	
	public void setBounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		frameOfAnim.setBounds(x, y, w, h);
	}

	public void setX(float x) {
		this.x = x;
		frameOfAnim.setPosition(x, y);
	}

	public void setY(float y) {
		this.y = y;
		frameOfAnim.setPosition(x, y);
	}

	public void setW(float w) {
		this.w = w;
		frameOfAnim.setDimension(w, h);
	}

	public void setH(float h) {
		this.h = h;
		frameOfAnim.setDimension(w, h);
	}

	public int getPositionX() {
		return (int) x;
	}

	public int getPositionY() {
		return (int) y;
	}

	public int getDimWidth() {
		return (int) w;
	}

	public int getDimHeight() {
		return (int) h;
	}

	public void setduration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void draw() {
		super.draw(x, y, w, h);
		g.drawRect(x, y, w, h);
		try {
			frameOfAnim.verif();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(float x, float y, float w, float h) {
		super.draw(x, y, w, h);
		frameCadre.setPosition(x, y);
		try {
			frameCadre.verif();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	@Override
	public void draw(float x, float y) {
		super.draw(x, y);
	}
	
	private ArrayList<Float[]> lastValues;
	private static final int LAST_VALUES_MAX = 30;
	
	public void undo() {
		if (lastValues.size() > 0) {
			Float[] oldValues = lastValues.get(lastValues.size()-1);
			setX(oldValues[0]);
			setY(oldValues[1]);
			setW(oldValues[2]);
			setH(oldValues[3]);
			lastValues.remove(oldValues);
		}
	}
	
	private void initMouseListener() {
		frameOfAnim.setMouseListener(new SimpleMouseListener() {
			public static final int NORMAL_MODE = 0,  MOVE_MODE = 1, LENGHT = 10, 
					LEFT = 2,TOP = 3, TOP_LEFT = 4, TOP_RIGHT = 5, RIGHT = 6, BOTTOM = 7, BOTTOM_LEFT = 8, BOTTOM_RIGHT = 9;
			
			private int mouseX, mouseY, state;
			private boolean entered, lastValueAdded;
			
			public void clickInside() throws SlickException {}
			public void clickOutside() throws SlickException {}
			public void entered() throws SlickException {
				entered = true;
				mouseX = frameOfAnim.input.getMouseX();
				mouseY = frameOfAnim.input.getMouseY();
					if (!frameOfAnim.isDragging()) {
						if (!lastValueAdded) {
							lastValues.add(new Float[] {x, y, w, h});
							if (lastValues.size() > LAST_VALUES_MAX)
								lastValues.remove(0);
							lastValueAdded = true;
						}
						boolean topSide = mouseY <= y+LENGHT;
						boolean bottomSide = mouseY >= h+y-LENGHT;
						boolean rightSide = mouseX >= w+x-LENGHT;
						boolean leftSide = mouseX <= x+ LENGHT;
						if (topSide)
							if (rightSide) {
								state = TOP_RIGHT;
								Launcher_HappyEnd.cursorResizeTOP_RIGHT();
							} else if (leftSide) {
								state = TOP_LEFT;
								Launcher_HappyEnd.cursorResizeTOP_LEFT();
							} else {
								state = TOP;
								Launcher_HappyEnd.cursorResizeTop();
							}
						else if (bottomSide)
							if (rightSide) {
								state = BOTTOM_RIGHT;
								Launcher_HappyEnd.cursorResizeTOP_LEFT();
							} else if (leftSide) {
								state = BOTTOM_LEFT;
								Launcher_HappyEnd.cursorResizeTOP_RIGHT();
							} else {
								state = BOTTOM;
								Launcher_HappyEnd.cursorResizeTop();
							}
						else if (rightSide) {
							state = RIGHT;
							Launcher_HappyEnd.cursorResizeLeft();
						} else if (leftSide) {
							state = LEFT;
							Launcher_HappyEnd.cursorResizeLeft();
						} else {
							state = MOVE_MODE;
							Launcher_HappyEnd.cursorMove();
						}
					} else
						lastValueAdded = false;
			}

			public void enteredOnce() throws SlickException {
			}

			public void exited() throws SlickException {
				if (!frameOfAnim.isDragging()) {
					if (!entered)
						return;
					entered = false;
					state = NORMAL_MODE;
					Launcher_HappyEnd.cursorDefault();
				}
			}

			public void exitedOnce() throws SlickException {
			}

			public void holdClick() throws SlickException {
			}

			public void dragged(int changeX, int changeY) throws SlickException {
				switch (state) {
				case MOVE_MODE:
					setX(x + changeX);
					setY(y + changeY);
					break;
				case LEFT:
					setX(x + changeX);
					setW(w - changeX);
					break;
				case RIGHT:
					setW(w + changeX);
					break;
				case TOP:
					setY(y + changeY);
					setH(h - changeY);
					break;
				case BOTTOM:
					setH(h + changeY);
					break;
				case TOP_LEFT:
					if (!frameOfAnim.input.isKeyDown(Input.KEY_LSHIFT)) {
						setW(w - changeX);
						setH(h - changeY);
						setY(y + changeY);
						setX(x + changeX);
					}
					break;
				case TOP_RIGHT:
					if (!frameOfAnim.input.isKeyDown(Input.KEY_LSHIFT)) {
						setW(w + changeX);
						setH(h - changeY);
						setY(y + changeY);
					}
					break;
				case BOTTOM_LEFT:
					if (!frameOfAnim.input.isKeyDown(Input.KEY_LSHIFT)) {
						setW(w - changeX);
						setH(h + changeY);
						setX(x + changeX);
					}
					break;
				case BOTTOM_RIGHT:
					if (frameOfAnim.input.isKeyDown(Input.KEY_LSHIFT)) {
						float value = changeX / 2 + changeY / 2, w2 = w;
						setW(w + value);
						setH(h + value * h / w2);
					} else {
						setW(w + changeX);
						setH(h + changeY);
					}
					break;
				}
			}
		});
		Frame frame = this;
		frameCadre.setMouseListener(new SimpleMouseListener() {
			public void clickInside() throws SlickException {
				getAnimationEditorState().frameInfo.setFrame(frame);
			}

			public void clickOutside() throws SlickException {
			}

			public void entered() throws SlickException {
			}

			public void enteredOnce() throws SlickException {
				Launcher_HappyEnd.cursorHand();
			}

			public void exited() throws SlickException {
			}

			public void exitedOnce() throws SlickException {
				Launcher_HappyEnd.cursorDefault();
			}

			public void dragged(int changeX, int changeY) throws SlickException {
			}

			public void holdClick() throws SlickException {
			}
		});
	}
}