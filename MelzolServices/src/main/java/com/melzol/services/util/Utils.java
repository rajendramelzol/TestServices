package com.melzol.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



public class Utils {
	
	public static Date parseDate(String dt) {
		if(dt==null || dt.trim().length()==0)return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		try {
			return formatter.parse(dt);
		} catch (ParseException e) {
			//logger.info("Error while parsing date",e);
		}
		return null;
	}
	public static String  formatDateTime(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("E, MMM dd");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		return formatter.format(dat);
	}
	public static Double trimDouble(Double d){
		
	        return Math.floor(d * 100) / 100;
	}
	public static String  formatDateBlogs(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		return formatter.format(dat);
	}
	public static String  formatDateForum(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		return formatter.format(dat);
	}
	public static Date parseCreatedDate(String dt) {
		if(dt==null || dt.trim().length()==0)return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			return formatter.parse(dt);
		} catch (ParseException e) {
			//logger.info("Error while parsing date",e);
		}
		return null;
	}
	public static String  formatDateComments(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("d MMM, hh:mm a");
		return formatter.format(dat);
	}
	public static Date parseEventStartDate(String dt) {
		if(dt==null || dt.trim().length()==0)return null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		try {
			return formatter.parse(dt);
		} catch (ParseException e) {
			//logger.info("Error while parsing date",e);
		}
		return null;
	}
	public static String  formatEventDate(Date dat) {
		if(dat==null )return "";
		DateFormat formatter = new SimpleDateFormat("EEE dd MMMM hh:mm a");
		return formatter.format(dat);
	}
	public static String parseCommentDate(Date dt) {
		if(dt==null)return null;
		 // Date currentDate = new Date(dt);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
		
			return formatter.format(dt);
		
	}

}
