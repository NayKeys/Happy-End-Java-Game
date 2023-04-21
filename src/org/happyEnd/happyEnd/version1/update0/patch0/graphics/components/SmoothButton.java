package org.happyEnd.happyEnd.version1.update0.patch0.graphics.components;

import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.ClickListener;
import org.happyEnd.happyEnd.version1.update0.patch0.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import org.happyEnd.happyEnd.version1.update0.patch0.utilities.HitBoxRectangle;
import org.happyEnd.happyEnd.version1.update0.patch0.utilities.Smooth;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

public class SmoothButton extends Clickable {

	private static final float DEFAULT_PERCENT = 0.30f;
	private static final String FRONT_PATH = "/front.png", BACK_PATH = "/back.png";

	public SmoothButton(String path, boolean inverted, ClickListener click) throws SlickException {
		this(path, inverted, DEFAULT_PERCENT, click);
	}
	
	public SmoothButton(String path, boolean inverted, HitBoxRectangle hitBox, ClickListener click) throws SlickException {
		this(path, inverted, DEFAULT_PERCENT, click);
		haveSetedHitBox = true;
		setBounds(hitBox);
	}

	public SmoothButton(String path, boolean inverted, float percent, ClickListener click) throws SlickException {
		this(path, percent, inverted);
		initMouseListener(click);
	}
	
	public SmoothButton(String path, boolean inverted, float percent, HitBoxRectangle hitBox, ClickListener click) throws SlickException {
		this(path, inverted, percent, click);
		haveSetedHitBox = true;
		setBounds(hitBox);
	}

	private SmoothButton(String path, float percent, boolean inverted) throws SlickException {
		this.front = new Image(path + FRONT_PATH);
		try {
			back = new Image(path + BACK_PATH);
			haveBackground = true;
			w = back.getWidth();
			h = back.getHeight();
		} catch (RuntimeException e) {
			haveBackground = false;
			w = front.getWidth();
			h = front.getHeight();
		}
		super.w = w;
		super.h = h;
		this.inverted = inverted;
		this.percent = percent;

		frontW = front.getWidth();
		frontH = front.getHeight();
		index = 0;
		indexClick = 0;
		selected = inverted;

		if (!inverted) {
			frontMin = frontW;
			frontMax = frontW + w * percent;
			speed2 = 3;
		} else {
			frontMax = frontW;
			frontMin = frontW - w * percent;
			speed1 = 2.5f;
		}
		function = Smooth.FAST_TO_SLOW_SMOOTH.getPoints(800, frontMin, frontMax, 0.5f);

		clickFunction = new ArrayList<>(Smooth.FAST_TO_SLOW_SMOOTH.getPoints(60, frontMax, (float) (frontMax * 1.08), 2000f));
	}

	private Image front, back;
	private boolean haveBackground, inverted, haveSetedHitBox;
	private float frontX, frontY, w , h, x, y;

	@Override
	public void paint(GUIContext container, Graphics g) throws SlickException {
		animations();

		if (haveBackground)
			back.draw(x, y, w, h);

		frontX = (w / 2) - (frontW / 2) + x; // Middle X
		frontY = (h / 2) - (frontH / 2) + y; // Middle Y
		front.draw(frontX, frontY, frontW, frontH);
	}

	protected boolean clickAnimationRunning, clickIn, selected;
	protected int indexClick, index;
	protected ArrayList<Float> function, clickFunction;
	protected float frontW, frontH, frontMax, frontMin, speed1 = 1, speed2 = 1, percent;

	public void animations() {
		int deltaTime = getTime();
		if (deltaTime != 0)
			if (clickAnimationRunning)
				clickAnim(deltaTime);
			else {
				int i = index;
				loop: while (index < i + deltaTime * speed1)
					if (index < function.size() && index >= 0)
						if (selected)
							if (frontW + function.get(index) < frontMax) {
								frontW += function.get(index);
								frontH = frontW * h / w;
								if (index + 1 < function.size())
									index++;
							} else
								break loop;
						else {
							if (index < i - deltaTime * speed2)
								break loop;
							if (frontW - function.get(index) > frontMin) {
								frontW += -function.get(index);
								frontH = frontW * h / w;
								if (index - 1 >= 0)
									index--;
							} else
								break loop;
						}
					else
						break loop;
			}
	}

	private void clickAnim(int deltaTime) {
		int i = indexClick;
		loop: while (indexClick <= i + deltaTime)
			if (indexClick < clickFunction.size() && indexClick >= 0)
				if (clickIn)
					if (frontW + clickFunction.get(indexClick) < frontMax * (1 + percent / 3)) {
						frontW += clickFunction.get(indexClick);
						frontH += -clickFunction.get(indexClick);
						if (indexClick + 1 < clickFunction.size())
							indexClick++;
					} else {
						clickIn = false;
						break loop;
					}
				else {
					if (indexClick <= i - deltaTime)
						break loop;
					if (frontW - clickFunction.get(indexClick) > frontMax) {
						frontW += -clickFunction.get(indexClick);
						frontH += clickFunction.get(indexClick);
						if (indexClick - 1 >= 0)
							indexClick--;
					} else {
						clickAnimationRunning = false;
						break loop;
					}
				}

	}

	public void setClickListener(ClickListener click) {
		initMouseListener(click);
	}
	
	@Override
	public void setPosition(float x, float y) {
		if (haveSetedHitBox) {
			this.x = x;
			this.y = y;
		}else {
			super.setPosition(x, y);
			this.x = super.x;
			this.y = super.y;
		}
	}

	public void clickAnimation() {
		if (clickAnimationRunning)
			return;
		clickAnimationRunning = true;
		clickIn = true;
		indexClick = 0;
	}

	protected void initMouseListener(ClickListener click) {
		super.mouse = new SimpleMouseListener() {

			@Override
			public void clickInside() throws SlickException {
				click.clicked();
				clickAnimation();
			}

			@Override
			public void entered() throws SlickException {
				if (selected == !inverted)
					return;
				selected = !inverted;
			}

			@Override
			public void exited() throws SlickException {
				if (selected == inverted)
					return;
				selected = inverted;
			}

			@Override
			public void clickOutside() {
			}

			@Override
			public void dragged(int changeX, int ChangeY) throws SlickException {
			}

			@Override
			public void enteredOnce() throws SlickException {
				Launcher_HappyEnd.cursorHand();
			}

			@Override
			public void exitedOnce() throws SlickException {
				Launcher_HappyEnd.cursorDefault();
			}

			@Override
			public void holdClick() throws SlickException {
				
			}
		};
	}
}
