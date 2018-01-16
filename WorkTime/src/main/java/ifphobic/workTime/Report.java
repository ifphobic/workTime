package ifphobic.workTime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import ifphobic.workTime.model.xml.Month;

public class Report {

	
	public Report() {
		
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		
		frame.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		frame.getContentPane().setLayout(new FlowLayout());
		
		Dimension size = new Dimension(900, Property.REPORT_DAY_HEIGHT.getInt() * 33); 
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = device.getDisplayMode().getHeight();
		int width = device.getDisplayMode().getWidth();
		frame.setLocation((width - size.width) / 2, (height - size.height) / 2);
		ReportComponent reportComponent = new ReportComponent();
		reportComponent.setMinimumSize(size);
		reportComponent.setPreferredSize(size);
		
		frame.getContentPane().add(reportComponent);
		frame.pack();
		frame.setVisible(true);	
		
		Month.readMonths();
	}
}
