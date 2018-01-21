package ifphobic.workTime.model.xml;

import ifphobic.workTime.model.DayType;

public enum HolydayType {
	PUBLIC_HOLIDAY(DayType.PUBLIC_HOLIDAY), HOLIDAY(DayType.HOLIDAY);
	
	private final DayType dayType;
	
	

	private HolydayType(DayType dayType) {
		this.dayType = dayType;
	}



	public DayType getDayType() {
		return dayType;
	}
	
	
	
}
