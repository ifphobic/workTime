package ifphobic.workTime.model;

import java.awt.Color;

public enum DayType {

	WORK_DAY(Color.WHITE),
	WEEK_END(Color.GRAY),
	HOLIDAY(Color.LIGHT_GRAY),
	PUBLIC_HOLIDAY(Color.DARK_GRAY),
	;
	
	
	private final Color background;
	
	private DayType(Color background) {
		this.background = background;
	}


	public Color getBackground() {
		return background;
	}

	
	
}
