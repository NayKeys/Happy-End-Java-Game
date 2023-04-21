package com.ensea.nya.ressourcesLoaders;

import static com.ensea.nya.app.appText.SessionLanguage.FRENCH;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ensea.nya.excetpionsManager.DefaultExceptionInInitializerError;
import org.newdawn.slick.util.Log;

public class FileReader {

	public static FilesExtension filesExtension;

	public static HashMap<Short, String> readLangFile(short language, String type) throws IOException {
		String langPath;
		langPath = language == FRENCH ? "FR_fr/" : "En_en/";

		Log.info("Load lang File : " + "lang/" + langPath + type + ".txt");
		List<String> allLines = Files.readAllLines(Paths.get("lang/" + langPath + type + ".txt"), Charset.defaultCharset());
		HashMap<Short, String> output = new HashMap<>();

		for (String line : allLines)
			output.put(Short.parseShort(line.split("/")[0]), line.split("/")[1]);
		return output;
	}

	public static ArrayList<File> getFilesInFolder(File folder) {
		try {
			File[] filesLoaded = getFilesAndFolderInFolder(folder);

			ArrayList<File> folders = new ArrayList<>();
			ArrayList<File> output = new ArrayList<>();

			do {
				if (folders.size() > 0) {
					filesLoaded = getFilesAndFolderInFolder(folders.get(0));
					folders.remove(0);
				}
				for (File file : filesLoaded) {
					if (file.isDirectory())
						folders.add(file);
					else
						output.add(file);
				}
			} while (folders.size() != 0);
			return output;
		} catch (Exception e) {
			 throw new DefaultExceptionInInitializerError(e);
		}
	}

	public static ArrayList<File> getFilesInFolder(ArrayList<File> files) {
		ArrayList<File> output = new ArrayList<>();
		for (File file : files) {
			if (file.isDirectory())
				for (File fileToAdd : getFilesInFolder(file)) {
					output.add(fileToAdd);
				}
			else
				output.add(file);
		}
		return output;
	}

	public static File[] getFilesAndFolderInFolder(File folder) {
		return folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});
	}
}
