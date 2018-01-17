package ifphobic.workTime.model;

public class ReportDay {

	private double workTime;
	private double targetTime;
	private DayType dayType;

	public double getWorkTime() {
		return workTime;
	}

	public void setWorkTime(double workTime) {
		this.workTime = workTime;
	}

	public double getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(double targetTime) {
		this.targetTime = targetTime;
	}

	public DayType getDayType() {
		return dayType;
	}

	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}

	public Double getDelta() {
		if (workTime == 0 && targetTime == 0) {
			return null;
		}
		return workTime - targetTime;
	}

}
