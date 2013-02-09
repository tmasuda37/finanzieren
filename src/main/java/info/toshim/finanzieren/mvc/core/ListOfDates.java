package info.toshim.finanzieren.mvc.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListOfDates
{
	public static final String YEAR_MODE = "year";

	public static final String MONTH_MODE = "month";

	public static final String DAY_MODE = "day";

	public List<String> getListOfDates(int numOfList, String mode)
	{
		List<String> listOfDates = new ArrayList<String>(numOfList);
		int calField = 0;
		Calendar calendar = Calendar.getInstance();
		if (YEAR_MODE.equals(mode))
		{
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calField = Calendar.YEAR;
		} else if (MONTH_MODE.equals(mode))
		{
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calField = Calendar.MONTH;
		} else if (DAY_MODE.equals(mode))
		{
			calField = Calendar.DATE;
		}
		for (int i = 0; i < numOfList; i++)
		{
			String strDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			listOfDates.add(strDate);
			calendar.add(calField, -1);
		}
		return listOfDates;
	}
}
