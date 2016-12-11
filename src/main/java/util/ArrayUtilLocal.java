package util;

public class ArrayUtilLocal {

	public static <T> T[][] deepCopy(final T[][] matrix) {
		return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
	}

}
