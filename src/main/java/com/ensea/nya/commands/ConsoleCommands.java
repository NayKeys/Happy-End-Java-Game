package com.ensea.nya.commands;

import static com.ensea.nya.App.changeState;
import static com.ensea.nya.App.defaultFont;
import static com.ensea.nya.App.defaultFontSize;
import static com.ensea.nya.App.exitGame;
import static com.ensea.nya.App.getCharacterSelectionState;
import static com.ensea.nya.App.getCurrentState;
import static com.ensea.nya.App.getGameContainer;
import static com.ensea.nya.App.getMapState;
import static com.ensea.nya.App.isHitBoxesVisibles;
import static com.ensea.nya.App.showHideBox;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.UnsupportedLookAndFeelException;

import com.ensea.nya.app.Game;
import com.ensea.nya.app.accounts.Account;
import com.ensea.nya.app.accounts.AdminAccount;
import com.ensea.nya.app.accounts.exceptions.WrongNameOrPasswordException;
import com.ensea.nya.app.appText.SessionLanguage;
import com.ensea.nya.game.entities.characters.AzunaCharacter;
import com.ensea.nya.game.entities.characters.CeliaCharacter;
import com.ensea.nya.game.entities.characters.GreedCharacter;
import com.ensea.nya.game.entities.characters.PlayerCharacter;
import com.ensea.nya.game.entities.mobs.ghouls.Ghoul;
import com.ensea.nya.game.fights.Fight;
import com.ensea.nya.graphics.gamesStates.BattleState;
import com.ensea.nya.graphics.gamesStates.SimpleGameState;
import com.ensea.nya.graphics.gamesStates.animationEditor.AnimationEditor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.util.Log;

public class ConsoleCommands {

	private static AnimationEditor animEdit;

	public static AnimationEditor getAnimationEditorState() {
		return animEdit;
	}

