package it.fscotto.utils;

/**
 * Classe Singleton per la gestione delle stringhe
 *
 * @author Fabio Scotto di Santolo
 * @since 22/12/2016
 */
public class StringUtils {

	private final static StringUtils instance = new StringUtils();

	private StringUtils() {
		// Costruttore Vuoto
	}

	public static StringUtils getInstance() {
		return instance;
	}

	/**
	 * Questo metodo serve per formattare le stringhe in modo tale da evitare
	 * errori dovuti alla presenza di valori speciali.
	 */
	public String escapeForJava(String value, boolean quote) {
		StringBuilder builder = new StringBuilder();
		if (quote) {
			builder.append("\"");
		}
		for (char c : value.toCharArray()) {
			if (c == '\'') {
				builder.append("\\'");
			} else if (c == '\"') {
				builder.append("\\\"");
			} else if (c == '\r') {
				builder.append("\\r");
			} else if (c == '\n') {
				builder.append("\\n");
			} else if (c == '\t') {
				builder.append("\\t");
			} else if (c < 32 || c >= 127) {
				builder.append(String.format("\\u%04x", (int) c));
			} else {
				builder.append(c);
			}
		}
		if (quote) {
			builder.append("\"");
		}
		return builder.toString();
	}

	public String stringWithNewLine(String str) {
		return str.concat("\n");
	}

}
