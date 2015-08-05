package com.mvc.footprints.utils;

public class DistanceUtils {
	 private static final double EARTH_RADIUS = 6378137;
	     private static double rad(double d) {
	        return d * Math.PI / 180.0;
	     }
	     
	     /**
	      * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	      * @param lng1
	      * @param lat1
	      * @param lng2
	      * @param lat2
	      * @return
	      */
	     public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
	        double radLat1 = rad(lat1);
	        double radLat2 = rad(lat2);
	        double a = radLat1 - radLat2;
	        double b = rad(lng1) - rad(lng2);
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	         Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	        s = s * EARTH_RADIUS;
	        s = Math.round(s * 10000) / 10000;
	        return s;
	     }
	     
	     
	     /**
	      * @param args
	      */
	     public static void main(String[] args) {
	     // TODO 自动生成方法存根
	 		double[] d1 = new double[]{112.9282600000, 28.2125090000 };
	 		double[] d2 = new double[]{112.9294010000, 28.2139730000 };
	 		double[] d3 = new double[]{117.563535, 39.132896 };
	 		double[] d4 = new double[]{117.667394, 39.142278 };
	 		double[] d5 = new double[]{113.030415, 28.209134 };
	 		double[] d6 = new double[]{113.030428, 28.209173 };
//	         double distance = getDistance(121.491909,31.233234,121.411994,31.206134);
	         double distance1 = getDistance(d1[0],d1[1],d2[0],d2[1]);
	         double distance2 = getDistance(d3[0],d3[1],d4[0],d4[1]);
	         double distance3 = getDistance(d5[0],d5[1],d6[0],d6[1]);
	         
	         System.out.println("Distance1 is:"+distance1);
	         System.out.println("Distance2 is:"+distance2);
	         System.out.println("Distance3 is:"+distance3);
	     }
	 
	 }
