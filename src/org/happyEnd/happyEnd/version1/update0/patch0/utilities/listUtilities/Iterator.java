package org.happyEnd.happyEnd.version1.update0.patch0.utilities.listUtilities;

import java.util.ArrayList;

public class Iterator<O> {
	
	private ArrayList<O> simil, different, a, b;

	public Iterator(ArrayList<O> a, ArrayList<O> b) {
		this.a = a;
		this.b = b;
	}
	
	public ArrayList<O> getSimilarValues() {
		for(O object : a) {
			if(b.contains(object))
				simil.add(object);
		}
		return simil;
	}
	
	public ArrayList<O> getDifferentValues() {
		for(O object : a) {
			if(!b.contains(a))
				different.add(object);
		}
		return different;
	}
}
