package it.fscotto.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatesTest {

  private Calendar date;

  @Before
  public void init() {
    date = Calendar.getInstance();
    date.set(2000, Calendar.DECEMBER, 31, 0, 0, 0);
    date.set(Calendar.MILLISECOND, 0);
  }

  @Test
  public void toStringAllOKTest() {
    assertEquals("31/12/2000", Dates.toString(date.getTime(), "dd/MM/yyyy"));
  }

  @Test
  public void toStringNullDateTest() {
    assertNull(Dates.toString(null, "ddMMyyyy"));
  }

  @Test
  public void toStringNullPatternTest() {
    assertEquals("31122000", Dates.toString(date.getTime(), null));
  }

  @Test
  public void toDateAllOkTest() {
    try {
      assertEquals(date.getTime(), Dates.toDate("31/12/2000", "dd/MM/yyyy"));
    } catch (ParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void toDateNullDateTest() {
    try {
      assertNull(Dates.toDate(null, "dd/MM/yyyy"));
    } catch (ParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void toDateNullPatternTest() {
    try {
      assertEquals(date.getTime(), Dates.toDate("31122000", null));
    } catch (ParseException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void getYearAllOkTest() {
    assertEquals(2000, Dates.getYear(date.getTime()));
  }

  @Test
  public void getMonthAllOkTest() {
    assertEquals(12, Dates.getMonth(date.getTime()));
  }

  @Test
  public void getDayAllOkTest() {
    assertEquals(31, Dates.getDay(date.getTime()));
  }

  @Test
  public void isLeapAllOkTest() {
    assertTrue(Dates.isLeap(date.getTime()));
  }

  @Test
  public void isLeapNotLeapYearTest() {
    date.set(Calendar.YEAR, 2001);
    assertFalse(Dates.isLeap(date.getTime()));
  }

  @Test
  public void getPeriodInDaysSameDateTest() {
    assertEquals(0, Dates.getPeriodInDays(date.getTime(), date.getTime()));
  }

  @Test
  public void getPeriodInDaysDifferentDateTest() {
    try {
      Calendar other = (Calendar) date.clone();
      other.add(Calendar.DATE, -1);
      assertEquals(1, Dates.getPeriodInDays(other.getTime(), date.getTime()));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void getPeriodInDaysWrongPositionArgumentsTest() {
    try {
      Calendar other = (Calendar) date.clone();
      other.add(Calendar.DATE, -1);
      assertEquals(1, Dates.getPeriodInDays(date.getTime(), other.getTime()));
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test(expected = NullPointerException.class)
  public void getPeriodInNullArgumentsDaysTest() {
    Dates.getPeriodInDays(null, null);
  }

  @Test
  public void addDaysAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.JANUARY, 1, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addDays(date.getTime(), 1));
  }

  @Test
  public void addDaysAddNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.JANUARY, 1, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addDays(date.getTime(), -1));
  }

  @Test
  public void addMonthAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.JANUARY, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addMonths(date.getTime(), 1));
  }

  @Test
  public void addMonthsAddNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.JANUARY, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addMonths(date.getTime(), -1));
  }

  @Test
  public void addYearsAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.DECEMBER, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addYears(date.getTime(), 1));
  }

  @Test
  public void addYearsAddNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2001, Calendar.DECEMBER, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.addYears(date.getTime(), -1));
  }

  @Test
  public void subDaysAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2000, Calendar.DECEMBER, 30, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subDays(date.getTime(), 1));
  }

  @Test
  public void subDaysSubNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2000, Calendar.DECEMBER, 30, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subDays(date.getTime(), -1));
  }

  @Test
  public void subMonthsAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2000, Calendar.NOVEMBER, 30, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subMonths(date.getTime(), 1));
  }

  @Test
  public void subMonthsNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2000, Calendar.NOVEMBER, 30, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subMonths(date.getTime(), -1));
  }

  @Test
  public void subYearsAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(1999, Calendar.DECEMBER, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subYears(date.getTime(), 1));
  }

  @Test
  public void subYearsNegativeValueTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(1999, Calendar.DECEMBER, 31, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.subYears(date.getTime(), -1));
  }

  @Test
  public void fromMillisecondsToSecondsAllOkTest() {
    assertEquals(1, Dates.fromMillisecondToSeconds(1000));
  }

  @Test
  public void fromMillisecondsToMinutesAllOkTest() {
    assertEquals(1, Dates.fromMillisecondToMinutes(60 * 1000));
  }

  @Test
  public void fromMillisecondsToHoursAllOkTest() {
    assertEquals(1, Dates.fromMillisecondToHours(60 * 60 * 1000));
  }

  @Test
  public void fromMillisecondsToDaysAllOkTest() {
    assertEquals(1, Dates.fromMillisecondToDays(24 * 60 * 60 * 1000));
  }

  @Test
  public void getEasterDayAllOkTest() {
    Calendar expected = Calendar.getInstance();
    expected.set(2019, Calendar.APRIL, 21, 0, 0, 0);
    expected.set(Calendar.MILLISECOND, 0);
    assertEquals(expected.getTime(), Dates.getEasterDay(2019));
  }

}
