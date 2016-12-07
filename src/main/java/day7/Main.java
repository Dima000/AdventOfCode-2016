package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;

import util.Reader;

public class Main {

	static Pattern pattern = Pattern.compile("\\[\\w+\\]");

	public static void main(final String[] args) {
		final String[] lines = Reader.readLines("input7.txt");
		int counter = 0;

		for (String line : lines) {
			Matcher matcher = pattern.matcher(line);
			if (isValidSequence(matcher)) {
				counter++;
			}
		}

		System.out.println(counter);
	}

	private static boolean isValidSequence(final Matcher m) {
		Pair<List<String>, List<String>> lists = extractStrings(m);
		List<String> classA = lists.getLeft();
		List<String> classB = lists.getRight();

		return containsXYYX(classA) && !containsXYYX(classB);
	}

	private static boolean containsXYYX(final List<String> strings) {

		for (String str : strings) {
			for (int i = 0; i < str.length() - 3; i++) {
				char i1 = str.charAt(i);
				char i2 = str.charAt(i + 1);
				char i3 = str.charAt(i + 2);
				char i4 = str.charAt(i + 3);

				if (i1 != i2 && i1 == i4 && i2 == i3) {
					return true;
				}
			}
		}

		return false;
	}

	static Pair<List<String>, List<String>> extractStrings(final Matcher m) {
		List<String> classA = new ArrayList<>();
		List<String> classB = new ArrayList<>();

		for (Matcher aux = m; aux.find();) {
			String bStr = m.group();
			int length = bStr.length();
			classB.add(bStr.substring(1, length - 1));
		}

		classA.addAll(Arrays.asList(m.replaceAll(" ").split(" ")));

		return Pair.of(classA, classB);
	}

}
