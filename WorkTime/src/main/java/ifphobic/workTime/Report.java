package ifphobic.workTime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
		
		ReportGenerator generator = new ReportGenerator();
		List<ReportMonth> report = generator.generate();
		
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
		
		Duration delta = generator.calculateTotalDelta(report);
		JLabel label = new JLabel(DateHelper.printableDuration(delta));
		if (delta.isNegative()) {
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
	
	public void close() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

}
