package org.happyEnd.happyEnd.version1.update0.patch0.map;

import org.happyEnd.happyEnd.version1.update0.patch0.app.Launcher_HappyEnd;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.characters.PlayerCharacter;
import org.happyEnd.happyEnd.version1.update0.patch0.game.entities.mobs.thief.Thief;
import org.happyEnd.happyEnd.version1.update0.patch0.game.fights.Fight;
import org.happyEnd.happyEnd.version1.update0.patch0.map.excpetions.IllegalMovementException;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.BossZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.HybridZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.MapZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.MonsterZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.NormalZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.PVPZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.StartZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.VillageZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.bonusZone.BonusZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.bonusZone.ChestBonusZone;
import org.happyEnd.happyEnd.version1.update0.patch0.map.zone.bonusZone.VillageBonus;
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
			Launcher_HappyEnd.exitGame();
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
