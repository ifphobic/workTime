package ifphobic.workTime.model.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeXmlAdapter extends XmlAdapter<String, Time> {

	@Override
	public Time unmarshal(String value) throws Exception {

		String[] tokens = value.split(":");

		Time result = new Time();
		result.setHour(Integer.parseInt(tokens[0]));
		result.setMinute(Integer.parseInt(tokens[1]));
		result.setSecond(Integer.parseInt(tokens[2]));
		return result;
	}

	@Override
	public String marshal(Time value) throws Exception {
		if (value == null) {
			return null;
		}
		String result = addZero(value.getHour()) + ":" + addZero(value.getMinute()) + ":" + addZero(value.getSecond());
		return result;
	}

	private String addZero(int value) {

		String result = "";
		if (value < 10) {
			result = "0";
		}
		result += value;
		return result;
	}

}
