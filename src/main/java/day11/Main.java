package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.ArrayUtilLocal;

public class Main {

	static int WIDTH = 10;

	public static void main(final String[] args) {
		final Byte[][] GRID = new Byte[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 12, 13, 14, 15 }, { 0, 2, 3, 4, 5, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 11, 0, 0, 0, 0 } };

			final long x = Arrays.deepHashCode(GRID);
			System.out.println(x);

			final Byte[][] copy = ArrayUtilLocal.deepCopy(GRID);

			GRID[1][4] = 50;

			System.out.println(copy);

	}

	boolean branchSolution(List<Long> oldStates, byte[][] grid, int moves, int elevatorFlor) {
		final List<Byte[]> permutations = getPermutations(grid[elevatorFlor]);

		return false;
	}

	/**
	 * Get all posible permutations from the non empty values existent on a row
	 * 
	 * @param grid
	 *            A row(flor) from the matrix
	 */
	List<Byte[]> getPermutations(byte[] grid) {
		final List<Byte[]> result = new ArrayList<>();

		for (int i = 0; i < WIDTH - 1; i++) {
			final byte num1 = grid[i];
			if (num1 != 0) {
				result.add(new Byte[] { -1, grid[i] });

				for (int j = i + 1; j < WIDTH; j++) {
					if (grid[j] != 0) {
						result.add(new Byte[] { num1, grid[j] });
					}
				}
			}
		}

		return result;
	}

}
