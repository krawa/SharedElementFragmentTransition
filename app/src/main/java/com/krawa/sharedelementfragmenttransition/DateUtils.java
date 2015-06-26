package com.krawa.sharedelementfragmenttransition;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	private static DateUtils instance;
	private static Locale loc;
	private Calendar cal;
	private String today;
	private String yesterday;
	private String tomorrow;
	
	private SimpleDateFormat dateCalcFormat;
	private SimpleDateFormat hoursFormat;
	private SimpleDateFormat dateMessageFormat;
	private SimpleDateFormat dateYearMessageFormat;
	private int nowYear;


	private DateUtils() {
		
		loc = Locale.getDefault();
		
		dateCalcFormat = new SimpleDateFormat("yyyy-MM-dd", loc);
		hoursFormat = new SimpleDateFormat("HH:mm", loc);		
		dateMessageFormat = new SimpleDateFormat("dd MMM", loc);
		dateYearMessageFormat = new SimpleDateFormat("dd MMM yyyy", loc);
	}
	
	public static DateUtils getInstance() {
		if(instance == null || !loc.equals(Locale.getDefault())) instance = new DateUtils();
		instance.initDates();
		return instance;		
	}
	

	/**
	 * 
	 */
	private void initDates() {
		cal = Calendar.getInstance();
		
		today = dateCalcFormat.format(cal.getTime());
	    cal.add(Calendar.DATE, -1);
	    yesterday = dateCalcFormat.format(cal.getTime());
	    cal.add(Calendar.DATE, 2);
	    tomorrow = dateCalcFormat.format(cal.getTime());
		nowYear = cal.get(Calendar.YEAR);
	}

	public String getDateTimeString(Date createTime) {
	    cal.setTime(createTime);
	    String createDay = dateCalcFormat.format(cal.getTime());
	    if(createDay.equals(today)){	    	
	    	return hoursFormat.format(createTime);
	    }else if(createDay.equals(yesterday)){
	    	return "Yesterday";
	    }else if(createDay.equals(tomorrow)){
	    	return "Tomorrow";
	    }else if(cal.get(Calendar.YEAR) == nowYear){
			return dateMessageFormat.format(createTime);
		}else{
			return dateYearMessageFormat.format(createTime);
	    }		
	}

}
