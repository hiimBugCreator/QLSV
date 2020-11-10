package qlhs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
	static Date date = new Date(); // This object contains the current date value
	
	public static String DateNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(date);
	}
}
