package ifphobic.workTime.model.xml;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ifphobic.workTime.DateHelper;
import ifphobic.workTime.Property;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Holidays {

	@XmlTransient
	private Map<LocalDate, HolidayDay> holidayMap = new HashMap<>();
	
	@XmlElement(name="holiday")
	private List<Holiday> holidays = new ArrayList<>();

	private Holidays(){}
	
	private void fillHolidayMap() {
		for (Holiday holiday : holidays) {
			List<LocalDate> dateList = DateHelper.retriveDateBetween(holiday.getStart(), holiday.getEnd());
			for (LocalDate date : dateList) {
				HolidayDay existing = holidayMap.get(date);
				if (checkPut(existing, date, holiday.getType())) {
					HolidayDay holidayDay = new HolidayDay();
					holidayDay.setDate(date);
					holidayDay.setTargetTime(holiday.getTargetTime());
					holidayDay.setType(holiday.getType());
					holidayMap.put(date, holidayDay);
				}
			}
		}
	}
	
	private boolean checkPut(HolidayDay existing, LocalDate date, HolydayType type) {
		if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)  || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			return false;
		}
		if (existing == null) {
			return true;
		}

		boolean result = existing.getType() == HolydayType.HOLIDAY && type == HolydayType.PUBLIC_HOLIDAY;
		return result;
	}

	public static Holidays read() {
		try {
			File file = createFile();
			JAXBContext context = JAXBContext.newInstance(Holidays.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Holidays result = (Holidays) unmarshaller.unmarshal(file);
			result.fillHolidayMap();
			return result;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void write() {
		try {
			File file = createFile();
			JAXBContext context = JAXBContext.newInstance(Holidays.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	private static File createFile() {
		File file = new File(Property.BASE_FOLDER.getFile(), Property.HOLIDAY_FILE.get());
		return file;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}
	
	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}
	
	public HolidayDay getHolidayDay(LocalDate date) {
		return holidayMap.get(date);
	}
}

