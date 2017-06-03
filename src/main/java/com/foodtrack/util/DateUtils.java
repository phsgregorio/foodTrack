package com.foodtrack.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/**
	 * Convert Date to Calendar
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * Convert String to Calendar
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendar(String date) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(format.parse(date));
		
		return calendar;
	}
	
	/**
	 * Get String days of the given week date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static ArrayList<String> getDaysOfWeek(String date) throws ParseException {

		Calendar now = DateUtils.toCalendar(date);

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	    String[] days = new String[7];
	    int delta = - now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 to start from Monday
	    now.add(Calendar.DAY_OF_MONTH, delta );

	    for (int i = 0; i < 7; i++) {
	        days[i] = format.format(now.getTime());
	        now.add(Calendar.DAY_OF_MONTH, 1);
	    }

	    return new ArrayList<String>(Arrays.asList(days));
	}
}
