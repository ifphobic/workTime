package ifphobic.workTime;

import java.time.LocalTime;

import ifphobic.workTime.model.xml.Day;
import ifphobic.workTime.model.xml.Month;
import ifphobic.workTime.model.xml.Pause;

public class TimeFileHandler extends Thread {

	private final Month month = Month.readCurrent();
	private final Day currentDay = month.getCurrentDay();
	private Pause currentPause = null;

	public TimeFileHandler() {
		
		if (currentDay.getStart() == null) {
			currentDay.setStart(LocalTime.now());
		}

		currentDay.setEnd(LocalTime.now());
		month.write();
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			synchronized (this) {
				LocalTime now = LocalTime.now();
				currentDay.setEnd(now);
				if (currentPause != null) {
					currentPause.setEnd(now);
				}
				month.write();
			}
		}
	}

	public void togglePause() {

		synchronized (this) {
			
			LocalTime now = LocalTime.now();
			if (!inPause()) {
				currentPause = new Pause();
				currentPause.setStart(now);
				currentPause.setEnd(now);
				currentDay.getPauses().add(currentPause);
			} else {
				currentPause.setEnd(now);
				currentPause = null;
			}
			currentDay.setEnd(now);
			month.write();
		}
	}

	public boolean inPause() {
		return currentPause != null;
	}

}
