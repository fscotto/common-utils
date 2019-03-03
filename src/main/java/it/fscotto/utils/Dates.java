package it.fscotto.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
  private static final String DEFAULT_PATTERN = "ddMMyyyy";

  private Dates() {
  }

  public static String toString(Date date, String pattern) {
    if (date == null) {
      return null;
    }
    DateFormat format = getDateFormatter(pattern == null ? DEFAULT_PATTERN : pattern);
    return format.format(date);
  }

  public static Date toDate(String date, String pattern) throws ParseException {
    if (date == null) {
      return null;
    }
    DateFormat format = getDateFormatter(pattern == null ? DEFAULT_PATTERN : pattern);
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

  public static long getPeriodInDays(Date from, Date to) {
    long dayFrom = fromMillisecondToDays(from.getTime());
    long dayTo = fromMillisecondToDays(to.getTime());
    return Math.abs(dayTo - dayFrom);
  }

  public static Date addDays(Date date, int days) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.DATE, Math.abs(days));
    return cal.getTime();
  }

  public static Date addMonths(Date date, int months) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.MONTH, Math.abs(months));
    return cal.getTime();
  }

  public static Date addYears(Date date, int years) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.YEAR, Math.abs(years));
    return cal.getTime();
  }

  public static Date subDays(Date date, int days) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.DATE, -Math.abs(days));
    return cal.getTime();
  }

  public static Date subMonths(Date date, int months) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.MONTH, -Math.abs(months));
    return cal.getTime();
  }

  public static Date subYears(Date date, int years) {
    Calendar cal = toCalendar(date);
    cal.add(Calendar.YEAR, -Math.abs(years));
    return cal.getTime();
  }

  public static long fromMillisecondToSeconds(long millisecond) {
    return millisecond / SECOND_IN_MILLIS;
  }

  public static long fromMillisecondToMinutes(long millisecond) {
    return millisecond / MINUTE_IN_MILLIS;
  }

  public static long fromMillisecondToHours(long millisecond) {
    return millisecond / HOUR_IN_MILLIS;
  }

  public static long fromMillisecondToDays(long millisecond) {
    return millisecond / DAY_IN_MILLIS;
  }

  public static Date getEasterDay(int year) {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int remain19 = year % 19;
    int century = (year / 100);
    int temp = (century - 15) / 2 + 202 - 11 * remain19;
    if (Arrays.asList(21, 24, 25, 27, 28, 29, 30, 31, 32, 35, 38).contains(century)) {
      temp = temp - 1;
    } else if (Arrays.asList(33, 36, 37, 39, 40).contains(century)) {
      temp = temp - 2;
    }
    temp = temp % 30;
    a = temp + 21;
    if (temp == 29) {
      a = a - 1;
    }
    if ((temp == 28) && (remain19 > 10)) {
      a = a - 1;
    }
    b = (a - 19) % 7;
    c = (40 - century) % 4;
    if (c == 3) {
      c = c + 1;
    }
    if (c > 1) {
      c = c + 1;
    }
    temp = year % 100;
    d = (temp + temp / 4) % 7;
    e = ((20 - b - c - d) % 7) + 1;

    Calendar cal = Calendar.getInstance();
    int day = a + e;
    if (day > 31) {
      cal.set(year, Calendar.APRIL, day - 31, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
    } else {
      cal.set(year, Calendar.MARCH, day, 0, 0, 0);
      cal.set(Calendar.MILLISECOND, 0);
    }
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
