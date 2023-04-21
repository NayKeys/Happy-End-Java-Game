package com.ensea.nya.map;

import com.ensea.nya.App;
import com.ensea.nya.game.entities.characters.PlayerCharacter;
import com.ensea.nya.game.entities.mobs.thief.Thief;
import com.ensea.nya.game.fights.Fight;
import com.ensea.nya.map.excpetions.IllegalMovementException;
import com.ensea.nya.map.zone.BossZone;
import com.ensea.nya.map.zone.HybridZone;
import com.ensea.nya.map.zone.MapZone;
import com.ensea.nya.map.zone.MonsterZone;
import com.ensea.nya.map.zone.NormalZone;
import com.ensea.nya.map.zone.PVPZone;
import com.ensea.nya.map.zone.StartZone;
import com.ensea.nya.map.zone.VillageZone;
import com.ensea.nya.map.zone.bonusZone.BonusZone;
import com.ensea.nya.map.zone.bonusZone.ChestBonusZone;
import com.ensea.nya.map.zone.bonusZone.VillageBonus;
import org.newdawn.slick.SlickException;

public class Map {

	// Thiefs
	Thief stealer;

	// Zones
	private StartZone spawn;
	private MonsterZone monster1, monster2, monster3, monster4, monster5, monster6, monster7, monster8;
	private VillageZone village1, village2, village3;
	private HybridZone hybrid1, hybrid2, hybrid3, hybrid4;
	private PVPZone pvp;
	private BossZone bossZone;
	private ChestBonusZone chest;
	private VillageBonus villageBonus;

	{
		initZones();
	}

	private final NormalZone[] MAP = { spawn, monster1, monster2, village1, monster3, hybrid1, hybrid2, monster4,
			village2, monster5, hybrid3, monster6, village3, monster7, monster8, hybrid4, pvp, bossZone };
	public BonusZone[] bonus = { chest, villageBonus };

	// Class Methods
	public MapZone moveZone(PlayerCharacter player, MapZone oldZone, int movement) {
		try {
			NormalZone zone = (NormalZone) oldZone;

			if (Thief.invoke()) {
				stealer = new Thief();
				new Fight(player, stealer);
			}

			if (zone.equals(bossZone))
				throw new IllegalMovementException("Cant move from bossZone with this method");
			if (zone.getIndex() + movement < 0)
				return MAP[0];
			else
				return MAP[zone.getIndex() + movement];
		} catch (ClassCastException e) {
			throw new IllegalMovementException("Don't use this methods to move on or from a bonusZone");
		} catch (SlickException e) {
			e.printStackTrace();
			App.exitGame();
			return null;
		}
	}

	public MapZone moveZone(PlayerCharacter player, NormalZone oldZone) throws SlickException {
		if (Thief.invoke()) {
			stealer = new Thief();
			new Fight(player, stealer);
		}

		if (oldZone.equals(bossZone))
			return chest;
		else {
			try {
				BonusZone bonus = (BonusZone) oldZone;
				return MAP[bonus.getAfterIndex()];
			} catch (ClassCastException e) {
				throw new IllegalMovementException(
						"Don't use this methods without for moving from a bossZone or a bonusZone !");
			}
		}
	}

	private void initZones() {
		// BonusZones
//		chest = new ChestBonusZone(18, 12);
//		villageBonus = new VillageBonus(18, 12);
//
//		spawn = new StartZone(0);
//		monster1 = new MonsterZone(new Ghoul(), 1);
//		monster2 = new MonsterZone(new Ghoul(), 2);
//		village1 = new VillageZone(3);
//		monster3 = new MonsterZone(WEAK, 4);
//		hybrid1 = new HybridZone(5);
//		hybrid2 = new HybridZone(6);
//		monster4 = new MonsterZone(MEDIUM, 7);
//		village2 = new VillageZone(8);
//		monster5 = new MonsterZone(MEDIUM, 9);
//		hybrid3 = new HybridZone(10);
//		monster6 = new MonsterZone(STRONG, 11);
//		village3 = new VillageZone(12);
//		monster7 = new MonsterZone(STRONG, 13);
//		monster8 = new MonsterZone(STRONG, 14);
//		hybrid4 = new HybridZone(15);
//		pvp = new PVPZone(16);
//		bossZone = new BossZone(17);
	}
}
