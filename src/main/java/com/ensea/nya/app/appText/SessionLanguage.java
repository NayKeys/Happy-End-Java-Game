package com.ensea.nya.app.appText;

public class SessionLanguage {

	public static final short FRENCH = 0;
	public static final short ENGLISH = 1;

	private static AppTextsList texts = new AppTextsList(FRENCH);

	public static String getText(short id) {
		return texts.getText(id);
	}

	protected static String getDialog(short id) {
		return texts.getDialog(id);
	}

	public static String getName(short id) {
		return texts.getName(id);
	}

	public static String getDesc(short id) {
		return texts.getDesc(id);
	}

	public static String getHistory(short id) {
		return texts.getHistory(id);
	}
}
