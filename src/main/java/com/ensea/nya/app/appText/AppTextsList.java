package com.ensea.nya.app.appText;

import static com.ensea.nya.app.appText.SessionLanguage.*;

import java.io.IOException;
import java.util.HashMap;

import com.ensea.nya.ressourcesLoaders.FileReader;
import org.newdawn.slick.util.Log;

public class AppTextsList {

    protected HashMap<Short, String> dialogs, descriptions, names, history, appTexts;

    public AppTextsList(short language) {
	if (language == FRENCH)
	    Log.info(" Current language session : FRENCH (lang/FR_fr/)");
	else if (language == ENGLISH)
	    Log.info(" Current language session : ENGLISH (lang/EN_en/)");
	loadTextData(language);
    }

    public String getName(short id) {
	return names.get(id);
    }

    public String getText(short id) {
	return appTexts.get(id);
    }

    public String getDialog(short id) {
	return dialogs.get(id);
    }

    public String getDesc(short id) {
	return descriptions.get(id);
    }

    public String getHistory(short id) {
	return history.get(id);
    }

    private void loadTextData(short language) {
	try {
	    names = FileReader.readLangFile(language, "names");
	    descriptions = FileReader.readLangFile(language, "desc");
	    history = FileReader.readLangFile(language, "history");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
