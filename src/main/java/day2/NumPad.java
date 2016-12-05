package day2;

import java.util.Arrays;

public class NumPad {
	//{ 1, 2, 3 }
	//{ 4, 5, 6 }
	//{ 7, 8, 9 }

	private int current;

	NumPad(final int current) {
		this.current = current;
	}

	public void changeButton(final char instruction) {
		switch (instruction) {
			case 'U':
				current = current > 3 ? current - 3 : current;
				break;
			case 'D':
				current = current < 7 ? current + 3 : current;
				break;
			case 'R':
				current = Arrays.asList(3, 6, 9).contains(current) ? current : ++current;
				break;
			case 'L':
				current = Arrays.asList(1, 4, 7).contains(current) ? current : --current;
				break;

		}
	}

	public int getSelection() {
		return current;
	}

}
