package day6;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import util.ReaderLocal;

public class Main {

	public static final Map<Integer, Map<Character, Integer>> result = new TreeMap<>();

	public static void main(final String[] args) {
		String[] lines = ReaderLocal.readLines("input6.txt");
		initMaps(lines[0].length());

		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				Map<Character, Integer> letterCountMap = result.get(i);
				char letterToProcess = line.charAt(i);
				Integer currentCount = letterCountMap.get(letterToProcess);
				Integer nextCount = currentCount == null ? 0 : currentCount + 1;
				letterCountMap.put(letterToProcess, nextCount);
			}
		}

		for(Map<Character, Integer> letterMap : result.values()) {
			//puzzle 1
			//Character toPrint = findLetterWithHighestCount(letterMap);

			//puzzle 2
			Character toPrint = findLetterWithLowestCount(letterMap);
			System.out.print(toPrint);
		}

	}


	public static void initMaps(final int length) {
		for(int i=0;i<length;i++) {
			result.put(i, new HashMap<Character, Integer>());
		}
	}

	public static Character findLetterWithHighestCount(final Map<Character, Integer> lettersMap) {
		Map.Entry<Character, Integer> highest = null;

		for(Map.Entry<Character, Integer> letter : lettersMap.entrySet()) {
			if(highest == null || highest.getValue() < letter.getValue()) {
				highest = letter;
			}
		}

		return highest.getKey();
	}

	public static Character findLetterWithLowestCount(final Map<Character, Integer> lettersMap) {
		Map.Entry<Character, Integer> lowest = null;

		for(Map.Entry<Character, Integer> letter : lettersMap.entrySet()) {
			if(lowest == null || lowest.getValue() > letter.getValue()) {
				lowest = letter;
			}
		}

		return lowest.getKey();
	}

}
