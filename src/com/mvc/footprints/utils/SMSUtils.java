package com.mvc.footprints.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SMSUtils {
	
	private static final String ACCOUNT_KEY = "kGt2e4Fu81d5aqNT2K7aovR3MwkI2m3a";
	
	public static void main(String[] args) {
//		try {
//			System.out.println(sendSMSGetRequest("+1(905)923-0240", "Thank you for signing up for My Footprints =) Your verification code is 325422"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//
		//+1(905)923-0240
//		System.out.println(parseNumber("9059230240"));
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/isbbsdatabase", "root", "");
//			System.out.println(conn);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		try {
			sendSMSGetRequest("9059230240", "测试test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String parseNumber(String phoneNumber){
		phoneNumber = "+1(" + phoneNumber.substring(0, 3) + ")" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
		return phoneNumber;
	}
	
	public static String sendSMSGetRequest(String phoneNumber, String smsBody) throws IOException{ 
		String result = null;

		String urlStr = "http://smsgateway.ca/SendSMS.aspx?" + 
		"AccountKey=" + ACCOUNT_KEY +
		"&CellNumber=" + parseNumber(phoneNumber) +
		"&MessageBody=" + URLEncoder.encode(smsBody, "GBK") + 
		"&Reference=FOOTPRINTS";

		URL url = new URL(urlStr);
		URLConnection conn = url.openConnection();

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;

		while ((line = rd.readLine()) != null) {
		   sb.append(line);
		}

		rd.close();
		result = sb.toString();
		return result; 
	}
	
}

