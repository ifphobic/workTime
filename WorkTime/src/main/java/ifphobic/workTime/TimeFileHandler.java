package ifphobic.workTime;

import java.util.List;

import ifphobic.workTime.model.xml.Day;
import ifphobic.workTime.model.xml.Month;
import ifphobic.workTime.model.xml.Pause;
import ifphobic.workTime.model.xml.Time;

public class TimeFileHandler {

	private final Month month = Month.readCurrent();
	private final Day currentDay = month.getCurrentDay();

	public TimeFileHandler() {
		if (currentDay.getStart() == null) {
			currentDay.setStart(Time.now());
			month.write();
		}

		if (currentDay.getEnd() != null) {
			currentDay.setEnd(null);
			month.write();
		}
	}

	public void end() {
		currentDay.setEnd(Time.now());
		month.write();
	}

	public void togglePause() {

		List<Pause> pauses = currentDay.getPauses();
		if (!inPause()) {
			Pause pause = new Pause();
			pause.setStart(Time.now());
			pauses.add(pause);
		} else {
			Pause pause = pauses.get(pauses.size() - 1);
			pause.setEnd(Time.now());
		}
		month.write();

	}

	public boolean inPause() {
		List<Pause> pauses = currentDay.getPauses();
		if (pauses.isEmpty()) {
			return false;
		}
		Pause pause = pauses.get(pauses.size() - 1);
		boolean result = pause.getEnd() == null;
		return result;
	}

}
