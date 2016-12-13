package day11;

import java.util.ArrayList;
import java.util.List;

import util.CollectionLocalUtils;
import util.StringUtilLocal;

public class Main {

	static int HEIGHT = 4;
	static byte PAIRS = 5;
	static int WIDTH = PAIRS * 2;

	static byte NONE = -100;
	static byte UP = 1;
	static byte DOWN = -1;

	static byte MAX_MOVES = 20;

	static Integer minSolution = Integer.MAX_VALUE;

	final static String state10 = "0222201111";
	// { 1, 0, 0, 0, 10, 0, 0, 0 }
	// { 0, 0, 0, 0, 0, 20, 30, 40 }
	// { 0, 2, 3, 4, 0, 0, 0, 0 }
	// { 0, 0, 0, 0, 0, 0, 0, 0 }

	final static String state4 = "0012";
	// { 1, 2, 0, 0}
	// { 0, 0, 10, 0}
	// { 0, 0, 0, 20}
	// { 0, 0, 0, 0 }

	public static void main(final String[] args) {
		final List<Integer> historyStates = new ArrayList<>();
		final byte floor = 0;
		final int moves = 0;

		branchSolution(historyStates, state10, moves, floor);

		System.out.println(minSolution);
	}

	private static void branchSolution(final List<Integer> historyStates, String state, final int moves,
			final int floor) {
		final List<Integer> historyStatesCopy;

		// rule 1: Cut solutions that will be worst than current
		if (moves > MAX_MOVES || moves > minSolution) {
			return;
		}
		// rule 2: Cut if was previously in this state
		final int stateHash = state.hashCode();
		if (historyStates.contains(state)) {
			return;
		} else {
			historyStatesCopy = CollectionLocalUtils.cloneList(historyStates);
			historyStatesCopy.add(stateHash);
		}
		// rule 3: Cut when end condition is met
		if (isEndState(state)) {
			if (moves < minSolution) {
				minSolution = moves;
				System.out.println(moves);
			}
		}

		final List<Byte[]> permutations = getPermutations(state, floor);
		for (final Byte[] permutation : permutations) {

			final String newState = applyStateChange(state, permutation);
			if (isValidConfiguration(newState)) {
				branchSolution(historyStatesCopy, newState, moves + 1, permutation[0]);
			}
		}

		return;
	}

	private static String applyStateChange(String state, final Byte[] nextMove) {
		final StringBuilder newState = new StringBuilder(state);
		final char floorToGo = Byte.toString(nextMove[0]).charAt(0);
		final byte elem1Pos = nextMove[1];
		final byte elem2Pos = nextMove[2];

		newState.setCharAt(elem1Pos, floorToGo);
		if (elem2Pos != NONE) {
			newState.setCharAt(elem2Pos, floorToGo);
		}

		return newState.toString();
	}

	/**
	 * Get combinations of 3 direction, (index1, obj1), (index2,obj2) from all
	 * existing elements on a given row
	 */
	private static List<Byte[]> getPermutations(String state, final int floor) {
		final List<Byte[]> result = new ArrayList<>();
		final List<Byte> elements = new ArrayList<>();
		final char floorAsChar = StringUtilLocal.getCharDigit(floor);

		for (byte i = 0; i < WIDTH; i++) {
			if (state.charAt(i) == floorAsChar) {
				elements.add(i);
			}
		}

		final int size = elements.size();

		for (byte i = 0; i < size - 1; i++) {
			createPermutation(result, floor, elements.get(i), NONE);

			for (int j = i + 1; j < size; j++) {
				createPermutation(result, floor, elements.get(i), elements.get(j));
			}
		}

		if (size > 0) {
			createPermutation(result, floor, elements.get(size - 1), NONE);
		}

		return result;
	}

	private static void createPermutation(List<Byte[]> result, final int floor, final byte elemPos1,
			byte elemPos2) {
		if (floor > 0) {
			result.add(new Byte[] { (byte) (floor + DOWN), elemPos1, elemPos2 });
		}
		if (floor < HEIGHT - 1) {
			result.add(new Byte[] { (byte) (floor + UP), elemPos1, elemPos2 });
		}
	}

	private static boolean isValidConfiguration(final String state) {
		final byte count = PAIRS;

		for (byte i = 0; i < count; i++) {
			final char chipFloor = state.charAt(i);
			if (chipFloor == state.charAt(i + count)) {
				continue;
			} else {
				for (byte j = count; j < count * 2; j++) {
					if (chipFloor == state.charAt(j)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private static boolean isEndState(final String state) {
		for (int i = 0; i < WIDTH; i++) {
			if (state.charAt(i) != '3') {
				return false;
			}
		}

		return true;
	}

}
