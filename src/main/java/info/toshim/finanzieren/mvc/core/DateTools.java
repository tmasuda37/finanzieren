package info.toshim.finanzieren.mvc.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools
{
	public static int getNumInDates(Date date1, Date date2)
	{
		long datetime1 = date1.getTime();
		long datetime2 = date2.getTime();
		long one_date_time = 1000 * 60 * 60 * 24;
		long diffDays = (datetime2 - datetime1) / one_date_time;
		return (int) diffDays;
	}

	public static Date getDateFromStrDate(String strDate) throws ParseException
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
	}
}
