package com.hybridFramework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author vasudevp
 *
 */
public class DateTimeHelper {

	/**
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String time="" + dateFormat.format(cal.getTime());
		return time;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return getCurrentDateTime().substring(12, 8);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDateTime().substring(0, 11);
	}
}
