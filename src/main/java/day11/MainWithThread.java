package day11;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainWithThread {

	static int WIDTH = 8;
	static int HEIGHT = 4;

	static byte NONE = -100;
	static byte UP = 1;
	static byte DOWN = -1;
	static byte IS_CHIP = 1;

	static byte THREAD_POOL = 7;
	static byte MAX_MOVES = 16;

	static AtomicInteger minSolution = new AtomicInteger(Integer.MAX_VALUE);

	final static byte[][] GRID = new byte[][] {
		{ 1, 0, 0, 0, 10, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 20, 30, 40 },
		{ 0, 2, 3, 4, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 } };

//	final static byte[][] GRID = new byte[][] {
//			{ 1, 2, 0, 0 },
//			{ 0, 0, 20, 0 },
//			{ 0, 0, 0, 10 },
//			{ 0, 0, 0, 0 } };

	public static void main(final String[] args) {
		final List<Integer> historyStates = new ArrayList<>();
		final byte floor = 0;
		final int moves = 0;
		System.out.println("Start time:" + new Date());

		BranchThread thread = new BranchThread(GRID, historyStates, (byte) moves, floor);
		thread.start();
	}

}
