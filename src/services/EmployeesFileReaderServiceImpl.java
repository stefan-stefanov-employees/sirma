package services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import interfaces.FileReaderService;

public class EmployeesFileReaderServiceImpl implements FileReaderService {

	private static final String COMMA_DELIMITER = ",";
	private static final String filePath = "/staticResources/EmployeesFile.csv";

	@Override
	public List<List<String>> readFromFile() {

		InputStream is = this.getClass().getResourceAsStream(filePath);
		List<List<String>> all = new LinkedList<List<String>>();
		try (Scanner scanner = new Scanner(is);) {
			while (scanner.hasNextLine()) {
				all.add(parseEachValueFromLine(scanner.nextLine()));
			}
		}

		return all;
	}

	private List<String> parseEachValueFromLine(String line) {
		List<String> parsedVal = new ArrayList<String>(4);
		try (Scanner lineScanner = new Scanner(line)) {

			lineScanner.useDelimiter(COMMA_DELIMITER);
			// EmpID, ProjectID, DateFrom, DateTo
			parsedVal.add(lineScanner.next());
			parsedVal.add(lineScanner.next());
			parsedVal.add(lineScanner.next());
			parsedVal.add(lineScanner.next());
		}
		return parsedVal;
	}
}
