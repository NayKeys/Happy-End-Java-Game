package com.ensea.nya.utilities;

import java.util.Random;

import org.newdawn.slick.util.Log;

public class Randomizer<O> {

	private O[] list;
	private short[] percentChances;

	public Randomizer(O[] list, short[] chance) {
		if(list.length != chance.length)
			throw new IllegalArgumentException("percent Chances Array must have the same length than the values Array");
		int testing = 0;
		for(short num : chance)
			testing += num;
		if(testing > 100)
			throw new IllegalArgumentException("Total chance must be smaller than 100 !");

		this.list = list;
		this.percentChances = chance.clone();
		percentChances[0] += -1;
	}

	public O getValue() {
		Random ran = new Random();

		int count = 0;
		short percentTotal = 100;

		for (short percentChance : percentChances) {
			if (percentChance > 100 || percentChance < -1)
				throw new IllegalArgumentException("percent mean a value between 0 and 100 ...\nValue : " + percentChance);

			if (ran.nextInt(percentTotal) <= percentChance)
				return list[count];

			percentTotal += -percentChance;
			count++;
		}
		Log.warn("<Randomizer> Return null");
		return null;
	}
}
