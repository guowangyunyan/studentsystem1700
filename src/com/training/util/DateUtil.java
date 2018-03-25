package com.training.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String FORMAT_1 = "yyyyMMddHHmmss";
	private static final String FORMAT_2 = "yyyy-MM-dd";
	private static final String FORMAT_3 = "yyyy-MM-dd HH:mm:ss";

	public static Date getDateByString(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_2);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateTimeByString(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_3);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
