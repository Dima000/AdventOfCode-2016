package day11;

import java.util.ArrayList;
import java.util.List;

import util.CollectionLocalUtils;
import util.StringUtilLocal;

public class BranchThread extends Thread {

	private final String state;
	private final List<Integer> historyStates;
	private final int moves;
	private final int floor;

	public BranchThread(String state, final List<Integer> historyStates, final int moves, final int floor) {
		super();
		this.state = state;
		this.historyStates = historyStates;
		this.moves = moves;
		this.floor = floor;
	}

	@Override
	public void run() {
		branchSolution(historyStates, state, moves, floor);
	}

	private void branchSolution(final List<Integer> historyStates, String state, final int moves,
			final int floor) {
		final List<Integer> historyStatesCopy;

		// rule 1: Cut solutions that will be worst than current
		if (moves > MainWithThread.MAX_MOVES || moves > MainWithThread.minSolution.get()) {
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
			if (moves < MainWithThread.minSolution.get()) {
				MainWithThread.minSolution.set(moves);
				System.out.println(moves);
			}
		}

		final List<Byte[]> permutations = getPermutations(state, floor);
		for (final Byte[] permutation : permutations) {

			final String newState = applyStateChange(state, permutation);

			if (isValidConfiguration(newState)) {
				if (moves % MainWithThread.THREAD_POOL == 0) {
					final BranchThread thread = new BranchThread(state, historyStatesCopy, moves,
							permutation[0]);
					thread.start();
				} else {
					branchSolution(historyStatesCopy, newState, moves + 1, permutation[0]);
				}
			}
		}

		return;
	}

	private String applyStateChange(String state, final Byte[] nextMove) {
		final StringBuilder newState = new StringBuilder(state);
		final char floorToGo = Byte.toString(nextMove[0]).charAt(0);
		final byte elem1Pos = nextMove[1];
		final byte elem2Pos = nextMove[2];

		newState.setCharAt(elem1Pos, floorToGo);
		if (elem2Pos != MainWithThread.NONE) {
			newState.setCharAt(elem2Pos, floorToGo);
		}

		return newState.toString();
	}

	/**
	 * Get combinations of 3 direction, (index1, obj1), (index2,obj2) from all
	 * existing elements on a given row
	 */
	private List<Byte[]> getPermutations(String state, final int floor) {
		final List<Byte[]> result = new ArrayList<>();
		final List<Byte> elements = new ArrayList<>();
		final char floorAsChar = StringUtilLocal.getCharDigit(floor);

		for (byte i = 0; i < MainWithThread.WIDTH; i++) {
			if (state.charAt(i) == floorAsChar) {
				elements.add(i);
			}
		}

		final int size = elements.size();

		for (byte i = 0; i < size - 1; i++) {
			createPermutation(result, floor, elements.get(i), MainWithThread.NONE);

			for (int j = i + 1; j < size; j++) {
				createPermutation(result, floor, elements.get(i), elements.get(j));
			}
		}

		if (size > 0) {
			createPermutation(result, floor, elements.get(size - 1), MainWithThread.NONE);
		}

		return result;
	}

	private void createPermutation(List<Byte[]> result, final int floor, final byte elemPos1, byte elemPos2) {
		if (floor > 0) {
			result.add(new Byte[] { (byte) (floor + MainWithThread.DOWN), elemPos1, elemPos2 });
		}
		if (floor < MainWithThread.HEIGHT - 1) {
			result.add(new Byte[] { (byte) (floor + MainWithThread.UP), elemPos1, elemPos2 });
		}
	}

	private boolean isValidConfiguration(final String state) {
		final byte count = MainWithThread.PAIRS;

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

	private boolean isEndState(final String state) {
		for (int i = 0; i < MainWithThread.WIDTH; i++) {
			if (state.charAt(i) != '3') {
				return false;
			}
		}

		return true;
	}

}
