package org.happyEnd.happyEnd.version1.update0.patch0.game;

import org.happyEnd.happyEnd.version1.update0.patch0.app.appText.SessionLanguage;

public abstract class HaveTextsElements {
	
	public String name;
	public String desc;
	
	public HaveTextsElements() {
		name = SessionLanguage.getName(getID());
		desc = SessionLanguage.getDesc(getID());
	}
	
	public String toString() {
		return name + "\n" + desc;
	}
	
	public abstract short getID();
}