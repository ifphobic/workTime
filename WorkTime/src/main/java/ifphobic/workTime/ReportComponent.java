package ifphobic.workTime;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import ifphobic.workTime.model.ReportDay;
import ifphobic.workTime.model.ReportMonth;

public class ReportComponent extends JComponent {

	private final List<ReportMonth> report;

	public ReportComponent(List<ReportMonth> report) {
		this.report = report;
	}

	@Override
	public void paint(Graphics g) {
		setOpaque(true);
		for (int x = 0; x < report.size(); x++) {
			ReportMonth month = report.get(x);
			for (int y = 0; y < month.getReportDays().size(); y++) {
				paint(x, y, month.getReportDays().get(y), g);
			}
		}

	}

	public void paint(int xOffset, int yOffset, ReportDay day, Graphics g) {
		int width = Property.REPORT_DAY_WIDTH.getInt();
		int height = Property.REPORT_DAY_HEIGHT.getInt();

		double standardTargetTimeWidth = width * Property.TARGET_TIME_RATIO.getDouble();
		double targetTimeWidth = standardTargetTimeWidth * DateHelper.asDouble(day.getTargetTime()) / Property.STANDARD_TARGET_TIME.getDouble();
		double workTimeWidth = standardTargetTimeWidth * DateHelper.asMinDouble(day.getWorkTime(), day.getTargetTime())
				/ Property.STANDARD_TARGET_TIME.getDouble();
		double overTimeWidth = standardTargetTimeWidth * DateHelper.asDouble(day.getWorkTime()) / Property.STANDARD_TARGET_TIME.getDouble();

		int y = yOffset * height;
		int x = xOffset * (width + 5) + 5;

		g.setColor(day.getDayType().getBackground());
		g.fillRect(x, y, round(standardTargetTimeWidth), height);

		g.setColor(Property.TARGET_TIME_COLOR.getColor());
		g.fillRect(x, y, round(targetTimeWidth), height);

		g.setColor(Property.OVER_TIME_COLOR.getColor());
		g.fillRect(x, y, round(overTimeWidth), height);

		g.setColor(Property.WORK_TIME_COLOR.getColor());
		g.fillRect(x, y, round(workTimeWidth), height);

		g.setColor(Property.LINE_COLOR.getColor());
		if (day.isPrint()) {
			g.drawString(DateHelper.printableDuration(day.getDelta()), x + 5, y + height - Property.REPORT_TEXT_OFFSET.getInt());
		}
		g.drawLine(x, y, x + width, y);
		g.drawLine(x, y + height, x + width, y + height);

	}

	
	private int round(double value) {
		return (int) (value + 0.5);
	}
}
