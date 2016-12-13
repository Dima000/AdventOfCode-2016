package util;

public class ArrayUtilLocal {

	public static <T> T[][] deepCopy(final T[][] matrix) {
		return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
	}

	public static boolean contains(byte[] array, byte toFind) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == toFind) {
				return true;
			}
		}

		return false;
	}

	public static byte[][] deepCopy(byte[][] grid) {
		return java.util.Arrays.stream(grid).map(el -> el.clone()).toArray($ -> grid.clone());
	}

}
