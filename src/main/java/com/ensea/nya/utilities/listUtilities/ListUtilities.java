package com.ensea.nya.utilities.listUtilities;

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

	public O getLastValue(int index, int how) {
		if (index-how<=1)
			return null;
		else
			return list.get(index-how);
	}

	// public float getLastFloat() {
  //   if (getLastValue() instanceof Float) {
  //     return list.size()==0 ? 0f : (float) getLastValue();
  //   } else
  //     return 0f;
  //   }
    
  // public float getLastFloat(int how) {
  //   if (getLastValue() instanceof Float) {
  //     return list.size()==0 ? 0f : (float) getLastValue(how);
  //   } else
  //     return 0f;
  // }
}
