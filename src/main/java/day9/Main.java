package day9;

import util.ReaderLocal;

public class Main {

	private static String input = ReaderLocal.readFile("input9.txt");

	public static void main(final String[] args) {
		final long length = findLength(0, input);

		System.out.println(length);
	}

	private static long findLength(final int startPos, final String subtringOfInput) {
		int i = 0;
		long length = 0;

		while (i < subtringOfInput.length()) {
			if (subtringOfInput.charAt(i) == '(') {
				final int[] numbers = parseInstruction(startPos + i);
				final int pos = numbers[0];
				final int range = numbers[1];

				final String substringToProcess = input.substring(pos, pos + range);
				length+= numbers[2] * findLength(numbers[0], substringToProcess);

				i = pos - startPos + range;
			} else {
				i++;
				length++;
			}
		}
		return length;
	}

	private static int[] parseInstruction(final int startPos) {
		int range = 0;
		int times = 0;
		StringBuilder nrHolder = new StringBuilder();

		for (int i = startPos + 1; i < input.length(); i++) {
			final char currentChar = input.charAt(i);
			switch (currentChar) {
			case 'x':
				range = Integer.parseInt(nrHolder.toString());
				nrHolder = new StringBuilder();
				break;
			case ')':
				times = Integer.parseInt(nrHolder.toString());
				return new int[] { i + 1, range, times };
			default:
				nrHolder.append(currentChar);
			}

		}

		return null;
	}

}
