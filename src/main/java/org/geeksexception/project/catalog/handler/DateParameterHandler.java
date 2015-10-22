package org.geeksexception.project.catalog.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateParameterHandler implements ParamConverterProvider,
		ParamConverter<Date> {
	
	private static final DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
	
	@Override
	public Date fromString(String date) throws IllegalArgumentException {
		
		DateTime dateTime = formatter.parseDateTime(date);
		
		return dateTime.toDate();
		
	}

	@Override
	public String toString(Date date) throws IllegalArgumentException {
		
		DateTime dateTime = new DateTime(date);
		
		return dateTime.toString(formatter);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> clazz, Type type,
			Annotation[] annotations) {
		if(clazz == Date.class) return (ParamConverter<T>) this;
		
		return null;
	}
	
}