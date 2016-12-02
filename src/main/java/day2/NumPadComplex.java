package day2;

import java.util.Arrays;

public class NumPadComplex {
	//     1
	//   2 3 4
	// 5 6 7 8 9
	//  10 11 12
	//     13

	private int current;

	NumPadComplex(final int current) {
		this.current = current;
	}

	public void changeButton(final char instruction) {
		switch (instruction) {
		case 'U':
			current = Arrays.asList(5, 2, 1, 4, 9).contains(current) ? current : current - getValue('U');
			break;
		case 'D':
			current = Arrays.asList(5, 10, 13, 12, 9).contains(current) ? current : current + getValue('D');
			break;
		case 'R':
			current = Arrays.asList(1, 4, 9, 12, 13).contains(current) ? current : ++current;
			break;
		case 'L':
			current = Arrays.asList(1, 2, 5, 10, 13).contains(current) ? current : --current;
			break;

		}
	}

	public int getSelection() {
		return current;
	}

	private int getValue(final char dir) {
		final int level = getLevel();
		final String key = dir == 'U' ? Integer.toString(level - 1) + level : level + Integer.toString(level + 1);

		switch (key) {
		case "12":
		case "45":
			return 2;
		case "23":
		case "34":
			return 4;
		default:
			return 0;
		}

	};

	private int getLevel() {
		if (current == 1) {
			return 1;
		} else if (Arrays.asList(2, 3, 4).contains(current)) {
			return 2;
		} else if (Arrays.asList(5, 6, 7, 8, 9).contains(current)) {
			return 3;
		} else if (Arrays.asList(10, 11, 12).contains(current)) {
			return 4;
		} else {
			return 5;
		}
	}

}
