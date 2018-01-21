package ifphobic.workTime.model.xml;

import java.time.Duration;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"type", "start", "end", "targetTime"})
public class Holiday {

	@XmlAttribute
	private HolydayType type;

	@XmlAttribute
	private LocalDate start;

	@XmlAttribute
	private LocalDate end;
	
	@XmlAttribute
	private Duration targetTime = Duration.ZERO;

	public HolydayType getType() {
		return type;
	}

	public void setType(HolydayType type) {
		this.type = type;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Duration getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Duration targetTime) {
		this.targetTime = targetTime;
	}

}
