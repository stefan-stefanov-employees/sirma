package configs;

import java.util.HashMap;
import java.util.Map;

import interfaces.DependencyInjector;
import interfaces.FileReaderService;

import services.EmployeeCreatorService;
import services.EmployeesFileReaderServiceImpl;
import services.ResultsHandler;

public final class DependencyInjectorMain implements DependencyInjector {
	private static DependencyInjectorMain INSTANCE;
	private static Map<Class, Object> allDependencies = new HashMap<Class, Object>();

	// here organize all service injections in order to loose-couple
	// dependencies:
	static void init() {
		allDependencies.put(FileReaderService.class, new EmployeesFileReaderServiceImpl());
		allDependencies.put(EmployeeCreatorService.class, new EmployeeCreatorService());
		allDependencies.put(ResultsHandler.class, new ResultsHandler());
	}

	@Override
	public Object getDependency(Class clazz) {
		return allDependencies.get(clazz);
	}

	private DependencyInjectorMain() {
	}

	public static DependencyInjectorMain getInstance() {
		if (INSTANCE == null) {
			synchronized (DependencyInjectorMain.class) {
				if (INSTANCE == null) {
					INSTANCE = new DependencyInjectorMain();
					init();
				}
			}
		}
		return INSTANCE;
	}

}
