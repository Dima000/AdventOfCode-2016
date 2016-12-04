package day4;

import org.apache.commons.lang3.tuple.Pair;

import ulit.ReadFile;

public class Main2 {

	public static void main(final String[] args) {
		final String input = ReadFile.readFromFile("src/main/resources/input4.txt");
		final String[] lines = input.split("\n");

		for (final String line : lines) {
			final String[] sectors = line.split("-");
			final Pair<String,Integer> controlFields = Main.getControlFields(sectors[sectors.length-1].trim());


		}

	}



}
