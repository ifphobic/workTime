package ifphobic.workTime.model.xml;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ifphobic.workTime.MyCalendar;

public class Time {

	private int hour;
	private int minute;
	private int second;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
	public static Time now() {
		Time result = new Time();
		result.hour = MyCalendar.getHour();
		result.minute = MyCalendar.getMinute();
		result.second = MyCalendar.getSecond();
		return result;
	}
	
}
