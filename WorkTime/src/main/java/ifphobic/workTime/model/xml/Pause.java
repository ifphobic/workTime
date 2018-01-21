package ifphobic.workTime.model.xml;

import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType (propOrder={"start","end"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pause {

	@XmlAttribute
	private LocalTime start;
	
	@XmlAttribute
	private LocalTime end;
	
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
}
