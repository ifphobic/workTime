package ifphobic.workTime.model.xml;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMANY);
	
	@Override
	public LocalDate unmarshal(String value) throws Exception {
		return LocalDate.parse(value, dateFormat);
	}

	@Override
	public String marshal(LocalDate value) throws Exception {
		return value.format(dateFormat);
	}


}
