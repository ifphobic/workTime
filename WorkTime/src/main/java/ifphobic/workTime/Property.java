package ifphobic.workTime;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public enum Property {

	MONTH_FILE_FOLDER,
	REPORT_DAY_HEIGHT,
	REPORT_DAY_WIDTH,
	REPORT_TEXT_OFFSET,
	
	LINE_COLOR,
	TARGET_TIME_COLOR,
	WORK_TIME_COLOR,
	OVER_TIME_COLOR,
	
	TARGET_TIME_RATIO,
	STANDARD_TARGET_TIME,
	
	;

	private static final Properties PROPERTIES = new Properties();

	private String defaultValue;
	private String key;

	static {
		try {
			InputStream stream = ClassLoader.getSystemResourceAsStream(WorkTime.class.getSimpleName() + ".default");
			PROPERTIES.load(stream);
			stream.close();
			stream = ClassLoader.getSystemResourceAsStream(WorkTime.class.getSimpleName() + ".properties");
			if (stream != null) {
				PROPERTIES.load(stream);
				stream.close();
			}
			PROPERTIES.putAll(System.getProperties());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Property() {
		key = name().replaceAll("_", ".").toLowerCase();
	}

	private Property(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String get() {
		return PROPERTIES.getProperty(toString(), defaultValue);
	}

	public int getInt() {
		return Integer.parseInt(get());
	}

	public double getDouble() {
		return Double.parseDouble(get());
	}
	

	public File getFile() {
		return new File(get());
	}
	
	public Color getColor() {
		long color = Long.decode(get());
		return new Color((int)color, false);
	}
	
	public boolean isSet() {
		String value = get();
		return value != null && !value.isEmpty();
	}

	@Override
	public String toString() {
		return key;
	}

}
