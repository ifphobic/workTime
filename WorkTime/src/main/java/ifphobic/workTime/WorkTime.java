package ifphobic.workTime;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;


public class WorkTime {

	private final TimeFileHandler timeFileHandler = new TimeFileHandler();
	private JToggleButton pauseButton;
	
	private WorkTime() {
		
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		
		frame.setBackground(new Color(0.0f,0.0f,0.0f,0.3f));
		frame.getContentPane().setLayout(new FlowLayout());
		
		pauseButton = new JToggleButton("Pause");
		pauseButton.setForeground(getToggleColor());
		pauseButton.setSelected(timeFileHandler.inPause());
		pauseButton.addActionListener(e -> togglePause());
		frame.getContentPane().add(pauseButton);
		
		JButton endButton = new JButton("Close");
		endButton.addActionListener(e -> close());
		frame.getContentPane().add(endButton);
		frame.pack();
		
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int height = device.getDisplayMode().getHeight();
		frame.setLocation(0, height - frame.getHeight());
		frame.setVisible(true);	
		
		new Report();
	}

	private Color getToggleColor() {
		return timeFileHandler.inPause() ? Color.GREEN : Color.RED;
	}

	private void togglePause() {
		timeFileHandler.togglePause();
		pauseButton.setForeground(getToggleColor());
	}
	
	private void close() {
		timeFileHandler.end();
		System.exit(0);
	}

	public static void main(String[] args) {
		new WorkTime();
	}
}
