package es.udc.rs.deliveries.client.util;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class XMLGregorianCalendarConversor {

	public static Calendar toCalendar(XMLGregorianCalendar gregorianCalendar) {
		if(gregorianCalendar == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(gregorianCalendar.toGregorianCalendar().getTimeInMillis());
		return calendar;
	}

}
