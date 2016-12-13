package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.ArrayUtilLocal;
import util.CollectionLocalUtils;

public class Main {

	static int WIDTH = 10;
	static int HEIGHT = 4;
	static byte NONE = -100;
	static byte UP = 1;
	static byte DOWN = -1;

	static Integer minSolution = Integer.MAX_VALUE;

	 final static byte[][] GRID = new byte[][] {
	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	 { 0, 0, 0, 0, 0, 10, 20, 30, 40, 0 },
	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 50 },
	 { 1, 2, 3, 4, 5, 0, 0, 0, 0, 0 }};

//	final static byte[][] GRID = new byte[][] {
//			{ 1, 2, 0, 0 },
//			{ 0, 0, 20, 0 },
//			{ 0, 0, 0, 10 },
//			{ 0, 0, 0, 0 } };

	public static void main(final String[] args) {
		final List<Integer> historyStates = new ArrayList<>();
		final byte floor = 0;
		final int moves = 0;

		branchSolution(historyStates, GRID, moves, floor);

		System.out.println(minSolution);
	}

	static boolean branchSolution(List<Integer> historyStates, byte[][] grid, int moves, byte floor) {
		final List<Integer> historyStatesCopy;

		// rule 1: Cut solutions that will be worst than current
		if (moves > minSolution) {
			return false;
		}
		// rule 2: Cut if was previously in this state
		final int state = Arrays.deepHashCode(grid);
		if (historyStates.contains(state)) {
			return false;
		} else {
			historyStatesCopy = CollectionLocalUtils.cloneList(historyStates);
			historyStatesCopy.add(state);
		}
		// rule 3: Cut when end condition is met
		if (isEndState(grid)) {
			if(moves < minSolution) {
				minSolution = moves;
			}

			return false;
		}

		final List<Byte[]> permutations = getPermutations(grid[floor], floor);

		for (final Byte[] permutation : permutations) {
			final byte[][] gridNewState = ArrayUtilLocal.deepCopy(grid);
			applyStateChange(gridNewState, permutation, floor);
			if (isValidConfiguration(gridNewState)) {
				branchSolution(historyStatesCopy, gridNewState, moves + 1, (byte) (floor + permutation[0]));
			}
		}

		return false;
	}

	static void applyStateChange(byte[][] grid, Byte[] permutation, byte floor) {
		final byte dir = permutation[0];
		final byte index1 = permutation[1];
		final byte elem1 = permutation[2];
		final byte index2 = permutation[3];
		final byte elem2 = permutation[4];

		grid[floor + dir][index1] = elem1;
		grid[floor][index1] = 0;

		if (index2 != NONE) {
			grid[floor + dir][index2] = elem2;
			grid[floor][index2] = 0;
		}
	}

	/**
	 * Get combinations of 3 direction, (index1, obj1), (index2,obj2) from all
	 * existing elements on a given row
	 */
	static List<Byte[]> getPermutations(byte[] row, byte floor) {
		final List<Byte[]> result = new ArrayList<>();

		for (byte i = 0; i < WIDTH - 1; i++) {
			if (row[i] != 0) {
				assignPermutation(result, floor, i, row[i], NONE, NONE);
				for (byte j = (byte) (i + 1); j < WIDTH; j++) {
					if (row[j] != 0) {
						assignPermutation(result, floor, i, row[i], j, row[j]);
					}
				}
			}
		}

		return result;
	}

	private static void assignPermutation(List<Byte[]> result, byte floor, byte index1, byte elem1,
			byte index2, byte elem2) {

		if (floor < HEIGHT - 1) {
			result.add(new Byte[] { UP, index1, elem1, index2, elem2 });
		}
		if (floor > 0) {
			result.add(new Byte[] { DOWN, index1, elem1, index2, elem2 });
		}
	}

	static boolean isValidConfiguration(byte[][] grid) {
		for (int i = 0; i < HEIGHT; i++) {

			final List<Byte> chips = new ArrayList<>();
			final List<Byte> generators = new ArrayList<>();

			for (int j = 0; j < WIDTH; j++) {
				final byte current = grid[i][j];
				if (current != 0 && current < 10) {
					chips.add(grid[i][j]);
				}
				if (current >= 10) {
					generators.add(grid[i][j]);
				}
			}

			if (!chips.isEmpty() && !generators.isEmpty()) {
				for (final byte chip : chips) {
					if (!generators.contains((byte) (chip * 10))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	static boolean isEndState(byte[][] grid) {
		for (int i = 0; i < WIDTH; i++) {
			if (grid[HEIGHT-1][i] == 0) {
				return false;
			}
		}

		return true;
	}

}
