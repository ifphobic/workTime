package ifphobic.workTime.model.xml;

import java.time.Duration;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

public class HolidayDay {

	@XmlAttribute
	private HolydayType type;

	@XmlAttribute
	private LocalDate date;

	
	@XmlAttribute
	private Duration targetTime = Duration.ZERO;


	public HolydayType getType() {
		return type;
	}


	public void setType(HolydayType type) {
		this.type = type;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Duration getTargetTime() {
		return targetTime;
	}


	public void setTargetTime(Duration targetTime) {
		this.targetTime = targetTime;
	}

	
}
