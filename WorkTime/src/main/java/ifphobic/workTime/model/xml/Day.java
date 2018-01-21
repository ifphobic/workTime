package ifphobic.workTime.model.xml;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "day", "start", "end", "pauses" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Day {

	@XmlAttribute
	private int day;

	@XmlAttribute
	private LocalTime start;

	@XmlAttribute
	private LocalTime end;

	@XmlElement(name = "pause")
	private List<Pause> pauses = new ArrayList<>();

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public List<Pause> getPauses() {
		return pauses;
	}

	public void setPauses(List<Pause> pauses) {
		this.pauses = pauses;
	}

	public Duration getWorkTime() {
		Duration workTime = Duration.between(start, end);
		for (Pause pause : pauses) {
			workTime = workTime.minus(Duration.between(pause.getStart(), pause.getEnd()));
		}
		return workTime;
	}

}
