package ifphobic.workTime.model;

import java.time.Duration;

public class ReportDay {

	private Duration workTime;
	private Duration targetTime;
	private DayType dayType;

	public void setWorkTime(Duration workTime) {
		this.workTime = workTime;
	}


	public Duration getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Duration targetTime) {
		this.targetTime = targetTime;
	}

	public Duration getWorkTime() {
		return workTime;
	}

	public DayType getDayType() {
		return dayType;
	}

	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}

	public Duration getDelta() {
		return workTime.minus(targetTime);
	}


	public boolean isPrint() {
		return !workTime.isZero() || !targetTime.isZero();
	}

}
