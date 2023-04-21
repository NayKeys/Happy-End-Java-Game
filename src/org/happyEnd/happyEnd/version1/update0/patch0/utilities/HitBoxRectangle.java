package org.happyEnd.happyEnd.version1.update0.patch0.utilities;

public class HitBoxRectangle {
	
	public HitBoxRectangle(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	private float x, y, w, h;
	
	public float getPositionX() {
		return x;
	}
	
	public float getPositionY() {
		return y;
	}
	
	public float getWidth() {
		return w;
	}
	
	public float getHeight() {
		return h;
	}
}
