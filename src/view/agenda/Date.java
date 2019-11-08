package view.agenda;

import java.util.Calendar;
import java.util.GregorianCalendar;
 
public class Date {
	public int year;

	int month;

	int day;
 
	public Calendar calendar=new GregorianCalendar();
 
	public Date(){
	    year=calendar.get(Calendar.YEAR);
	    month=calendar.get(Calendar.MONTH);
	    day=calendar.get(Calendar.DAY_OF_MONTH);
	}
 
	Date(int year,int month,int day){
		this.year=year;
		this.month=month;
		this.day=day;
	}
 
	public int firstDayOfTheMonth(){
 
		return new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK);
 
	}
 
	public int getYear(){
		return year;
	}
	public int getMonth(){
		return month;
	}
	public int getDay(){
		return day;
	}
	public void setYear(int year){
		this.year=year;
	}
	public void setMonth(int month){
		this.month=month;
	}void setDay(int day){
		this.day=day;
	}
}