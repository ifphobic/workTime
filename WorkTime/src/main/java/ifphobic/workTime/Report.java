package ifphobic.workTime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ifphobic.workTime.model.DayType;
import ifphobic.workTime.model.ReportDay;
import ifphobic.workTime.model.ReportMonth;
import ifphobic.workTime.model.xml.Day;
import ifphobic.workTime.model.xml.Month;

public class Report {

	
	private JFrame frame;

	public Report() {
		
		List<ReportMonth> report = generate();
		
		frame = new JFrame();
		frame.setUndecorated(true);
		
		frame.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		frame.getContentPane().setLayout(new FlowLayout());
		
		Dimension size = new Dimension(900, Property.REPORT_DAY_HEIGHT.getInt() * 33); 
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = device.getDisplayMode().getHeight();
		int width = device.getDisplayMode().getWidth();
		frame.setLocation((width - size.width) / 2, (height - size.height) / 2);
		ReportComponent reportComponent = new ReportComponent(report);
		reportComponent.setMinimumSize(size);
		reportComponent.setPreferredSize(size);
		
		double delta = 0.0;
		for (ReportMonth reportMonth : report) {
			delta += reportMonth.getDelta();
		}
		String printableTime = MyCalendar.printableTime(delta);
		JLabel label = new JLabel(printableTime);
		if (printableTime.startsWith("-")) {
			label.setForeground(Color.RED);
		} else {
			label.setForeground(Color.GREEN);
			
		}
		
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(reportComponent);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);	
		
		
	}
	
	private List<ReportMonth> generate() {
		
		TreeMap<Integer, TreeMap<Integer, Month>> workMonth = Month.readMonths();
		Entry<Integer, TreeMap<Integer, Month>> firstEntry = workMonth.firstEntry();
		int startYear = firstEntry.getKey();
		int startMonth = firstEntry.getValue().firstKey();
		Entry<Integer, TreeMap<Integer, Month>> lastEntry = workMonth.lastEntry();
		int lastYear = lastEntry.getKey();
		int lastMonth = lastEntry.getValue().lastKey();
		
		Map<Integer, Map<Integer, Map<Integer, Double>>> workTimes = createWorkTimeMap(workMonth);
		List<ReportMonth> result = createReportMonths(startYear, startMonth, lastYear, lastMonth, workTimes);
		return result;
	}

	public void close() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	private Map<Integer, Map<Integer, Map<Integer, Double>>> createWorkTimeMap(TreeMap<Integer, TreeMap<Integer, Month>> workMonth) {
		Map<Integer, Map<Integer, Map<Integer, Double>>> result = new HashMap<>();
		for (Entry<Integer, TreeMap<Integer, Month>> yearEntry : workMonth.entrySet()) {
			HashMap<Integer, Map<Integer, Double>> yearMap = new HashMap<>();
			result.put(yearEntry.getKey(), yearMap);
			for (Entry<Integer,  Month> monthEntry : yearEntry.getValue().entrySet()) {
				HashMap<Integer, Double> monthMap = new HashMap<>();
				yearMap.put(monthEntry.getKey(), monthMap);
				for (Day day: monthEntry.getValue().getDays()) {
					monthMap.put(day.getDay(), day.getWortTime());
				}		
			}
		}
		return result;
	}

	private List<ReportMonth> createReportMonths(int startYear, int startMonth, int lastYear, int lastMonth, Map<Integer, Map<Integer, Map<Integer, Double>>> workTimes) {
		List<ReportMonth> result = new ArrayList<>();
		
		for (int year = startYear; year <= lastYear; year++) {
			int minMonth = (year == startYear) ? startMonth : 1;
			int maxMonth = (year == lastYear) ? lastMonth : 12;
			for (int month = minMonth; month <= maxMonth; month++) {
				ReportMonth reportMonth = new ReportMonth();
				result.add(reportMonth);
				reportMonth.setName(month + " - " + year);
				int numberOfDays = MyCalendar.getNumberOfDays(year, month);
				for(int day = 1; day <= numberOfDays; day++) {
					double targetTime = Property.TARGET_TIME.getDouble();
					DayType dayType = DayType.WORK_DAY;
					if (MyCalendar.isWeekend(year, month, day)) {
						targetTime = 0;
						dayType = DayType.WEEK_END;
					}
					if (year == lastYear && month == lastMonth && day > MyCalendar.getDay()) {
						targetTime = 0;
					}
					ReportDay reportDay = new ReportDay();
					reportDay.setDayType(dayType);
					reportDay.setTargetTime(targetTime);
					double workTime = lookupWorkTime(year, month, day, workTimes);
					reportDay.setWorkTime(workTime);
					reportMonth.addDelta(workTime - targetTime);
					reportMonth.getReportDays().add(reportDay);
				}
			}
		}
		return result;
	}
	
	private double lookupWorkTime(int year, int month, int day, Map<Integer, Map<Integer, Map<Integer, Double>>> workTimes) {
		Map<Integer, Map<Integer, Double>> yearMap = workTimes.get(year);
		if (yearMap == null) {
			return 0.0;
		}
		Map<Integer, Double> monthMap = yearMap.get(month);
		if (monthMap == null) {
			return 0.0;
		}
		Double workTime = monthMap.get(day);
		if (workTime == null) {
			return 0.0;
		}
		return workTime;
	}
}
