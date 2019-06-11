package services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import configs.DependencyInjectorMain;
import interfaces.BasicEmployeeRecord;
import interfaces.DependencyInjector;

public class MainEmployeesProgram {
		
	private DependencyInjector injector = DependencyInjectorMain.getInstance();
	private EmployeeCreatorService employeeCreatorService = injector.getDependency(EmployeeCreatorService.class);
	private ResultsHandler resultsHandler = injector.getDependency(ResultsHandler.class);

	public void startProg() {
		List<BasicEmployeeRecord> employeesRecords = employeeCreatorService.getObjectsFromStrings();
		Collection<List<BasicEmployeeRecord>> normalizedRecords = resultsHandler.getNormalizedRecords(employeesRecords);
		normalizedRecords.stream().forEach(resultsHandler::getBiggestDateInterval);
		resultsHandler.checkResult();
	}

	public static void main(String[] args) throws IOException {
		MainEmployeesProgram starter = new MainEmployeesProgram();
		starter.startProg();
	}
}
