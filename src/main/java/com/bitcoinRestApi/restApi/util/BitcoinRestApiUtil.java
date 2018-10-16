package com.bitcoinRestApi.restApi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BitcoinRestApiUtil {
	
	public Calendar getLastMonthFirstDay() {
		 Calendar c = Calendar.getInstance();		    
		    c.add(Calendar.MONTH, -1);
		    c.set(Calendar.DATE, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH-1));		    
		    return c;

	}
	
	
	public Calendar getLastMonthLastDay() {
		 Calendar c = Calendar.getInstance();
		    c.add(Calendar.MONTH, -1); 		    
		    c.set(Calendar.DATE,Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)-1);		    
		    return c;
	}
	
	
	public Calendar  getLastWeekFirstDay() {
		 Calendar c = Calendar.getInstance();		    
		 int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DATE, -i - 7);
		    return c;   		

	}
	
	
	public Calendar  getLastWeekLastDay() {
		 Calendar c = Calendar.getInstance();
		 int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DATE, -i - 7);
		    c.add(Calendar.DATE, 6);    
		    return c;
	}
	
	
	public int getLastYear() {
		 Calendar c = Calendar.getInstance();
		    c.add(Calendar.YEAR, -1);		   		    
		    return c.get(Calendar.YEAR);
	}
	
	public int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public Date getDate(String strDate) {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date cDate = null;
		try {
			cDate = sDF.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cDate;
	}
	
	public Date getOnlyDate(String strDate) {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate = null;
		try {
			cDate = sDF.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cDate;
	}
	
	
	public Date getCurrentDay() {
		Calendar calendar = Calendar.getInstance(); // this would default to now
		return calendar.getTime();
	}
	
	public Date getlastNthDay(int n) {	    
		 Calendar calendar = Calendar.getInstance(); // this would default to now
		 calendar.add(Calendar.DAY_OF_MONTH, -n);
		    return calendar.getTime(); 		

	}
	
	
}
