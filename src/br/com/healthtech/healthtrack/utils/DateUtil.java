package br.com.healthtech.healthtrack.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
	
	public static LocalDateTime toDateTime(String text) {
		return LocalDateTime.parse(text, dateTimeFormatter);
	}
	
	public static String toText(LocalDateTime date) {
		return date.format(dateTimeFormatter);
	}

	public static LocalDate toDate(String text) {
		return LocalDate.parse(text, dateFormatter);
	}
	
	public static String toText(LocalDate date) {
		return date.format(dateFormatter);
	}

}
