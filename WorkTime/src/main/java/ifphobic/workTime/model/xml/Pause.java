package ifphobic.workTime.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType (propOrder={"start","end"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pause {

	@XmlAttribute
	private Time start;
	
	@XmlAttribute
	private Time end;
	
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}
}
