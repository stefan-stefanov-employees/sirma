package services;

import java.util.List;

import java.util.stream.Collectors;

import configs.DependencyInjectorMain;
import entities.EmployeeRecord;
import interfaces.BasicEmployeeRecord;
import interfaces.DependencyInjector;
import interfaces.FileReaderService;
import interfaces.StringToObjectParserService;

public class EmployeeCreatorService implements StringToObjectParserService<BasicEmployeeRecord> {

	private DependencyInjector injector = DependencyInjectorMain.getInstance();
	private FileReaderService fileReader = injector.getDependency(FileReaderService.class);

	@Override
	public List<BasicEmployeeRecord> getObjectsFromStrings() {
		List<List<String>> allParsedLines = fileReader.readFromFile();
		List<BasicEmployeeRecord> allEmployeeObj = allParsedLines.stream().map(this::createObjectFromStrings)
				.collect(Collectors.toList());
		return allEmployeeObj;
	}

	@Override
	public EmployeeRecord createObjectFromStrings(List<String> rawStrings) {
		EmployeeRecord employee = new EmployeeRecord();

		// EmpID = 0, ProjectID = 1, DateFrom, DateTo
		employee.setEmpId(Integer.parseInt(rawStrings.get(0)));
		employee.setProjectId(Integer.parseInt(rawStrings.get(1)));
		employee.setDateFrom(rawStrings.get(2));
		employee.setDateTo(rawStrings.get(3));

		return employee;
	}

}
