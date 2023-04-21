package com.ensea.nya.utilities;

import java.util.ArrayList;

public class Function extends ArrayList<Float> {

/**
     *
     */
    private static final long serialVersionUID = 7212146583104055134L;

//    private Smooth s;


//    public Function(Smooth smooth, boolean return0) {
//
//    }

    private ArrayList<Float> function;

    public Function(ArrayList<Float> function) {
	this.function = function;
    }

    public float get(int index, boolean return0) {
	try {
	    return function.get(index);
	} catch(IndexOutOfBoundsException e) {
	    if(return0)
		return 0;
	    else {
		return 0;
		//TODO
	    }
	}
    }
}
