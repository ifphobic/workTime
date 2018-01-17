package ifphobic.workTime.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlType (propOrder={"day","start", "end", "pauses"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Day {

	@XmlAttribute
	private int day;

	@XmlAttribute
	private Time start;

	@XmlAttribute
	private Time end;
	
	@XmlElement(name="pause")
	private List<Pause> pauses = new ArrayList<>();

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

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

	public List<Pause> getPauses() {
		return pauses;
	}

	public void setPauses(List<Pause> pauses) {
		this.pauses = pauses;
	}

	public double getWortTime() {
			double workTime = end.minus(start);
			for (Pause pause : pauses) {
				workTime -= pause.getEnd().minus(pause.getStart());
			}

		
		return workTime;
	}
	 
	
	
}
