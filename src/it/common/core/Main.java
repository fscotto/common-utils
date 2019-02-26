package it.common.core;

import it.common.core.utils.Dates;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World");

		Date d = new Date();
		System.out.println(Dates.getDay(d));
		System.out.println(Dates.getMonth(d));
		System.out.println(Dates.getYear(d));

		System.out.println(Dates.toString(d, "ddMMyyyy"));
		System.out.println(Dates.toDate("10022019", "ddMMyyyy"));

		Date d1 = Dates.toDate("1002019", "ddMMyyyy");
		Date d2 = Dates.toDate("1202019", "ddMMyyyy");
		System.out.println(Dates.differenceInDays(d1, d2));
		System.out.println(Dates.toSqlDate(new Date()));

		System.out.println(Dates.addDays(d1, 4));
		System.out.println(Dates.getEasterDay(2019));
	}
}
