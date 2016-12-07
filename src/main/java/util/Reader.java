package util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Reader {

	private static final String PATH = "src/main/resources/";

	public static String readFile(final String fileName) {
		try (FileInputStream inputStream = new FileInputStream(PATH + fileName)) {
			final String everything = IOUtils.toString(inputStream);
			return everything;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[] readLines(final String fileName) {
		return readFile(fileName).split("\n");
	}

}
