package com.cars24.dateutils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;




public class DateUtil {
	
	public static String getDateInRequiredFormat(String daysToAdd)   {
		return getDateInRequiredFormat(daysToAdd, "dd/MM/yyyy");
	}
	
	public static String getDateInRequiredFormat(int daysToAdd)   {
		return getDateInRequiredFormat(daysToAdd, "dd/MM/yyyy");
	}
	
	/*Letter	Date or Time Component	Presentation	Examples
	G	Era designator	Text	AD
	y	Year	Year	1996; 96
	Y	Week year	Year	2009; 09
	M	Month in year	Month	July; Jul; 07
	w	Week in year	Number	27
	W	Week in month	Number	2
	D	Day in year	Number	189
	d	Day in month	Number	10
	F	Day of week in month	Number	2
	E	Day name in week	Text	Tuesday; Tue
	u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
	a	Am/pm marker	Text	PM
	H	Hour in day (0-23)	Number	0
	k	Hour in day (1-24)	Number	24
	K	Hour in am/pm (0-11)	Number	0
	h	Hour in am/pm (1-12)	Number	12
	m	Minute in hour	Number	30
	s	Second in minute	Number	55
	S	Millisecond	Number	978
	z	Time zone	General time zone	Pacific Standard Time; PST; GMT-08:00
	Z	Time zone	RFC 822 time zone	-0800
	X	Time zone	ISO 8601 time zone	-08; -0800; -08:00*/
	
	public static String getDateInRequiredFormat(String daysToAdd, String dateFormat)   {
		return getDateInRequiredFormat(Integer.parseInt(daysToAdd), dateFormat);
	}
	
	private static Calendar getCalendarObject(int yearsToAdd, int monthsToAdd, int daysToAdd,int hoursToAdd, int minutesToAdd){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR,yearsToAdd);
		now.add(Calendar.MONTH, monthsToAdd);
		now.add(Calendar.DATE,daysToAdd);
		now.add(Calendar.HOUR,hoursToAdd);
		now.add(Calendar.MINUTE,minutesToAdd);
		return now;
	}
	
	public static String getDateInRequiredFormat(int yearsToAdd, int monthsToAdd, int daysToAdd,int hoursToAdd, int minutesToAdd, String requiredDateFormat, String timeZone)   {
		Calendar now = getCalendarObject(yearsToAdd, monthsToAdd, daysToAdd,hoursToAdd, minutesToAdd);
		SimpleDateFormat sdf = new SimpleDateFormat(requiredDateFormat);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(now.getTime());
	}
	
	public static String getDateInRequiredFormat(String yearsToAdd, String monthsToAdd, String daysToAdd,String hoursToAdd, String minutesToAdd, String requiredDateFormat, String timeZone)   {
		return getDateInRequiredFormat(Integer.parseInt(yearsToAdd), Integer.parseInt(monthsToAdd), Integer.parseInt(daysToAdd),Integer.parseInt(hoursToAdd), Integer.parseInt(minutesToAdd), requiredDateFormat, timeZone);
	}
	
	public static String getDateInRequiredFormat(int yearsToAdd, int monthsToAdd, int daysToAdd,int hoursToAdd, int minutesToAdd, String requiredDateFormat)   {
		Calendar now = getCalendarObject(yearsToAdd, monthsToAdd, daysToAdd,hoursToAdd, minutesToAdd);
		SimpleDateFormat sdf = new SimpleDateFormat(requiredDateFormat);
		return sdf.format(now.getTime());
	}
	
	public static String getDateInRequiredFormat(int daysToAdd, String requiredDateFormat)   {
		return getDateInRequiredFormat(0, 0, daysToAdd,0, 0,requiredDateFormat);
	}
	
	public static String getDateInRequiredFormat(int daysToAdd,String givenDate, String reqdateFormat) throws Exception   {
		return getDateInRequiredFormat(daysToAdd,givenDate,"MM/dd/yyyy",reqdateFormat);	
	}
	
	public static String getDateInRequiredFormat(String daysToAdd,String givenDate, String reqdateFormat) throws Exception   {
		return getDateInRequiredFormat(daysToAdd,givenDate,"MM/dd/yyyy",reqdateFormat);	
	}
	
	public static String getDateInRequiredFormat(String daysToAdd,String givenDate, String givenDateFormat, String reqdateFormat) throws Exception   {
		return getDateInRequiredFormat(Integer.parseInt(daysToAdd),givenDate, givenDateFormat, reqdateFormat);
	}
	
	public static String getDateInRequiredFormat(int daysToAdd,String givenDate, String givenDateFormat, String reqdateFormat) throws Exception   {
		DateFormat format = new SimpleDateFormat(givenDateFormat);
		Date date = format.parse(givenDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,daysToAdd);
		return new SimpleDateFormat(reqdateFormat).format(calendar.getTime());
	}
	@SuppressWarnings("unused")
	public static boolean getDateInRequiredFormatandCompare(String givenDate1, String givenDate1Format, String givenDate2, String givenDate2Format) throws Exception   {
	
		
		Date date1 = null;
		Date date2= null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(givenDate1Format);
        date1 = simpleDateFormat.parse(givenDate1);
	    SimpleDateFormat formatter = new SimpleDateFormat(givenDate2Format);
	    date2 = formatter.parse(givenDate2);
	
		/*
		 * if(DateUtils.isSameDay(date1, date2)) return true; else
		 */
	    	return false;
	}
	@SuppressWarnings("resource")
	public static String getDateInRequiredTextformat(String daysToAdd)
	{
		String InreuiredFormat ="";
		Formatter fm=new Formatter();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE,Integer.parseInt(daysToAdd));
		fm.format("%td%tb",cal,cal,cal);
		return InreuiredFormat+""+fm;
	}
	@SuppressWarnings({ "unused", "static-access" })
	public static List<String> getDateAndMonthFormatForOffline()
	{
		List<String> date=new ArrayList<String>();
		DateUtil dateUtil = new DateUtil();
		
	    String departuredate = dateUtil.getDateInRequiredFormat(90, "MM/dd/yyyy");

		String dep_monthvalue = departuredate.split("/")[0].trim();
		String dep_dateval = departuredate.split("/")[1].trim();
		String dep_yearval = departuredate.split("/")[2].trim();

		String arrivaldate = dateUtil.getDateInRequiredFormat(92, "MM/dd/yyyy");
		String arr_monthval = arrivaldate.split("/")[0].trim();
		String arr_dateval = arrivaldate.split("/")[1].trim();
		String arr_yearval = arrivaldate.split("/")[2].trim();

		int dep_monthNumber = Integer.parseInt(dep_monthvalue);
		dep_monthvalue = Month.of(dep_monthNumber).name().substring(0, 3);

		int arr_monthNumber = Integer.parseInt(arr_monthval);
		arr_monthval = Month.of(arr_monthNumber).name().substring(0, 3);
		date.add(arr_dateval);
		date.add(dep_monthvalue.toLowerCase());
		return date;
		
	}
public static void main(String[] args) {
	getDateInRequiredTextformat("0");
}
}
