package ifphobic.workTime.model.xml;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class XmlAdapterText {

	
	@Test
	public void testDateMarshal() throws Exception {
		LocalDateXmlAdapter adapter = new LocalDateXmlAdapter();
		String result = adapter.marshal(LocalDate.of(2018, 2, 1));
		Assert.assertEquals("01.02.2018", result);
	}
	
	@Test
	public void testDateUnmarshal() throws Exception {
		LocalDateXmlAdapter adapter = new LocalDateXmlAdapter();
		LocalDate result = adapter.unmarshal("01.02.2018");
		LocalDate expected = LocalDate.of(2018, 2, 1);
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void testTimeMarshal() throws Exception {
		LocalTimeXmlAdapter adapter = new LocalTimeXmlAdapter();
		String result = adapter.marshal(LocalTime.of(14, 45, 15));
		Assert.assertEquals("14:45:15", result);
	}

	@Test
	public void testTimeUnmarshal() throws Exception {
		LocalTimeXmlAdapter adapter = new LocalTimeXmlAdapter();
		LocalTime result = adapter.unmarshal("14:45:15");
		LocalTime expected = LocalTime.of(14, 45, 15);
		Assert.assertEquals(expected, result);
	}

}
