package com.garage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.garage.exception.PrenotationException;

public class Utility {

	public java.sql.Date parseDataToSql(String toParse) throws PrenotationException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArr = { null, null, null };
		java.sql.Date parsedToSql = null;
		dateArr = toParse.split("/");
		if (dateArr != null) {
			toParse = dateArr[2] + "-" + dateArr[0] + "-" + dateArr[1];
			java.util.Date parsedToUtil = null;
			try {
				parsedToUtil = format.parse(toParse);
			} catch (ParseException e) {
				throw new PrenotationException(e);
			}
			parsedToSql = new java.sql.Date(parsedToUtil.getTime());
		}
		return parsedToSql;
	}
	
}
