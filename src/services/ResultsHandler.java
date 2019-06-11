package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import interfaces.BasicEmployeeRecord;

public class ResultsHandler {
	private static final int DAY = (1000 * 60 * 60 * 24);
	private long interval;
	private BasicEmployeeRecord emp1;
	private BasicEmployeeRecord emp2;

	public Collection<List<BasicEmployeeRecord>> getNormalizedRecords(List<BasicEmployeeRecord> employeesRecords) {

		Map<Integer, List<BasicEmployeeRecord>> map = new HashMap<Integer, List<BasicEmployeeRecord>>();
		for (BasicEmployeeRecord record : employeesRecords) {

			List<BasicEmployeeRecord> empsInProject = map.get(record.getProjectId());

			if (empsInProject == null) {
				empsInProject = new LinkedList<BasicEmployeeRecord>();
				map.put(record.getProjectId(), empsInProject);
			}
			empsInProject.add(record);
		}
		return map.values();

	}

	public void getBiggestDateInterval(List<BasicEmployeeRecord> employeesRecords) {

		for (int i = 0; i < employeesRecords.size(); i++) {
			BasicEmployeeRecord emp = employeesRecords.get(i);
			long stDate1 = emp.getDateFrom().getTime();
			long endDate1 = emp.getDateTo().getTime();

			for (int y = i; y < employeesRecords.size(); y++) {
				BasicEmployeeRecord emp2 = employeesRecords.get(y);

				// do not compare a record with itself:
				if (emp.getEmpId() == emp2.getEmpId()) {
					continue;
				}

				long stDate2 = emp2.getDateFrom().getTime();
				long endDate2 = emp2.getDateTo().getTime();

				// check if two intervals overlap:
				if (!isOverLaped(stDate1, endDate1, stDate2, endDate2)) {
					continue;
				}

				long interval = -Math.max(stDate1, stDate2) + Math.min(endDate1, endDate2);

				recordGreatestInterval(interval, emp, emp2);
			}

		}
	}

	private void recordGreatestInterval(long interval, BasicEmployeeRecord emp1, BasicEmployeeRecord emp2) {
		if (this.interval < interval) {
			this.interval = interval;
			this.emp1 = emp1;
			this.emp2 = emp2;
		}
	}

	public void checkResult() {
		System.out.println(" The biggest time frame that employees: " + emp1.getEmpId() + " and " + emp2.getEmpId()
				+ " worket togather is " + (interval / DAY) + " days.");
	}

	boolean isOverLaped(long start1, long end1, long start2, long end2) {
		return start1 <= end2 && start2 <= end1;
	}
}
