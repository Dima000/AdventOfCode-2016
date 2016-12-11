package day7;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.tuple.Pair;

import util.ReaderLocal;

public class Main2 {

	public static void main(final String[] args) {
		final String[] lines = ReaderLocal.readLines("input7.txt");
		int counter = 0;

		for (String line : lines) {
			Matcher matcher = Main.pattern.matcher(line);
			if (isValidSequence(matcher)) {
				counter++;
			}
		}

		System.out.println(counter);
	}

	private static boolean isValidSequence(final Matcher m) {
		Pair<List<String>, List<String>> lists = Main.extractStrings(m);
		List<String> classA = lists.getLeft();
		List<String> classB = lists.getRight();

		for (String str : classA) {
			for (int i = 0; i < str.length() - 2; i++) {
				char i1 = str.charAt(i);
				char i2 = str.charAt(i + 1);
				char i3 = str.charAt(i + 2);
				char[] bSequence = new char[] { i2, i1, i2 };

				if (i1 == i3 &&
						i1 != i2 &&
						anyHasSequence(classB, bSequence))
				{
					return true;
				}
			}
		}

		return false;
	}

	private static boolean anyHasSequence(final List<String> list, final char[] sequence) {
		for (String bStr : list) {
			if (bStr.contains(new String(sequence))) {
				return true;
			}
		}

		return false;
	}

}
