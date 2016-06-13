package fre.genmu.utils;

import java.util.Date;

import fre.genmu.utils.utilsEnums.DateTypeEnum;

public class DateUtil {


	/**
	 * Formatte une date en chaine de string/
	 * Le type permet d'utiliser le pattern de la date 
	 * ou de l'heure selon ce que l'on veut.
	 * @param date_
	 * @return
	 */
	public static String format(Date date_, DateTypeEnum type_){
		return type_.format(date_);
	}
	
	/**
	 * Parse une string en date.
	 * @param date_
	 * @return
	 */
	public static Date parse(String date_, DateTypeEnum type_){
		return type_.parse(date_);
	}
	
	/**
	 * 
	 * @param date_
	 * @return
	 */
	public static boolean validDate(String date_){
		return DateTypeEnum.DATE.parse(date_) != null;
	}
	
	/**
	 * 
	 * @param date_
	 * @return
	 */
	public static boolean validTime(String time_){
		return DateTypeEnum.TIME.parse(time_) != null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCurrentTime(){
		return DateTypeEnum.TIME.format(new Date());
	}
	
	public static String getFullDate(){
		
		// Init de la date courante.
		Date currentDate = new Date();
		return format(currentDate, DateTypeEnum.DATE).trim() + " - " + format(currentDate, DateTypeEnum.TIME).trim();
	}
	
	
}
