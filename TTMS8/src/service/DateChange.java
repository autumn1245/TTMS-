package service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateChange {

	public static Timestamp UToS(java.util.Date d) {
		Timestamp timestamp = null;
		timestamp = new Timestamp(d.getTime());
		
		return timestamp;
	}
	
	public static java.util.Date SToU(Timestamp d){
		java.util.Date date = null;
		
		date = new java.util.Date(d.getTime());
		
		return date;
	}
	
	public static java.util.Date StToU(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		java.util.Date date = null;
		try {
			date = dateFormat.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static Timestamp StToS(String d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		java.util.Date date = null;
		try {
			date = dateFormat.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timestamp timestamp = null;
		timestamp = new Timestamp(date.getTime());
		
		return timestamp;
	}
	
	public static String UToSt(java.util.Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = null;
		
		date = dateFormat.format(d);
		
		return date;
	}
	
	public static void main(String[] args) {
		Timestamp timestamp = null;
		java.util.Date dateUtil = null;
		
		String date = "2019-1-1 10:20:30";
		dateUtil = StToU(date);
		System.out.println( "Util的："+dateUtil);
		
		timestamp = StToS(date);
		System.out.println(SToU(timestamp));
		
		timestamp = UToS(dateUtil);
		System.out.println(timestamp);
		
		date = UToSt(dateUtil);
		System.out.println(date);
		
	}

}
