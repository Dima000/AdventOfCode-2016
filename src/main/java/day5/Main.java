package day5;

import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

	private static final String PUZZLE_INPUT = "cxdnnyjw";

	public static void main(final String[] args) {

		int i = 0;
		StringBuilder password = new StringBuilder();

		while (true) {
			String md5Hash = DigestUtils.md5Hex(PUZZLE_INPUT + i);

			if (isValid(md5Hash)) {
				password.append(md5Hash.charAt(5));
			}

			if (password.length() >= 8) {
				break;
			}

			i++;
		}

		System.out.print(password.toString());

	}

	public static boolean isValid(final String hash) {
		for (int j = 0; j < 5; j++) {
			if (!Objects.equals('0', hash.charAt(j))) {
				return false;
			}
		}

		return true;
	}

}
