package com.ensea.nya.utilities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NayKeysUtilities {

	public static String formatNumber(String toSplit) {

		String output = "";
		if (toSplit.split(".").length == 2) { // It's a double

		}
		for (int count = toSplit.length() - 1; count >= 0; count--) {
			output += String.valueOf(toSplit.charAt(count));
			if (count % 3 == 0 && count != 0)
				output += " ";
		}
		return output;
	}

	public static int getLastIndexOf(ArrayList<Integer> list) {
		if (list.size() == 0)
			return 0;
		else
			return list.get(list.size() - 1);
	}

	public static int getLastIndexOf(ArrayList<Integer> list, int how) {
		if (list.size() - how < 0)
			return 0;
		else
			return list.get(list.size() - how);
	}

	public static int getLastIndexOf(ArrayList<Integer> list, int Index, int how) {
		if (Index - how <= 1)
			return 0;
		else
			return list.get(Index - how);
	}

	public static String getTimeEllapsed(long start, long end) {
		double secondMillis = (end - start);

		double secondes = secondMillis / 1000;

		int hours = 0, minutes = 0;

		if (secondes >= 60) {

			minutes = (int) (secondes / 60);

			if (minutes >= 60) {

				hours = minutes / 60;

			}
			minutes = minutes - hours * 60;
			secondes = secondes - (hours * 60 + minutes) * 60;
		}
		return "h:" + hours + "/m:" + minutes + "/s:" + String.format("%.3f ", secondes);
	}

	public static String validChar(KeyEvent key) {
		if (key.getKeyCode() == 8) {
			return "BackSpace";
		} else if (key.getKeyCode() == 127) {
			return "suppr";
		} else if (key.getKeyChar() == '?' && key.getKeyCode() != 44) {
			throw new IllegalArgumentException("Unknown Char");
		} else
			return String.format("%c", key.getKeyChar());
	}

	public static String getAllLines(List<String> list) {
		String allLines = "";
		for (String line : list) {
			allLines += line;
		}
		return allLines;
	}

	public static String isValidJName(String name) {
		String[] names = name.split(" ");
		if (names.length == 1) {
			return "addLastName";
		} else if (names.length < 2) {
			return "tooMuchNames";
		} else if (names[0].charAt(0) == 'J' || names[1].charAt(0) == 'j') {
			if (names[1].charAt(0) == 'J' || names[1].charAt(0) == 'j') {
				return "ok";
			} else
				return "noJ";

		} else
			return "problem";
	}

	public static void firstNameToUpperCase(String name) {

	}

	public static boolean isValidPassword(String password) {
		// Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Pattern p = Pattern.compile("\\p{Lower}{1,100}");
		Matcher m = p.matcher(password);
		return !(m.matches());
	}

	public static boolean isEmailAdress(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

	public static int charToInt(char c) {
		int output;
		switch (c) {
		case '0':
			output = 0;
			break;
		case '1':
			output = 1;
			break;
		case '2':
			output = 2;
			break;
		case '3':
			output = 3;
			break;
		case '4':
			output = 4;
			break;
		case '5':
			output = 5;
			break;
		case '6':
			output = 6;
			break;
		case '7':
			output = 7;
			break;
		case '8':
			output = 8;
			break;
		case '9':
			output = 9;
			break;
		default:
			throw new IllegalArgumentException("Impossible to convert " + c + " in type : java/lang/Integer");
		}
		return output;
	}

	public static void sendEmail(String emailSender, String emailDest) {
		// Properties properties = new Properties();

	}
	// switch (keyCode) {
	// case 0:
	// if(extendedKeyCode == 16777394) {
	// return '�';
	// } else if(extendedKeyCode == 16777465) {
	// return '�';
	// }
	//
	// case 49:
	// return '&';
	// case 50:
	// return '�';
	// case 51:
	// return '"';
	// case 52:
	// return '\'';
	// case 53:
	// return '(';
	// case 54:
	// return '-';
	// case 55:
	// return '�';
	// case 56:
	// return '_';
	// case 57:
	// return '�';
	// case 48:
	// return '�';
	// case 522:
	// return ')';
	// case 61:
	// return '=';
	// case 65:
	// return 'a';
	// case 90:
	// return 'z';
	// case 69:
	// return 'e';
	// case 82:
	// return 'r';
	// case 84:
	// return 't';
	// case 89:
	// return 'y';
	// case 85:
	// return 'u';
	// case 73:
	// return 'i';
	// case 79:
	// return 'o';
	// case 80:
	// return 'p';
	// case 76:
	// return 'l';
	// case 77:
	// return 'm';
	// case 44:
	// return '?';
	// case 75 :
	// return 'k';
	// case 515 :
	// return '$';
	// case 151 :
	// return '*';
	// case 517 :
	// return '!';
	// case 513 :
	// return ':';
	// case 59 :
	// return ';';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	// case 77 :
	// return '=';
	//
	//
	// default:
	// throw new IllegalArgumentException("Not Validable char !");
}
