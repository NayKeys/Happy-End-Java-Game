package com.ensea.nya.graphics.gamesStates.animationEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ensea.nya.App;
import com.ensea.nya.excetpionsManager.DefaultExceptionInInitializerError;
import com.ensea.nya.graphics.components.Clickable;
import com.ensea.nya.graphics.components.Component;
import com.ensea.nya.graphics.components.TextField;
import com.ensea.nya.graphics.components.listeners.mouseListeners.ClickListener;
import com.ensea.nya.graphics.components.listeners.mouseListeners.KeyPressedListener;
import com.ensea.nya.graphics.components.listeners.mouseListeners.MouseDragListener;
import com.ensea.nya.graphics.components.listeners.mouseListeners.SimpleMouseListener;
import com.ensea.nya.graphics.gamesStates.CharacterSelectionState;
import com.ensea.nya.graphics.gamesStates.SimpleGameState;
import com.ensea.nya.ressourcesLoaders.FileReader;
import com.ensea.nya.ressourcesLoaders.FilesExtension;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.state.StateBasedGame;

public class AnimationEditor extends SimpleGameState implements Serializable {

	private static final long serialVersionUID = 201806132302l;

	public static final String PATH = "textures/gui/animEdit/";
	public static final String SAVE_PATH = "animationsCreated/";
	public static final float SIZE = 72, FRAMES_Y = 856, FRAME_X = 75, FRAME_STEP = 85;

	public AnimationEditor(short ID) throws SlickException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		super(ID);
		App.getGameContainer().setFullscreen(false);
		App.getGameContainer().setShowFPS(true);
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Windows".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
	}

	private static final int INDEX = 0, DURATION = 1, X = 2, Y = 3, WIDTH = 4, HEIGHT = 5;

	private static int DataReader(Path path, int frameIndex, int INFO) throws IOException {
		return Integer.parseInt(Files.readAllLines(path).get(frameIndex = frameIndex * 4 + 2).split(", ")[INFO]);
	}

	private void load() throws SlickException, IOException {
		animation = new Animation();
		JFileChooser chooser = new JFileChooser(SAVE_PATH);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(Display.getParent()) == JFileChooser.APPROVE_OPTION) {
			ArrayList<Frame> loadedFrames = new ArrayList<>();
			int count = 0;
			for (File file : FileReader.getFilesInFolder(chooser.getSelectedFile()))
				if (FilesExtension.get(file).equals(".png")) {
					Path path = file.toPath();
					Frame frame = new Frame(file.getAbsolutePath());
					loadedFrames.add(frame);
					frame.index = DataReader(path, count, INDEX);
					frame.setduration(DataReader(path, count, DURATION));
					frame.setBounds(DataReader(path, count, X), DataReader(path, count, Y), DataReader(path, count, WIDTH), DataReader(path, count, HEIGHT));
					count++;
				}
		}
	}

	private void save() {
		int count = 1;
		File fileSave;
		while ((fileSave = new File(SAVE_PATH+count+"/Object.nay")).exists())
			count++;
		try {
			fileSave.getParentFile().mkdir();
			fileSave.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		serialise(this, fileSave);

		//Aleternative save
		try {
			File dataFile = new File(SAVE_PATH+count+"/frames/data.nay");
			dataFile.getParentFile().mkdir();
			dataFile.createNewFile();
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(dataFile.getAbsolutePath()), StandardCharsets.UTF_8);
			int frameCount = 0;
			for (Frame frame : animation) {
				ImageOut.write(frame, "png", SAVE_PATH+count+"/frames/frame"+frameCount+".png", true);
				writer.append("Frame n�"+frameCount);
				writer.newLine();
				writer.append("Duration : "+frame.getDuration()+" x : "+frame.getPositionX()+" y : "+frame.getPositionY()+" w : "+frame.getDimWidth()+" h : "+frame.getDimHeight());
				writer.newLine();
				writer.append(String.format("%d, %d, %d, %d, %d, %d", frameCount, frame.getDuration(), frame.getPositionX(), frame.getPositionY(), frame.getDimWidth(), frame.getDimHeight()));
				writer.newLine();
				writer.newLine();
				frameCount++;
			}
			writer.close();
			JOptionPane.showMessageDialog(Display.getParent(), "L'animation a �t� sauvegard� avec succ�s !", "Sauvegarde r�ussie", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | SlickException e) {
			e.printStackTrace();
		}
	}

	private void serialise(Object toSave, File path) {
		try {
			FileOutputStream file_Out = new FileOutputStream(path);
			ObjectOutputStream out_Object = new ObjectOutputStream(file_Out);
			try {
				out_Object.writeObject(toSave);
			} catch (Exception e) {}
			out_Object.close();
			file_Out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Image background, downHud, pictureCadre, currentCadre, animationInfoHud, selected;
	Button save, load, pause, previous, next;
	Animation animation;
	FrameInfoMenu frameInfo;
	FrameSetWindow setFrame;

	@Override
	public void init(GameContainer container) throws SlickException {
		this.input.addKeyListener(new KeyPressedListener() {
			@Override
			public void keyPressed(int key, char c) {
				if (key == Input.KEY_Z && (input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(Input.KEY_RCONTROL)))
					animation.getCurrentFrame().undo();
			}
			public void keyReleased(int key, char c) {}
		});
		background = new Image(CharacterSelectionState.SELECTION_PATH + "background.png");
		downHud = new Image(PATH + "downHud.png");
		pictureCadre = new Image(PATH + "pictureCadre.png");
		selected = new Image(PATH + "selected.png");
		currentCadre = new Image(PATH + "current.png");
		animation = new Animation();
		frameInfo = new FrameInfoMenu(container);
		animationInfoHud = new Image(PATH + "animationInfo.png");
		save = new Button(PATH + "saveButton.png", new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				save();
			}
		});
		load = new Button(PATH + "loadButton.png", new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				try {
					load();
				} catch (IOException e) {
					e.printStackTrace();
					throw new DefaultExceptionInInitializerError(e);
				}
			}
		});
		load.setPosition(1752, 777);
		pause = new Button(PATH + "pauseButton.png", new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				animation.setPause(!animation.isPaused());
			}
		});
		add(pause, 1695, 89);
		previous = new Button(PATH + "previousFrame.png", new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				animation.previous();
			}
		});
		add(previous, 1662, 87);
		next = new Button(PATH + "nextFrame.png", new ClickListener() {
			@Override
			public void clicked() throws SlickException {
				animation.next();
			}
		});
		add(next, 1809, 87);
		save.setPosition(1599, 777);

		//For test :
