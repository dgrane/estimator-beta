package com.rga.estimator2;


public class DataUtils {

	public final static String SLA2017 = "SLA2017";

	public final static String FALABELLA = "Falabella";
	
	public final static String SANTIAGO2017 = "Santiago2017";
	
	public final static int HOURS_PER_YEAR = 1920;

	public static final int WEEKS = 1;
	
	public static final int MONTHS = 2;
	
	public static final int YEARS = 3;




	
	public static Object trimArea(String area) {
		if (area.equalsIgnoreCase("Business Operations"))
			return "BO";
		if (area.equalsIgnoreCase("Business Transformation"))
			return "BT";
		if (area.equalsIgnoreCase("Client Services"))
			return "CS";
		if (area.equalsIgnoreCase("Communications"))
			return "COMM";
		if (area.equalsIgnoreCase("Content Studio"))
			return "CONT";
		if (area.equalsIgnoreCase("Copywriting"))
			return "COPY";
		if (area.equalsIgnoreCase("Digital Advertising"))
			return "DA";
		if (area.equalsIgnoreCase("Executive Management"))
			return "EXEC";
		if (area.equalsIgnoreCase("Experience Design"))
			return "XD";
		if (area.equalsIgnoreCase("Marketing Sciences"))
			return "MS";
		if (area.equalsIgnoreCase("Production"))
			return "PROD";
		if (area.equalsIgnoreCase("Quality Assurance"))
			return "QA";
		if (area.equalsIgnoreCase("Strategy"))
			return "STRAT";
		if (area.equalsIgnoreCase("Technology"))
			return "TECH";
		if (area.equalsIgnoreCase("Visual Design"))
			return "VISUAL";
		return area;
	}

	public static String currencyValidator(String rate) {
		if (rate.startsWith("CLP")) {
			String a = rate.replace("CLP ", "");
			String b = a.replace(",", "");
			rate = b;

		}
		if (rate.contains("$")) {
			rate.replace("$", ""); 

		}
		if (rate.contains("-")) {
			rate = "0";
		}
		return rate;
	}

}
