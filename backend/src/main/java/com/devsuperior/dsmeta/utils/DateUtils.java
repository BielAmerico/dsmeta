package com.devsuperior.dsmeta.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtils {
	
	/**
	 * Get date now with zone id.
	 * 
	 * @return A current LocalDate
	 */
	public static LocalDate dateNowWithZoneId() {
		return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}
}
