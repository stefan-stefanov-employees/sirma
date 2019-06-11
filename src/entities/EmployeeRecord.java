package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import interfaces.BasicEmployeeRecord;

public class EmployeeRecord implements  BasicEmployeeRecord {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private int empId;
	private int projectId;
	private Date dateFrom;
	private Date dateTo;

	@Override
	public int getEmpId() {
		return empId;
	}

	@Override
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public int getProjectId() {
		return projectId;
	}

	@Override
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public Date getDateFrom() {
		return dateFrom;
	}

	@Override
	public void setDateFrom(String dateFrom) {
		try {
			this.dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(dateFrom);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Date getDateTo() {
		return dateTo;
	}

	@Override
	public void setDateTo(String dateTo) {
		if (dateTo.equals("NULL")) {
			this.dateTo = new Date();
		} else
			try {
				this.dateTo = new SimpleDateFormat(DATE_FORMAT).parse(dateTo);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}

}
