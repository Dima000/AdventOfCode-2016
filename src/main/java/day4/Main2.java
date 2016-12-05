package day4;

import util.ReadFile;

public class Main2 {

	public static void main(final String[] args) {
		final String input = ReadFile.readFromFile("src/main/resources/input4.txt");
		final String[] lines = input.split("\n");

		for (final String line : lines) {
			final String[] sectors = line.split("-");
			final int sectorId = Main.getControlFields(sectors[sectors.length - 1].trim()).getValue();

			final StringBuilder str = new StringBuilder();
			for (int i = 0; i < sectors.length - 1; i++) {

				for (int j = 0; j < sectors[i].length(); j++) {
					str.append(decodeLetter(sectors[i].charAt(j), sectorId));
				}

				if(i!= sectors.length - 2)str.append(decodeSpace(sectorId));
			}

			final String decriptedName = str.toString();
			if(decriptedName.contains("north") || decriptedName.contains("pole")) {
				System.out.println("Name: " + decriptedName + " Sum: " + sectorId);
			}

		}



	}

	private static char decodeLetter(final char inputLetter, final int shiftTimes) {
		final int inputLetterOffset = inputLetter - 'a';
		final int offset = (shiftTimes + inputLetterOffset) % 26;

		return (char) ('a' + offset);
	}

	private static char decodeSpace(final int shiftTimes) {
		return shiftTimes % 2 == 0 ? '-' : ' ';
	}

}
