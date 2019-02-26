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

	public static Date getEasterDay(int year) {
		int century = (year / 100); //first 2 digits of year
		int temp; //intermediate results
		int tA, tB, tC, tD, tE; //table A to E results
		int day = 0;
		int month = 0;
		int remain19 = year % 19; //remainder of year / 19		// calculate Paschial Full Moon date
		temp = (century - 15) / 2 + 202 - 11 * remain19;
		switch (century) {
			case ((21) | (24) | (25) | (27) | (28) | (29) | (30) | (31) | (32) | (34) | (35) | (38)) :
				temp = temp - 1;
			case ((33) | (36) | (37) | (39) | (40)) :
				temp = temp - 2;
		}
		temp = temp % 30;
		tA = temp + 21;
		if (temp == 29) {
			tA = tA - 1;
		}
		if ((temp == 28) && (remain19 > 10)) {
			tA = tA - 1;
		} //find the next Sunday
		tB = (tA - 19) % 7;
		tC = (40 - century) % 4;
		if (tC == 3) {
			tC = tC + 1;
		}
		if (tC > 1) {
			tC = tC + 1;
		}
		temp = year % 100;
		tD = (temp + temp / 4) % 7;
		tE = ((20 - tB - tC - tD) % 7) + 1;
		day = tA + tE; // return the date
		if (day > 31) {
			day = day - 31;
			month = 4;
		} else {
			month = 3;
		}
		String d = day < 10 ? "0" + day : "" + day;
		String m = month < 10 ? "0" + month : "" + month;
		Calendar cal = Calendar.getInstance();
		cal.set(year, Integer.valueOf(m) - 1, Integer.valueOf(d), 0, 0, 0);
		return cal.getTime();
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
