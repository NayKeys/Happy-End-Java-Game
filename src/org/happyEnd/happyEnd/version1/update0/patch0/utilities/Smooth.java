package org.happyEnd.happyEnd.version1.update0.patch0.utilities;

import java.util.ArrayList;

import org.happyEnd.happyEnd.version1.update0.patch0.utilities.listUtilities.ListUtilities;

public enum Smooth {

	FAST_TO_SLOW {
		@Override
		public double function(float timeMillis, float max, float min, float smooth, float x) {
			max = max-min;
			return ((Math.pow((Math.pow(max, 1/timeMillis)), -x))*max)+min;
		}
	},
	FAST_TO_SLOW_SMOOTH {
		@Override
		public double function(float timeMillis, float max, float min, float smooth, float x) {
			max = max-min;
			return ((Math.pow((Math.pow((max+smooth/smooth), 1/timeMillis)), -x))*max)+min;
		}
	},
	SLOW_TO_FAST {
		@Override
		public double function(float timeMillis, float max, float min, float smooth, float x) {
			max = max-min;
			return max-(Math.pow(Math.pow(max, 1/timeMillis), x))+min/smooth;
		}
	},
	SLOW_TO_FAST_SMOOTH {
		@Override
		protected double function(float timeMillis, float max, float min, float smooth, float x) {
			max = max-min;
			return max+smooth-((Math.pow(Math.pow((max+smooth)/smooth, 1/timeMillis), x))*smooth)+min;
		}

	};

	protected abstract double function(float timeMillis, float max, float min, float smooth, float n);

	/**
	 * @param timeMillis, set time in ms, the function will return this number of points in an arrayList, mean every states for each ms
	 * 
	 * @param start the minValue y, the function is going from min to max
	 * 
	 * @Param end the maxValue y, the function is going from min to max
	 * 
	 * @param smooth
	 *            how much the function should be "smoothed", over 3000 it's
	 *            looking like a affine function (a right) /!\ Be careful this
	 *            setting is only affecting SLOW_TO_FAST_SMOOTH and
	 *            FAST_TO_SLOW_SMOOTH
	 *            
	 * @return time*points (y) in an ArrayList<Integer>, everypoints for each
	 *         milliSecondes in <SetedTime>
	 */
	public ArrayList<Float> getPoints(float timeMillis, float start, float end, float smooth) {
		return getPoints(timeMillis, start, end, smooth, (int) timeMillis);
	}
	
	public ArrayList<Float> getPoints(float timeMillis, float start, float end, float smooth, int points) {
		ArrayList<Float> output = new ArrayList<>();
		ArrayList<Float> function = new ArrayList<>();
		ListUtilities<Float> listU = new ListUtilities<>(function);
		double p = 0;
		float pBis;
		
		for (int x = 0; x<=timeMillis; x++) {
			if (p!=0)
				function.add(end-(float) p);
			pBis = listU.getLastFloat()-listU.getLastFloat(2);
			if (pBis!=0)
				if(start > end)
					output.add(-pBis);
				else
					output.add(pBis);
			if(start > end)
				p = function(timeMillis, start, end, smooth, x);
			else
				p = function(timeMillis, end, start, smooth, x);
		}
		return output;
	} 

	public static void main(String[] s) {
		Smooth test = Smooth.FAST_TO_SLOW_SMOOTH;
		ArrayList<Float> function = test.getPoints(200f, 721, 1405, 0.5f);
		double d = 0;
		for (float f : function) {
			d += f;
			System.err.println(d);
		}
		System.out.println(function.get(0));
		System.out.println(0+d);
	}
}
