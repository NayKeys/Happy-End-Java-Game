package com.ensea.nya.utilities;

public class ListForGenerator<O> {

	private O[] list;
	private short[] chances;

	public ListForGenerator(O[] list, short[] chances) {
		this.list = list;
		this.chances = chances;
	}

	public O[] getList() {
		return list;
	}

	public short[] getChances() {
		return chances;
	}
}
