package com.mvc.footprints.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static boolean isSameMonth(Date nowDate, Date compareDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		
		int nowMonth = calendar.get(Calendar.MONTH);
		
		calendar.setTime(compareDate);
		int compareMonth = calendar.get(Calendar.MONTH);
		return nowMonth == compareMonth;
	}
	
	public static void main(String[] args) {
		Date nowDate = new Date();
		Date compareDate = new Date(nowDate.getTime() + 1000 * 60 * 60 * 3);
		System.out.println(nowDate);
		System.out.println(compareDate);
		System.out.println(isSameMonth(nowDate, compareDate));
	}
}
