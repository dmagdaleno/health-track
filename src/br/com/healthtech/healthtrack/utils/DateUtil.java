package br.com.healthtech.healthtrack.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public static LocalDateTime toDate(String text) {
		return LocalDateTime.parse(text, formatter);
	}
	
	public static String toText(LocalDateTime date) {
		return date.format(formatter);
	}

}
