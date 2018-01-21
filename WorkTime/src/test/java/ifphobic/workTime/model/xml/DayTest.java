package ifphobic.workTime.model.xml;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;


public class DayTest {

	
	@Test
	public void testWorkTime() {
		Day day = new Day();
		day.setStart(LocalTime.of(7, 2, 1));
		day.setEnd(LocalTime.of(16, 14, 15));
		// 09:12:14
		Pause pause = new Pause();
		pause.setStart(LocalTime.of(12, 5, 24));
		pause.setEnd(LocalTime.of(13, 10, 34));
		day.getPauses().add(pause);
		// - 01:05:10
		// = 08:07:04
		Duration expected = Duration.ofHours(8).plusMinutes(7).plusSeconds(4);
		Duration result = day.getWorkTime();
		Assert.assertEquals(expected, result);
	}
}
