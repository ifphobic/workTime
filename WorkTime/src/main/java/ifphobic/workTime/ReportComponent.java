package ifphobic.workTime;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import ifphobic.workTime.model.DayType;
import ifphobic.workTime.model.ReportDay;

public class ReportComponent extends JComponent {

	public ReportComponent() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) {
		setOpaque(true);

		List<ReportDay> days = new ArrayList<>();
		days.add(new ReportDay(0, 0, DayType.WORK_DAY));
		days.add(new ReportDay(7.5, 8.0, DayType.WORK_DAY));
		days.add(new ReportDay(8.0, 8.0, DayType.WORK_DAY));
		days.add(new ReportDay(8.5, 8.0, DayType.WORK_DAY));
		
		days.add(new ReportDay(3.5, 4.0, DayType.HOLIDAY));
		days.add(new ReportDay(4.0, 4.0, DayType.HOLIDAY));
		days.add(new ReportDay(4.5, 4.0, DayType.HOLIDAY));		
		
		days.add(new ReportDay(3.5, 4.0, DayType.PUBLIC_HOLIDAY));
		days.add(new ReportDay(4.0, 4.0, DayType.PUBLIC_HOLIDAY));
		days.add(new ReportDay(4.5, 4.0, DayType.PUBLIC_HOLIDAY));		
		
		
		days.add(new ReportDay(0, 0, DayType.PUBLIC_HOLIDAY));
		days.add(new ReportDay(0, 0, DayType.HOLIDAY));
		days.add(new ReportDay(0, 0, DayType.WEEK_END));

		
		
		for (int y = 0; y < days.size(); y++) {
			paint(100, y, days.get(y), g);
		}
	
	}


	
	
	public void paint(int x, int yOffset, ReportDay day, Graphics g) {
		int width = Property.REPORT_DAY_WIDTH.getInt();
		int height = Property.REPORT_DAY_HEIGHT.getInt();
		
		double standardTargetTimeWidth = width * Property.TARGET_TIME_RATIO.getDouble();
		double targetTimeWidth = standardTargetTimeWidth * day.getTargetTime() / Property.STANDARD_TARGET_TIME.getDouble();	
		double workTimeWidth = standardTargetTimeWidth * Math.min(day.getWorkTime(), day.getTargetTime()) / Property.STANDARD_TARGET_TIME.getDouble();
		double overTimeWidth = standardTargetTimeWidth * day.getWorkTime() / Property.STANDARD_TARGET_TIME.getDouble();
		
		int y = yOffset * height;
		
		g.setColor(day.getDayType().getBackground());
		g.fillRect(x, y, round(standardTargetTimeWidth), height);
		
		
		g.setColor(Property.TARGET_TIME_COLOR.getColor());
		g.fillRect(x, y, round(targetTimeWidth), height);
		
		g.setColor(Property.OVER_TIME_COLOR.getColor());
		g.fillRect(x, y, round(overTimeWidth), height);

		g.setColor(Property.WORK_TIME_COLOR.getColor());
		g.fillRect(x, y, round(workTimeWidth), height);
		
		g.setColor(Property.LINE_COLOR.getColor());
		g.drawString(day.getDelta(), x + 5, y + height - Property.REPORT_TEXT_OFFSET.getInt());
		g.drawLine(x, y, x + width, y);
		g.drawLine(x, y + height, x + width, y + height);
		
	}
	
	private int round(double value) {
		return (int)(value + 0.5);
	}
}
