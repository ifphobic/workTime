package ifphobic.workTime;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateHelper {

	public static List<LocalDate> retriveDateBetween(LocalDate start, LocalDate end) {
		List<LocalDate> result = new ArrayList<>();

		if (end.isBefore(start)) {
			throw new RuntimeException("End is before start: " + start + " - " + end);
		}

		LocalDate date = start;
		while (!date.isAfter(end)) {
			result.add(date);
			date = date.plusDays(1);
		}
		return result;
	}

	public static double asDouble(Duration duration) {
		double workTimeDouble = duration.toMillis() / 1000.0;
		workTimeDouble /= 3600;
		return workTimeDouble;
	}

	public static double asMinDouble(Duration value0, Duration value1) {
		return Math.min(asDouble(value0), asDouble(value1));
	}

	public static String printableDuration(Duration duration) {
		if (duration == null) {
			return "";
		}
		
		
		long seconds = duration.toMillis() / 1000;
		String result = (seconds < 0) ? "-" : "";
		seconds = Math.abs(seconds);
		long minutes = seconds / 60;
		seconds = seconds % 60;
		long hours = minutes / 60;
		minutes = minutes % 60;
		long days = hours / 24;
		hours = hours % 24;
		if (days > 0) {
			result += addZero(days) + ":";
		}
		result += addZero(hours) + ":";
		result += addZero(minutes);
		return result;
	}
	
	public static String addZero(long input) {
		String result = "";
		if (input < 10) {
			result += "0";
		}
		result += input;
		return result;
	}


}
