package interfaces;

import java.util.Date;

public interface BasicEmployeeRecord {
	public int getEmpId();
	public void setEmpId(int empId);
	public int getProjectId();
	public void setProjectId(int projectId);
	public Date getDateFrom();
	public Date getDateTo();
	public void setDateTo(String dateTo);
	void setDateFrom(String dateFrom);
}
