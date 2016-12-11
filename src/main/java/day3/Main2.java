package day3;

import util.ReaderLocal;

public class Main2 {

	public static void main(final String[] args) {
		final String[] triangles = ReaderLocal.readLines("input3.txt");
		int count = 0;

		for (int i = 0; i < triangles.length; i += 3) {
			final String[] nrLine1 = Main1.splitLine(triangles[i]);
			final String[] nrLine2 = Main1.splitLine(triangles[i + 1]);
			final String[] nrLine3 = Main1.splitLine(triangles[i + 2]);

			for (int j = 0; j < 3; j++) {
				final int a = Integer.parseInt(nrLine1[j]);
				final int b = Integer.parseInt(nrLine2[j]);
				final int c = Integer.parseInt(nrLine3[j]);

				if (Main1.isTriangle(a, b, c)) {
					count++;
				}
			}

		}

		System.out.println(count);
	}

}
