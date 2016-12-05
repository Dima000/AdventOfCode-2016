package day5;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {

	private static final String PUZZLE_INPUT = "cxdnnyjw";
	private static final StringBuilder password = new StringBuilder();

	public static void main(final String[] args) {

		for (int i = 0; password.length() < 8; i++) {
			String md5Hash = DigestUtils.md5Hex(PUZZLE_INPUT + i);

			if (isValid(md5Hash)) {
				password.append(md5Hash.charAt(5));
			}
		}

		System.out.print(password.toString());
	}

	public static boolean isValid(final String hash) {
		for (int j = 0; j < 5; j++) {
			if ('0' != hash.charAt(j)) {
				return false;
			}
		}

		return true;
	}

}
