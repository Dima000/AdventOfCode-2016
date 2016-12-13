package util;

public class StringUtilLocal {

	public static String[] getNumbers(final String input) {
		return input.replaceAll("\\D+", " ").trim().split(" ");
	}
	
	public static char getCharDigit(int digit) {
		return String.valueOf(digit).charAt(0);
	}

}
