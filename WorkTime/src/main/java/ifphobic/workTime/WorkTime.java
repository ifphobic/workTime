package ifphobic.workTime;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;


public class WorkTime {

	private final TimeFileHandler timeFileHandler = new TimeFileHandler();
	private JToggleButton pauseButton;
	private JButton reportButton;
	private Report report;
	
	private WorkTime() {
		
		URL url = WorkTime.class.getClassLoader().getResource("Clock.png");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image image = kit.createImage(url);
        
		//com.apple.eawt.Application.getApplication().setDockIconImage(image);
		
		JFrame frame = new JFrame();
		frame.setIconImage(image);
		frame.setUndecorated(true);
		
		frame.setBackground(new Color(0.0f,0.0f,0.0f,0.3f));
		frame.getContentPane().setLayout(new FlowLayout());
		
		pauseButton = new JToggleButton("Pause");
		pauseButton.setForeground(getToggleColor());
		pauseButton.addActionListener(e -> togglePause());
		frame.getContentPane().add(pauseButton);
		
		reportButton = new JButton("|||");
		reportButton.setForeground(Color.GREEN);
		reportButton.addActionListener(e -> toggleReport());
		frame.getContentPane().add(reportButton);
		frame.pack();
		
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = device.getDisplayMode().getHeight();
		frame.setLocation(0, height - frame.getHeight());
		frame.setVisible(true);	
		
		
	}

	private Color getToggleColor() {
		return timeFileHandler.inPause() ? Color.RED : Color.GREEN;
	}
	
	private void toggleReport() {
		if (report == null) {
			report = new Report();
			reportButton.setForeground(Color.RED);
		} else {
			report.close();
			report = null;
			reportButton.setForeground(Color.GREEN);
		}
	}

	private void togglePause() {
		timeFileHandler.togglePause();
		pauseButton.setForeground(getToggleColor());
	}

	public static void main(String[] args) {
		new WorkTime();
	}
}