//		animation.add(new Frame("textures/items/combat/amulet/0.png"));
	}

	@Override
	public void paint(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw();
		downHud.draw(52, 833);
		float x = FRAME_X, y = FRAMES_Y, count = 0;
		for (Frame frame : animation) {
			pictureCadre.draw(x - 3, y - 3);
			frame.draw(x, y, SIZE, SIZE);
			if (frame.equals(frameInfo.currentFrame))
				selected.draw(x, y);
			if (count == animation.getCurrentFrameIndex())
				currentCadre.draw(x, y);
			g.setColor(Color.green);
			g.drawString(String.valueOf(count), x+36, y+76);
			x += FRAME_STEP;
			if (count == 21) {
				x = FRAME_X;
				y = FRAMES_Y + FRAME_STEP;
			}
			count++;
		}
		animation.render();
		animationInfoHud.draw(1599, 3);
		g.drawString("Frame : " + animation.getCurrentFrameIndex() + "/" + animation.size(), 1619, 24);
		g.drawString("Current Frame Time : " + animation.getTime(), 1666, 46);
		g.drawString("Total Time : " + animation.getTotalTime(), 1666, 68);
		frameInfo.render(container, g);
		pause.render(container, g);
		load.render(container, g);
		save.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException {
		super.update(container, game, deltaTime);
		animation.setDeltatime(deltaTime);
		setFrame.setDeltaTime(deltaTime);
	}

	public class FrameSetWindow extends Component implements Serializable {

		private static final long serialVersionUID = 201806132302l;

		private Image hud;

		public FrameSetWindow(GameContainer container, ClickListener ok) throws SlickException {
			hud = new Image(PATH + "editFrameWindow.png");
			x = 749;
			y = 344;
			this.ok = new Button(PATH + "okButton.png", ok);
			add(this.ok, x + 68, y + 157);
			this.cancel = new Button(PATH + "cancelButton.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					frameInfo.showSetFrame = false;
					cancel.componentClose();
					frameInfo.currentFrame.setBounds(oldX, oldY, oldW, oldH);
					frameInfo.currentFrame.setduration(oldDuration);
				}
			});
			add(this.cancel, x + 225, y + 157);
			inputX = new TextField(container, (int) (x + 149), (int) (y + 37), 85, 26, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try { frameInfo.currentFrame.setX(changeSetting(inputX.getText())); } catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "En g�n�ral x est un nombre :)", "T'es chiant", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			inputY = new TextField(container, (int) (x + 267), (int) (y + 37), 85, 26, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try { frameInfo.currentFrame.setY(changeSetting(inputY.getText())); } catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "En g�n�ral y est un nombre :)", "T'es chiant", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			inputW = new TextField(container, (int) (x + 149), (int) (y + 72), 85, 26, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try { frameInfo.currentFrame.setW(changeSetting(inputW.getText())); } catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "En g�n�ral w (la longueur) est un nombre :)", "T'es chiant", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			inputH = new TextField(container,  (int) (x + 267), (int) (y + 72), 85, 26, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try { frameInfo.currentFrame.setH(changeSetting(inputH.getText())); } catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "En g�n�ral h (la hauteur) est un nombre :)", "T'es chiant", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			inputDuration = new TextField(container, (int) (x + 130), (int) (y + 115), 85, 26, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try {
						int newDuration;
						if ((newDuration = Integer.parseInt(inputDuration.getText())) >= 0)
							frameInfo.currentFrame.setduration(newDuration);
						else
							throw new NumberFormatException();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "En g�n�ral la dur�e est un nombre sup�rieur � 0 :)", "T'es chiant", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			add(inputX);
			add(inputY);
			add(inputW);
			add(inputH);
			add(inputDuration);
		}

		private TextField inputX, inputY, inputW, inputH, inputDuration;
		private Button ok, cancel;

		@Override
		public void paint(GUIContext container, Graphics g) throws SlickException {
			hud.draw(x, y);
		}

		public void reset() throws SlickException {
			if (animation.haveFrame) {
				oldX = frameInfo.currentFrame.getPositionX();
				oldY = frameInfo.currentFrame.getPositionY();
				oldW = frameInfo.currentFrame.getWidth();
				oldH = frameInfo.currentFrame.getHeight();
				oldDuration = frameInfo.currentFrame.getDuration();
			} else {
				frameInfo.showSetFrame(false);
				return;
			}
			inputX.setText(String.valueOf(oldX));
			inputY.setText(String.valueOf(oldY));
			inputW.setText(String.valueOf(oldH));
			inputH.setText(String.valueOf(oldW));
			inputDuration.setText(String.valueOf(oldDuration));
		}

		private int oldX, oldY, oldW, oldH, oldDuration;

		private int changeSetting(String value) {
			int output = Integer.parseInt(value);
			return output;
		}
	}

	public class FrameInfoMenu extends Clickable implements Serializable {

		private static final long serialVersionUID = 201806132302l;

		private Button addFrame, changeN, delete, changeNCancel, changeNOk, nextIndex, previousIndex;
		private Image hud, changeNumberWindow;
		private TextField userChangeNumber;
		Button changeButton;
		private Frame currentFrame;
		private Graphics g;
		private boolean showSetFrame, showChangeNumber;
		public static final String PATH = AnimationEditor.PATH + "frameInfo/";

		public FrameInfoMenu(GameContainer container) throws SlickException {
			setInput(container.getInput());
			x = 1600;
			y = 135;
			w = 294;
			h = 634;
			setUnclickable(true);
			setFrame = new FrameSetWindow(container, new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					showSetFrame = false;
					setFrame.ok.componentClose();
				}
			});
			changeButton = new Button(PATH + "change/front.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					showSetFrameWindow();
				}
			});
			addFrame = new Button(PATH + "addFrame/front.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					JFileChooser chooser = new JFileChooser("textures");
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooser.setMultiSelectionEnabled(true);
					chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
					chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "png"));
					if (chooser.showOpenDialog(Display.getParent()) == JFileChooser.APPROVE_OPTION) {
						Frame frame = null;
						for (File file : chooser.getSelectedFiles())
							animation.add((frame = new Frame(file.getPath())));
						currentFrame = frame;
						showSetFrameWindow();
					}
				}
			});
			changeN = new Button(PATH + "changeNumber.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					showChangeNumber = true;
					if (currentFrame != null)
						oldIndex = currentFrame.index;
					else
						showChangeNumber = false;
					userChangeNumber.setText(String.valueOf(oldIndex));
				}
			});
			delete = new Button(PATH + "delete.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					animation.remove(currentFrame.index);
					animation.next();
					currentFrame = null;
				}
			});
			userChangeNumber = new TextField(container, (int) (cX + 247), (int) (cY + 36), 20, 27, new ComponentListener() {
				@Override
				public void componentActivated(AbstractComponent source) {
					try {
						int newIndex = Integer.parseInt(userChangeNumber.getText());
						if (newIndex < 0 || newIndex >= animation.size())
							JOptionPane.showMessageDialog(Display.getParent(), "L'index d'une frame de l'animation doit �tre compris entre 0 et �tre plus petit que le nombre de frames acutelle de l'animation :)", "Erreur", JOptionPane.ERROR_MESSAGE);
						else
							currentFrame.setIndex(newIndex);
						showChangeNumber = false;
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(Display.getParent(), "Entre un nombre stp ...", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			changeNCancel = new Button(AnimationEditor.PATH + "cancelButton.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					showChangeNumber = false;
					changeNCancel.componentClose();
					if (currentFrame != null)
						currentFrame.setIndex(oldIndex);
				}
			});
			changeNCancel.setPosition(cX+208, cY+99);
			changeNOk = new Button(AnimationEditor.PATH + "okButton.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					showChangeNumber = false;
					changeNOk.componentClose();
				}
			});
			changeNOk.setPosition(cX+34, cY+99);
			nextIndex = new Button(AnimationEditor.PATH + "nextFrame.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					currentFrame.setIndex(1+currentFrame.index);
				}
			});
			previousIndex = new Button(AnimationEditor.PATH + "previousFrame.png", new ClickListener() {
				@Override
				public void clicked() throws SlickException {
					currentFrame.setIndex(currentFrame.index-1);
				}
			});
			changeNumberWindow = new Image(PATH + "changeNumberWindow.png");
			hud = new Image(PATH + "hud.png");
			g = new Graphics();
			g.setFont(App.defaultFont);
			initMouseListener();
			add(nextIndex, x+170, y+578);
			add(previousIndex, x+126, y+578);
			add(addFrame, x + 72, y + 33);
			add(changeN, 1629+70, y + 182);
			add(delete, 1760, y + 130);
			add(changeButton, x + 54, y + 130);
			showComponent(false);
		}

		private int oldIndex;

		public void showSetFrame(boolean b) {
			showSetFrame = b;
		}

		@Override
		protected void renderComponents(GUIContext container, Graphics g) throws SlickException {
			for (Component component : components) {
				if (!component.equals(addFrame) && currentFrame != null)
					component.render(container, g);
			}
		}

		private void showSetFrameWindow() throws SlickException {
			showSetFrame = true;
			setFrame.reset();
		}

		private float cX = 849, cY = 569;

		@Override
		public void paint(GUIContext container, Graphics g) throws SlickException {
			hud.draw(x, y);
			addFrame.render(container, g);
			if (currentFrame != null) {
				Frame currentFrame = this.currentFrame;
				if (showChangeNumber) {
					changeNumberWindow.draw(cX, cY);
					userChangeNumber.render(container, g);
					changeNCancel.render(container, g);
					changeNOk.render(container, g);
				}
				currentFrame.draw(x + 10, y + 210, 274, 274 * frameH / frameW);
				renderComponents(container, g);
				g.drawString("(" + currentFrame.getPositionX() + ", " + currentFrame.getPositionY() + "), (" + currentFrame.getDimWidth() + ", " + currentFrame.getDimHeight() + ")", x + 34, y + 84);
				g.drawString("Dur�e : " + currentFrame.getDuration(), x + 34, y + 107);
				g.drawString("frame n�" + currentFrame.index, x + 34, y + 182);
				Color color = g.getColor();
				g.setColor(Color.white);
				g.drawString("Changer l'index", x+90, y+602);
				g.setColor(color);
				if (showSetFrame)
					setFrame.render(container, g);
			}
		}

		private float frameW, frameH;

		public void setFrame(Frame frame) {
			showSetFrame = false;
			this.currentFrame = frame;
			frameH = frame.getDimHeight();
			frameW = frame.getDimWidth();
		}

		private void initMouseListener() {
			mouse = new MouseDragListener() {
				@Override
				public void dragged(int changeX, int changeY) throws SlickException {
					setPosition(x + changeX, y + changeY);
				}
			};
		}
	}

	public class Button extends Clickable implements Serializable {

		private static final long serialVersionUID = 201806132302l;

		private Image button;

		public Button(String path, ClickListener click) throws SlickException {
			button = new Image(path);
			setDimension(button.getWidth(), button.getHeight());
			mouse = new SimpleMouseListener() {
				public void dragged(int changeX, int changeY) throws SlickException {}
				public void holdClick() throws SlickException {}
				public void exitedOnce() throws SlickException {
					App.cursorDefault();
				}
				public void exited() throws SlickException {}
				public void enteredOnce() throws SlickException {
					App.cursorHand();
				}
				public void entered() throws SlickException {}
				public void clickOutside() throws SlickException {}
				public void clickInside() throws SlickException {
					click.clicked();
				}
			};
		}

		@Override
		public void paint(GUIContext container, Graphics g) throws SlickException {
			button.draw(x, y);
		}
	}
}
