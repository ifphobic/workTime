package ifphobic.workTime.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ReportMonth {

	private String name;
	private List<ReportDay> reportDays = new ArrayList<>();
	private Duration delta = Duration.ZERO;

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

	public Duration getDelta() {
		return delta;
	}

	public void setDelta(Duration delta) {
		this.delta = delta;
	}

	public void addDelta(Duration delta) {
			this.delta = this.delta.plus(delta);
	}

}
