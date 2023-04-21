package org.happyEnd.happyEnd.version1.update0.patch0.utilities.listUtilities;

import java.util.List;

public class ListUtilities<O> {

	private List<O> list;

	public ListUtilities(List<O> list) {
		this.list = list;
	}

	public void setList(List<O> newList) {
		this.list = newList;
	}

	// getIndex
	public int getLastIndex() {
		if (list.size()==0)
			return 0;
		else
			return list.size()-1;
	}

	// getValue
	public O getLastValue() {
		return list.get(getLastIndex());
	}

	public O getLastValue(int how) {
		if (list.size()-how<0)
			return null;
		else
			return list.get(list.size()-how);
	}

	public O getLastValue(int Index, int how) {
		if (Index-how<=1)
			return null;
		else
			return list.get(Index-how);
	}

	public float getLastFloat() {
		return list.size()==0 ? 0f : (float) getLastValue();
	}

	public float getLastFloat(int how) {
		return list.size()-how<=0 ? 0f : 
			(float) getLastValue(how);
	}
}
