package es.udc.rs.deliveries.jaxrs.util;

import java.util.Calendar;

import es.udc.rs.deliveries.jaxrs.dto.shipment.DateDtoJaxb;

public class CalendarToCalendarDtoJaxbConversor {

	public static DateDtoJaxb toDateDtoJaxb(Calendar calendar) {
		return new DateDtoJaxb(calendar);
	}

}
