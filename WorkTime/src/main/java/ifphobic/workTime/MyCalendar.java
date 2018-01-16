package ifphobic.workTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

	private long lastUpdate;
	private Calendar calendar;
	
	private static MyCalendar instance = new MyCalendar();

	private MyCalendar() {
		update();
	}
	
	private void update(){
		lastUpdate = System.currentTimeMillis();
		calendar = GregorianCalendar.getInstance();
	
	}
	
	
	private Calendar getCalendar() {
		if (System.currentTimeMillis() - lastUpdate > 1000) {
			update();
		}
		return calendar;
	}
	
	public static int getYear() {
		return instance.getCalendar().get(Calendar.YEAR);
	}
	
	public static int getMonth() {
		return instance.getCalendar().get(Calendar.MONTH) + 1;
	}
	
	public static int getDay() {
		return instance.getCalendar().get(Calendar.DATE);
	}
	
	public static int getHour() {
		return instance.getCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getMinute() {
		return instance.getCalendar().get(Calendar.MINUTE);
	}
	
	public static int getSecond() {
		return instance.getCalendar().get(Calendar.SECOND);
	}
	
}
