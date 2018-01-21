package ifphobic.workTime.model.xml;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeXmlAdapter extends XmlAdapter<String, LocalTime> {

	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.GERMANY);
	
	@Override
	public LocalTime unmarshal(String value) throws Exception {

		return LocalTime.parse(value, dateFormat);
	}

	@Override
	public String marshal(LocalTime value) throws Exception {
		if (value == null) {
			return null;
		}
		return value.format(dateFormat);
	}


}
