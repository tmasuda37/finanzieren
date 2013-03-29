package info.toshim.finanzieren.mvc.core;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class GetDatesForSql
{
	public static final String HM_KEY_START_DATE = "HmKeyStartDate";

	public static final String HM_KEY_END_DATE = "HmKeyEndDate";

	public static final String HM_KEY_DAYS_BETWEEN_START_END = "HmKeyDaysBetweenStartEnd";

	public HashMap<String, Date> getFirstLastDateOfMonth()
	{
		HashMap<String, Date> map = new HashMap<String, Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		map.put(HM_KEY_START_DATE, cal.getTime());
		cal.add(Calendar.MONTH, +1);
		map.put(HM_KEY_END_DATE, cal.getTime());
		return map;
	}

	public HashMap<String, Date> getFirstLastDateOfMonth(Date date)
	{
		HashMap<String, Date> map = new HashMap<String, Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		map.put(HM_KEY_START_DATE, cal.getTime());
		cal.add(Calendar.MONTH, +1);
		map.put(HM_KEY_END_DATE, cal.getTime());
		return map;
	}
}
