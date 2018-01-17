package ifphobic.workTime.model;

import java.util.ArrayList;
import java.util.List;

public class ReportMonth {

	private String name;
	private List<ReportDay> reportDays = new ArrayList<>();
	private double delta;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ReportDay> getReportDays() {
		return reportDays;
	}
	public void setReportDays(List<ReportDay> reportDays) {
		this.reportDays = reportDays;
	}
	public double getDelta() {
		return delta;
	}
	public void addDelta(double delta) {
		this.delta += delta;
	}
	
	
}
