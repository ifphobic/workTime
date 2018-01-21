package ifphobic.workTime.model.xml;

import java.time.Duration;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DurationXmlAdapter extends XmlAdapter<String, Duration> {

	@Override
	public Duration unmarshal(String value) throws Exception {
		double doubleValue = Double.parseDouble(value) * 3600 + 0.5;
		return Duration.ofSeconds((long) doubleValue);
	}

	@Override
	public String marshal(Duration value) throws Exception {
		double result = value.getSeconds() / 3600.0;
		return Double.toString(result);
	}


}
