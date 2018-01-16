package ifphobic.workTime.model;

public class ReportDay {

	
	private final double workTime;
	private final double targetTime;

	private final DayType dayType;

	public ReportDay(double workTime, double targetTime, DayType dayType) {
		super();
		this.workTime = workTime;
		this.targetTime = targetTime;
		this.dayType = dayType;
	}

	public double getWorkTime() {
		return workTime;
	}

	public double getTargetTime() {
		return targetTime;
	}

	public DayType getDayType() {
		return dayType;
	}
	
	public String getDelta() {
		if (workTime == 0 && targetTime == 0) {
			return "";
		}
		return workTime - targetTime + "";
	}

}
