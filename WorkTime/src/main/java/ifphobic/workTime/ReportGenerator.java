package ifphobic.workTime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import ifphobic.workTime.model.DayType;
import ifphobic.workTime.model.ReportDay;
import ifphobic.workTime.model.ReportMonth;
import ifphobic.workTime.model.xml.Day;
import ifphobic.workTime.model.xml.HolidayDay;
import ifphobic.workTime.model.xml.Holidays;
import ifphobic.workTime.model.xml.Month;

public class ReportGenerator {
	
	public Duration calculateTotalDelta(List<ReportMonth> report) {
		Duration delta = Duration.ZERO;
		for (ReportMonth reportMonth : report) {
			delta = delta.plus(reportMonth.getDelta());
		}
		return delta;
	}
	
	public List<ReportMonth> generate() {
		
		TreeMap<YearMonth, Month> workMonth = Month.readMonths();
		YearMonth firstKey = workMonth.firstKey();
		YearMonth lastKey = workMonth.lastKey();
		
		Map<LocalDate, Duration> workTimes = createWorkTimeMap(workMonth);
		List<ReportMonth> result = createReportMonths(firstKey, lastKey, workTimes);
		return result;
	}

	private Map<LocalDate, Duration> createWorkTimeMap(TreeMap<YearMonth, Month> workMonth) {
		Map<LocalDate, Duration> result = new HashMap<>();
		for (Entry<YearMonth, Month> next : workMonth.entrySet()) {
			for (Day day: next.getValue().getDays()) {
				YearMonth nextKey = next.getKey();
				LocalDate key = LocalDate.of(nextKey.getYear(), nextKey.getMonth(), day.getDay());
				result.put(key, day.getWorkTime());
			}		
		}
		return result;
	}

	private List<ReportMonth> createReportMonths(YearMonth firsttMonth, YearMonth lastMonth, Map<LocalDate, Duration> workTimes) {
		List<ReportMonth> result = new ArrayList<>();
		Holidays holidays = Holidays.read();
		
		int firstYear = firsttMonth.getYear();
		int lastYear = lastMonth.getYear();
		for (int year = firstYear; year <= lastYear; year++) {
			int minMonth = (year == firstYear) ?  firsttMonth.getMonthValue(): 1;
			int maxMonth = (year == lastYear) ? lastMonth.getMonthValue() : 12;
			for (int month = minMonth; month <= maxMonth; month++) {
				ReportMonth reportMonth = new ReportMonth();
				result.add(reportMonth);
				reportMonth.setName(month + " - " + year);
				int numberOfDays = MyCalendar.getNumberOfDays(year, month);
				for(int day = 1; day <= numberOfDays; day++) {
					ReportDay reportDay = createReportDay(year, month, day, workTimes, holidays);
					reportMonth.addDelta(reportDay.getDelta());
					reportMonth.getReportDays().add(reportDay);
				}
			}
		}
		return result;
	}

	private ReportDay createReportDay(int year, int month, int day, Map<LocalDate, Duration> workTimes,Holidays holidays) {
		LocalDate date = LocalDate.of(year, month, day);
		HolidayDay holiday = holidays.getHolidayDay(date);
		
		Duration targetTime = Property.TARGET_TIME.getDuration();
		DayType dayType = DayType.WORK_DAY;
		if (MyCalendar.isWeekend(year, month, day)) {
			targetTime = Duration.ZERO;
			dayType = DayType.WEEK_END;
		} else if (holiday != null) {
			targetTime = holiday.getTargetTime();
			dayType = holiday.getType().getDayType();
		}
		if (date.isAfter(LocalDate.now())) {
			targetTime = Duration.ZERO;
		}
		ReportDay reportDay = new ReportDay();
		reportDay.setDayType(dayType);
		reportDay.setTargetTime(targetTime);
		Duration workTime = lookupWorkTime(date, workTimes);
		reportDay.setWorkTime(workTime);
		return reportDay;
	}
	
	private Duration lookupWorkTime(LocalDate date, Map<LocalDate, Duration> workTimes) {
		
		Duration workTime = workTimes.get(date);
		if (workTime == null) {
			return Duration.ZERO;
		}
		return workTime;
	}

}
