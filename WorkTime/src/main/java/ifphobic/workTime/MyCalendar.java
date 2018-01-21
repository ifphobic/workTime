package ifphobic.workTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

	private static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
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
	
	public static int getNumberOfDays(int year, int month) {
		return DAYS[month - 1];
	}

	
	public static boolean isWeekend(int year, int month, int day) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month - 1, day);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		boolean result = weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY;
		return result;
	}
	
}
