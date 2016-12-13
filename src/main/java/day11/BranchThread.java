package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.ArrayUtilLocal;
import util.CollectionLocalUtils;

public class BranchThread extends Thread {

	private byte[][] grid;
	private List<Integer> historyStates;
	private byte moves;
	private byte floor;


	public BranchThread(final byte[][] grid, final List<Integer> historyStates, final byte moves, final byte floor) {
		super();
		this.grid = grid;
		this.historyStates = historyStates;
		this.moves = moves;
		this.floor = floor;
	}

	@Override
	public void run() {
		branchSolution(historyStates, grid, moves, floor);
	}

	private void branchSolution(final List<Integer> historyStates, final byte[][] grid, final int moves, final byte floor) {
		final List<Integer> historyStatesCopy;

		// rule 1: Cut solutions that will be worst than current
		if (moves > MainWithThread.MAX_MOVES || moves > MainWithThread.minSolution.get()) {
			return;
		}
		// rule 2: Cut if was previously in this state
		final int state = Arrays.deepHashCode(grid);
		if (historyStates.contains(state)) {
			return;
		} else {
			historyStatesCopy = CollectionLocalUtils.cloneList(historyStates);
			historyStatesCopy.add(state);
		}
		// rule 3: Cut when end condition is met
		if (isEndState(grid)) {
			if(moves < MainWithThread.minSolution.get()) {
				MainWithThread.minSolution .set(moves);;
				System.out.println(moves);
			}
			if(moves == 7) {
				System.out.println();
			}

		}

		final List<Byte[]> permutations = getPermutations(grid[floor], floor);

		for (final Byte[] permutation : permutations) {
			applyStateChange(grid, permutation, floor);
			if (isValidConfiguration(grid)) {
				final byte[][] gridNewState = ArrayUtilLocal.deepCopy(grid);
				if(moves % MainWithThread.THREAD_POOL == 0) {
					BranchThread thread = new BranchThread(gridNewState, historyStatesCopy, (byte) (moves + 1), (byte) (floor + permutation[0]));
					thread.start();
				} else {
					branchSolution(historyStatesCopy, gridNewState, moves + 1, (byte) (floor + permutation[0]));
				}
			}
			revertStateChange(grid, permutation, floor);
		}

		return;
	}

	private void applyStateChange(final byte[][] grid, final Byte[] permutation, final byte floor) {
		final byte dir = permutation[0];
		final byte index1 = permutation[1];
		final byte elem1 = permutation[2];
		final byte index2 = permutation[3];
		final byte elem2 = permutation[4];

		grid[floor + dir][index1] = elem1;
		grid[floor][index1] = 0;

		if (index2 != MainWithThread.NONE) {
			grid[floor + dir][index2] = elem2;
			grid[floor][index2] = 0;
		}
	}

	private void revertStateChange(final byte[][] grid, final Byte[] permutation, final byte floor) {
		final byte dir = permutation[0];
		final byte index1 = permutation[1];
		final byte elem1 = permutation[2];
		final byte index2 = permutation[3];
		final byte elem2 = permutation[4];

		grid[floor + dir][index1] = 0;
		grid[floor][index1] = elem1;

		if (index2 != MainWithThread.NONE) {
			grid[floor + dir][index2] = 0;
			grid[floor][index2] = elem2;
		}
	}

	/**
	 * Get combinations of 3 direction, (index1, obj1), (index2,obj2) from all
	 * existing elements on a given row
	 */
	private List<Byte[]> getPermutations(final byte[] row, final byte floor) {
		final List<Byte[]> result = new ArrayList<>();

		for (byte i = 0; i < MainWithThread.WIDTH - 1; i++) {
			if (row[i] != 0) {
				assignPermutation(result, floor, i, row[i], MainWithThread.NONE, MainWithThread.NONE);
				for (byte j = (byte) (i + 1); j < MainWithThread.WIDTH; j++) {
					if (row[j] != 0) {
						assignPermutation(result, floor, i, row[i], j, row[j]);
					}
				}
			}
		}

		java.util.Collections.sort(result, NextMoveComparators.CHIPS_UP);

		return result;
	}

	private void assignPermutation(final List<Byte[]> result, final byte floor, final byte index1, final byte elem1,
			final byte index2, final byte elem2) {

		if (floor > 0) {
			result.add(new Byte[] { MainWithThread.DOWN, index1, elem1, index2, elem2 });
		}
		if (floor < MainWithThread.HEIGHT - 1) {
			result.add(new Byte[] { MainWithThread.UP, index1, elem1, index2, elem2 });
		}
	}

	private boolean isValidConfiguration(final byte[][] grid) {
		for (int i = 0; i < MainWithThread.HEIGHT; i++) {

			final List<Byte> chips = new ArrayList<>();
			final List<Byte> generators = new ArrayList<>();

			for (int j = 0; j < MainWithThread.WIDTH; j++) {
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

	private boolean isEndState(final byte[][] grid) {
		for (int i = 0; i < MainWithThread.WIDTH; i++) {
			if (grid[MainWithThread.HEIGHT-1][i] == 0) {
				return false;
			}
		}

		return true;
	}

}
