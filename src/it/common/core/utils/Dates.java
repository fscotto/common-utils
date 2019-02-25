package it.common.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe di utilita' per la gestione delle date.
 *
 * @author Fabio Scotto di Santolo
 * @since 27/09/2016
 */
public final class Dates {
	private static final long SECOND_IN_MILLIS = 1000;
	private static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
	private static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
	private static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
	private static final long WEEK_IN_MILLIS = DAY_IN_MILLIS * 7;

	public static String toString(Date date, String pattern) {
		DateFormat format = getDateFormatter(pattern);
		return format.format(date);
	}

	public static Date toDate(String date, String pattern) throws ParseException {
		DateFormat format = getDateFormatter(pattern);
		return format.parse(date);
	}

	public static int getYear(Date date) {
		return getField(date, Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		return getField(date, Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		return getField(date, Calendar.DATE);
	}

	public static boolean isLeap(Date date) {
		int year = getYear(date);
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	private static int getField(Date date, int field) {
		Calendar cal = toCalendar(date);
		return cal.get(field);
	}

	public static long differenceInDays(Date from, Date to) {
		long dayFrom = fromMillisecondToDay(from.getTime());
		long dayTo = fromMillisecondToDay(to.getTime());
		return dayTo - dayFrom;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = toCalendar(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = toCalendar(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	public static Date addYears(Date date, int years) {
		Calendar cal = toCalendar(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	public static long fromMillisecondToSecond(long millisecond) {
		return millisecond / SECOND_IN_MILLIS;
	}

	public static long fromMillisecondToMinute(long millisecond) {
		return millisecond / MINUTE_IN_MILLIS;
	}

	public static long fromMillisecondToHour(long millisecond) {
		return millisecond / HOUR_IN_MILLIS;
	}

	public static long fromMillisecondToDay(long millisecond) {
		return millisecond / DAY_IN_MILLIS;
	}

	public static java.sql.Date toSqlDate(Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static DateFormat getDateFormatter(String pattern) {
		return new SimpleDateFormat(pattern);
	}
}
