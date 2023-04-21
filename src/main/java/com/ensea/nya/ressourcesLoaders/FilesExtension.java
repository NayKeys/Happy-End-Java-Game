package com.ensea.nya.ressourcesLoaders;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesExtension {

	public static String get(Path path) {
		return get(path.toString());
	}

	public static String get(File file) {
		return get(file.getPath());
	}

	public static String get(String path) {
		return path.substring(path.lastIndexOf("."));
	}

	public static boolean isEqualsTo(File file, String extension) {
		return get(file).equals(extension);
	}

	public static boolean isEqualsTo(String file, String extension) {
		return get(file).equals(extension);
	}

	public static boolean isEqualsTo(Path file, String extension) {
		return get(file).equals(extension);
	}

	public static String fileType(String extension) {
		ArrayList<String> picture = new ArrayList<>(
				Arrays.asList(new String[] { "Picture", ".gif", ".png", ".jpg", ".bmp", ".tiff", ".jpeg" }));
		ArrayList<String> movie = new ArrayList<>(
				Arrays.asList(new String[] { "Movie", ".mp4", ".mov", ".wmv", ".wmf", ".m4v", ".avi" }));
		ArrayList<String> text = new ArrayList<>(
				Arrays.asList(new String[] { "Text", ".txt", ".odf", ".xlsx", ".docx", ".rtf", ".pdf", ".odt" }));
		ArrayList<String> music = new ArrayList<>(
				Arrays.asList(new String[] { "Music", ".mp3", ".aac", ".ogg", ".wav", ".flac", ".aiff", ".wma" }));

		List<ArrayList<String>> everyExtensions = new ArrayList<>();
		everyExtensions.add(picture);
		everyExtensions.add(movie);
		everyExtensions.add(text);
		everyExtensions.add(music);

		for (ArrayList<String> type : everyExtensions) {
			if (type.contains(extension))
				return type.get(0);
		}
		return "File";
	}
}
