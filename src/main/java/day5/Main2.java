package day5;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

public class Main2 {

	private static final String PUZZLE_INPUT = "cxdnnyjw";
	private static final Map<Integer, Character> password = new TreeMap<>();

	public static void main(final String[] args) {
		int size = 0;

		for (int i = 0; password.size() < 8; i++) {
			String md5Hash = DigestUtils.md5Hex(PUZZLE_INPUT + i);

			if (Main.isValid(md5Hash)) {
				int pos = Character.getNumericValue(md5Hash.charAt(5));
				char passwordChar = md5Hash.charAt(6);

				if (pos >= 0 && pos <= 7 && password.get(pos) == null) {
					password.put(pos, passwordChar);
				}
			}

			//optional code
			if (password.size() > size) {
				prettyPrintValues();
				size++;
			}
		}

		prettyPrintValues();

	}

	public static void prettyPrintValues() {
		for (int i = 0; i < 8; i++) {
			Character value = password.get(i);
			char toPrint = value == null ? '-' : value;
			System.out.print(toPrint);
		}

		System.out.println();
	}

}
