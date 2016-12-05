package day3;

import util.ReadFile;

public class Main1 {

	public static void main(final String[] args) {
		final String input = ReadFile.readFromFile("src/main/resources/input3.txt");
		final String[] triangles = input.split("\n");
		int count = 0;

		for (final String element : triangles) {
			final String[] vertices = splitLine(element);
			final int a = Integer.parseInt(vertices[0]);
			final int b = Integer.parseInt(vertices[1]);
			final int c = Integer.parseInt(vertices[2]);

			if (isTriangle(a,b,c)) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static boolean isTriangle(final int a, final int b, final int c) {
		return a + b > c && a + c > b && b + c > a;
	}

	public static String[] splitLine(final String line) {
		return line.replaceAll("( |\t)+", " ").trim().split(" ");
	}

}
