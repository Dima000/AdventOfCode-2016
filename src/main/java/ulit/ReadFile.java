package ulit;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ReadFile {

	public static String readFromFile(final String filePath) {
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			// Session IOUtils;
			final String everything = IOUtils.toString(inputStream);
			return everything;
		} catch (final IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
