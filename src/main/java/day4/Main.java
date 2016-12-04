package day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;

import ulit.ReadFile;

public class Main {

	public static void main(final String[] args) {
		final String input = ReadFile.readFromFile("src/main/resources/input4.txt");
		final String[] lines = input.split("\n");
		int sectorIdSum = 0;

		for (final String line : lines) {
			final String[] sectors = line.split("-");
			boolean isValid = true;

			final Map<String, Pair<String,Integer>> lettersCountMap = countLetters(sectors);
			final List<Pair<String,Integer>> lettersCount = new ArrayList<>(lettersCountMap.values());
			Collections.sort(lettersCount, new LettersComparator());

			final Pair<String,Integer> controlFields = getControlFields(sectors[sectors.length-1].trim());

			for(int i=0;i<5;i++) {
				final String checkSumLetter = Character.toString(controlFields.getKey().charAt(i));
				final String sortedArrayLetter = lettersCount.get(i).getKey();
				final boolean sameLetter = Objects.equals(checkSumLetter, sortedArrayLetter);
				isValid = isValid && sameLetter;
			}

			if(isValid) {
				sectorIdSum+=controlFields.getValue();
			}
		}

		System.out.println(sectorIdSum);
	}

	public static Pair<String, Integer> getControlFields(final String lastSector) {
		final int length = lastSector.length();
		final String checkSum = lastSector.substring(length-6, length-1);
		final String sectorId = lastSector.substring(0, length-7);

		return Pair.of(checkSum, Integer.parseInt(sectorId));
	}

	private static Map<String, Pair<String,Integer>> countLetters(final String[] sectors) {
		final Map<String, Pair<String,Integer>> letters = new HashMap<>();
		for (int i = 0; i < sectors.length - 1; i++) {

			final String sector = sectors[i];
			for (int j = 0; j < sector.length(); j++) {
				final String letter = Character.toString(sector.charAt(j));
				final Pair<String, Integer> letterInMap = letters.get(letter);
				final int newCount = letterInMap == null ? 1 : letterInMap.getValue() + 1;
				letters.put(letter, Pair.of(letter, newCount));
			}

		}
		return letters;
	}
}
