package org.happyEnd.happyEnd.version1.update0.patch0.map.zone;

import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.Mob;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.ghouls.Ghoul;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.medusa.Snake;
import org.happyEnd.happyEnd.version1.update0.patch0.utilities.ListForGenerator;
import org.happyEnd.happyEnd.version1.update0.patch0.utilities.Randomizer;
import org.newdawn.slick.SlickException;

public class MonsterZone extends NormalZone {

	public static final ListForGenerator<Mob> WEAK = new ListForGenerator<>(new Mob[] { new Ghoul(), new Snake() },
			new short[] { 60, 40 });
	public static final ListForGenerator<Mob> MEDIUM = new ListForGenerator<>(new Mob[] { new Ghoul(), new Snake() },
			new short[] { 60, 40 });
	public static final ListForGenerator<Mob> STRONG = new ListForGenerator<>(new Mob[] { new Ghoul(), new Snake() },
			new short[] { 60, 40 });

	private Mob monster;

	public MonsterZone(String path, float mapX, float mapY, ListForGenerator<Mob> list, int index) throws SlickException {
		super(path, mapX, mapY, index);
		monster = new Randomizer<Mob>(list.getList(), list.getChances()).getValue();
	}

	public MonsterZone(String path, float mapX, float mapY, Mob monster, int index) throws SlickException {
		super(path, mapX, mapY, index);
		this.monster = monster;
	}

	public Mob getMonster() {
		return monster;
	}

	public void generateNewMonster(ListForGenerator<Mob> list) {
		monster = new Randomizer<Mob>(list.getList(), list.getChances()).getValue();
	}

	public void generateNewMonster(Mob monster) {
		this.monster = monster;
	}
}
