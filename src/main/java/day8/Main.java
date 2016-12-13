package day8;

import org.apache.commons.lang3.ArrayUtils;

import util.ReaderLocal;

public class Main {

	static final int HEIGHT = 6;
	static final int WIDTH = 50;
	static final boolean[][] LCD = new boolean[HEIGHT][WIDTH];
	static final int RECTANGLE = 0;
	static final int SHIFT_ROW = 1;
	static final int SHIFT_COLUMN = 2;

	public static void main(final String[] args) {
		String[] lines = ReaderLocal.readLines("input8.txt");

		for (String line : lines) {
			int[] instructionParams = decodeInstruction(line);
			int instruction = instructionParams[0];
			int param1 = instructionParams[1];
			int param2 = instructionParams[2];

			switch (instruction) {
				case RECTANGLE: rectangle(param1, param2); break;
				case SHIFT_ROW: rowShift(param1, param2); break;
				case SHIFT_COLUMN: columnShift(param1, param2); break;
			}
		}

		int count = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if(LCD[i][j]) {
					count++;
				}
			}
		}

		printMatrix();
		System.out.println(count);
	}

	private static void columnShift(final int param1, final int param2) {
		boolean[] columnCopy = new boolean[HEIGHT];
		for (int j = 0; j < HEIGHT; j++) {
			columnCopy[j] = LCD[j][param1];
		}

		for (int j = 0; j < HEIGHT; j++) {
			int shiftIndex = (param2 + j) % HEIGHT;
			LCD[shiftIndex][param1] = columnCopy[j];
		}
	}

	private static void rowShift(final int param1, final int param2) {
		ArrayUtils.shift(LCD[param1], param2);
	}

	private static void rectangle(final int param1, final int param2) {
		for (int i = 0; i < param2; i++) {
			for (int j = 0; j < param1; j++) {
				LCD[i][j] = true;
			}
		}
	}

	public static int[] decodeInstruction(final String instructionLine) {
		int instruction;
		if (instructionLine.contains("rect")) {
			instruction = RECTANGLE;
		}
		else if (instructionLine.contains("row")) {
			instruction = SHIFT_ROW;
		}
		else {
			instruction = SHIFT_COLUMN;
		}

		String params[] = instructionLine.replaceAll("\\D+", " ").trim().split("\\D+");

		return new int[] { instruction, Integer.parseInt(params[0]), Integer.parseInt(params[1]) };
	}

	static void printMatrix() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				char toPrint = LCD[i][j] ? '*' : ' ';
				System.out.print(toPrint);
			}
			System.out.println();
		}
	}

}
