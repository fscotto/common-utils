package it.common.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Classe di utilita' per la gestione delle date.
 *
 * @author Fabio Scotto di Santolo
 * @since 27/09/2016
 */
public class DateUtils {

	private static final DateUtils instance = new DateUtils();

	private static Set<DateFormat> formatters = new HashSet<>();

	private DateUtils() {
		// costruttore vuoto privato
	}

	public static DateUtils getInstance() {
		return instance;
	}

	private void formatters() {
		formatters.add(new SimpleDateFormat("dd/MM/yyyy"));
		formatters.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"));
		formatters.add(new SimpleDateFormat("dd MMM yyyy"));
		formatters.add(new SimpleDateFormat("M/dd/yyyy"));
		formatters.add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
		formatters.add(new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z"));
		formatters.add(new SimpleDateFormat("EEE, MMM d, ''yy"));
		formatters.add(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z"));
		formatters.add(new SimpleDateFormat("EEE, MMM d HH:mm:ss Z yyyy"));
		formatters.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US));
	}

	public Date stringToDate(String str) {
		formatters();

		if (str == null) {
			return null;
		}
		Date date = null;
		for (DateFormat formatter : formatters) {
			try {
				date = formatter.parse(str);
				if (date != null) {
					return date;
				}
			} catch (ParseException pe) {
				// log.error("Formato stringa non valido!",
				// DateUtil.getInstance());
			}
		}

		return new Date();
	}

	public String format(Date date) {
		formatters();

		if (date == null) {
			return null;
		}
		String str = null;
		for (DateFormat formatter : formatters) {
			str = formatter.format(date);
			if (str != null) {
				return str;
			}
		}

		return "";
	}
}