	public static void showOptions() throws SlickException {
		try {
			animEdit = new AnimationEditor(Game.ANIM_EDITOR);
			SimpleGameState.console = null;
			changeState(animEdit);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static boolean execute(String command) {
		try {
			if (command.charAt(0) != '/')
				return false;
		} catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
			return false;
		}
		if (list.containsKey(command))
			try {
				SimpleGameState.console.println(command);
				list.get(command).execute();
				return true;
			} catch (Exception e) {
				SimpleGameState.console.printlnErr("An error has occured");
				e.printStackTrace();
			}
		else
			SimpleGameState.console.printlnErr("unknown command : \"" + command + "\", tape \"/help\" to show help");
		return true;
	}

	private static HashMap<String, Command> list;

	static {
		list = new HashMap<>();
		list.put("/connect", new Command() {
			@Override
			public void execute() {
				SimpleGameState.console.println("Account Name : ");
				SimpleGameState.console.addListener(new ComponentListener() {
					String name, password;

					@Override
					public void componentActivated(AbstractComponent source) {
						if (name == null) {
							name = SimpleGameState.console.getText();
							SimpleGameState.console.println("Password : ");
							SimpleGameState.console.input.hideText(true);
						} else {
							password = SimpleGameState.console.getText();
							SimpleGameState.console.removeListener(this);
							SimpleGameState.console.input.hideText(false);
							try {
								Account.Connect(name, password);
								SimpleGameState.console.println("You are now connected as : <" + Account.connectedAccount.getName() + ">");
							} catch (WrongNameOrPasswordException e) {
								SimpleGameState.console.printlnErr(e.getMessage());
							}
						}
					}
				});
			}

			@Override
			public String description() {
				return "Connect to an account, entering the name and the password";
			}
		});
		list.put("/hitbox", new Command() {
			@Override
			public void execute() {
				showHideBox(!getCurrentState().isHitBoxesVisibles());
				SimpleGameState.console.println("Hide box visibility seted to : " + isHitBoxesVisibles());
			}

			@Override
			public String description() {
				return "Make hideBoxes visible or invisible of all the components of the current GameState";
			}
		});
		list.put("/hb", list.get("/hitbox"));
		list.put("/fullscreen", new Command() {
			@Override
			public void execute() {
				try {
					getGameContainer().setFullscreen(!getGameContainer().isFullscreen());
					SimpleGameState.console.println("Fullscreen mode seted to : " + getGameContainer().isFullscreen());
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}

			@Override
			public String description() {
				return "invert fullscreen value";
			}
		});
		list.put("/fs", list.get("/fullscreen"));
		list.put("/changeFont", new Command() {
			@Override
			public void execute() {
				SimpleGameState.console.println("Font name : ");
				SimpleGameState.console.addListener(new ComponentListener() {
					private String fontName;
					private int fontSize;

					@Override
					public void componentActivated(AbstractComponent source) {
						if (fontName == null) {
							fontName = SimpleGameState.console.getText();
							SimpleGameState.console.println("Font size : ");
						} else {
							try {
								fontSize = Integer.parseInt(SimpleGameState.console.getText());
								defaultFont = new TrueTypeFont(new Font(fontName, 0, fontSize), true);
								defaultFontSize = fontSize;
								SimpleGameState.console.println("\ndefault Game Font changed to  : " + defaultFont + ", " + defaultFontSize);
								SimpleGameState.console.removeListener(this);
							} catch (NumberFormatException e) {
								SimpleGameState.console.printlnErr(e.getMessage());
							} catch (Exception e) {
								SimpleGameState.console.printlnErr(e.getMessage());
							}
						}
					}
				});
			}

			@Override
			public String description() {
				return "Change the default game Font by the entered font name and size";
			}
		});
		list.put("/changeCharacter", new Command() {
			@Override
			public void execute() {
				if (Account.connectedAccount instanceof AdminAccount) {
					if (!getCurrentState().equals(getMapState())) {
						SimpleGameState.console.printlnErr("You must start a game to change your character");
						return;
					}
					SimpleGameState.console.println("Character Name : ");
					SimpleGameState.console.addListener(new ComponentListener() {
						private String characterName;

						@Override
						public void componentActivated(AbstractComponent source) {
							characterName = SimpleGameState.console.getText();
							PlayerCharacter[] players = getCharacterSelectionState().getCharacters();
							int index = 0;
							loop: for (index++; index < players.length;)
								if (players[index].name.equals(characterName))
									break loop;
							if (index < players.length) {
								getMapState().setCharacter(players[index]);
								SimpleGameState.console.println("Character changed for : " + characterName);
							} else
								SimpleGameState.console.printlnErr("Unknown character : " + characterName + "try this : " + SessionLanguage.getName(GreedCharacter.ID) + "; " + SessionLanguage.getName(AzunaCharacter.ID) + SessionLanguage.getName(CeliaCharacter.ID));
						}
					});
				} else
					SimpleGameState.console.printlnErr("You havent rights to use this command !");
			}

			@Override
			public String description() {
				return null;
			}
		});
		list.put("/help", new Command() {
			@Override
			public void execute() {
				int count = 0;
				for (String commandName : list.keySet()) {
					SimpleGameState.console.println("	" + count + ": " + commandName + " -> " + list.get(commandName).description());
					count++;
				}
			}

			@Override
			public String description() {
				return "Show every commands";
			}
		});
		list.put("/exit", new Command() {
			@Override
			public void execute() {
				Log.info("{Game Exit} executed from console command : \"/exit\"");
				exitGame();
			}

			@Override
			public String description() {
				return "Exit the game";
			}
		});
		list.put("/showFps", new Command() {
			@Override
			public void execute() {
				AppGameContainer changeFps = getGameContainer();
				SimpleGameState.console.println("showFps changing to : " + !changeFps.isShowingFPS());
				changeFps.setShowFPS(!changeFps.isShowingFPS());
			}

			@Override
			public String description() {
				return "Show Fps in the top left hand corner, if they were hide, and hide it if they were visible";
			}
		});
		list.put("/fps", list.get("/showFps"));
		list.put("/battle test", new Command() {
			@Override
			public void execute() {
				try {
					changeState(new BattleState(Game.BATTLE, new Fight(getCharacterSelectionState().getCharacters()[0], new Ghoul())));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}

			@Override
			public String description() {
				return "/!\\ Actualy not working !!			Enter in the GameState : BattleState, to test some animations, by default the battle is between Azuna and a simple Ghoul";
			}
		});
	}
}
