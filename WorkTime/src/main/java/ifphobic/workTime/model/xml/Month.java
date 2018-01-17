package ifphobic.workTime.model.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ifphobic.workTime.MyCalendar;
import ifphobic.workTime.Property;

@XmlRootElement
@XmlType (propOrder={"year","month", "days"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Month {

	private static final Pattern filePatter = Pattern.compile("[0-9]{4}-[0-9]{1,2}\\.xml");
	
	@XmlAttribute
	private int year;

	@XmlAttribute
	private int month;
	
	@XmlElement(name="day")
	private List<Day> days = new ArrayList<>();

	private Month() {}
	
	
	public Day getCurrentDay() {
		int currentDay = MyCalendar.getDay();
		for (Day day : days) {
			if (day.getDay() == currentDay) {
				return day;
			}
		}
		Day day = new Day();
		day.setDay(currentDay);
		days.add(day);
		return day;
	}
	
	
	public void write() {
		File file = createFile(year, month);
		
		try {
			JAXBContext context = JAXBContext.newInstance(Month.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public static Month readCurrent() {
		File file = createFile(MyCalendar.getYear(), MyCalendar.getMonth());
		if (!file.exists()) {
			Month result = new Month();
			result.initialize();
			return result;
		}
		return read(file);
	}
	
	public static TreeMap<Integer, TreeMap<Integer, Month>> readMonths() {
		TreeMap<Integer, TreeMap<Integer, Month>> result = new TreeMap<>();
		 
		 File folder = Property.MONTH_FILE_FOLDER.getFile();
		 File[] files = folder.listFiles(file -> filter(file));
		for (File file : files) {
			Month month = read(file);
			TreeMap<Integer, Month> map = result.get(month.getYear());
			if (map == null) {
				map = new TreeMap<>();
				result.put(month.getYear(), map);
			}
			map.put(month.getMonth(), month);
			
		}	
		return result;
	}

	private static boolean filter(File file) {
		return file.isFile() && filePatter.matcher(file.getName()).matches();
	}
	
	
	private static Month read(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(Month.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Month month = (Month) unmarshaller.unmarshal(file);
			return month;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}		
	}

	private void initialize() {
		year = MyCalendar.getYear();
		month = MyCalendar.getMonth();
	}
	
	private static File createFile(int year, int month) {
		String filename = year +"-" + month + ".xml";
		File folder = Property.MONTH_FILE_FOLDER.getFile();
		File file = new File(folder, filename);
		return file;
	}
	
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public List<Day> getDays() {
		return days;
	}
	
	public void setDays(List<Day> days) {
		this.days = days;
	}
}
