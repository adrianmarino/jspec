package ar.com.nonosoft.test.jspec.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssertionErrorUtils {

	public static List<String> errorLines(AssertionError error) {
		List<String> lines= new ArrayList<>();
		Scanner scanner = new Scanner(error.getMessage());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().replace("\n", "");
			if (!line.isEmpty()) lines.add(line);
		}
		return lines;
	}
}
