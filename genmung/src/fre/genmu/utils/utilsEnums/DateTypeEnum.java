package fre.genmu.utils.utilsEnums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fre.genmu.utils.UtilsManager;

/**
 * Enumere du type de date a traiter (Date, Heure).
 * @author Utilisateur 1
 *
 */
public enum DateTypeEnum {

	TIME("TIME"),
	DATE("DATE");
	
	/** Le pattern pour la date */
	private static final String DATE_PATTERN = UtilsManager.getInstance().getDateLabel("DateUtil.date.pattern.text");
	
	/** Le pattern por l'heure */
	private static final String TIME_PATTERN = UtilsManager.getInstance().getDateLabel("DateUtil.time.pattern.text");
	
	/** Le formatteur pour la date */
	private static SimpleDateFormat SDF_DATE = new SimpleDateFormat(DATE_PATTERN);
	
	/** Le formatteur pour l'heure */
	private static SimpleDateFormat SDF_TIME = new SimpleDateFormat(TIME_PATTERN);
	
	/** Type du DateType */
	private String TYPE = "";
	
	/**
	 * Constructeur.
	 * @param type_
	 */
	private DateTypeEnum(String type_) {
		TYPE = type_;
	}
	
	/**
	 * Retourne une chaine de String a partir 
	 * de la Date() passee en parametre.
	 * @param date_
	 * @return
	 */
	public String format(Date date_){
		
		String date = null;
		
		// Si c'est pour une date.
		if (TYPE.equals("DATE")) {
			date = SDF_DATE.format(date_);			
		
		// Sinon c'est pour une heure.
		}else if(TYPE.equals("TIME")) {
			date = SDF_TIME.format(date_);
		}		
		return date;
	}
	
	/**
	 * Retourne une Date() a partir d'une chaine
	 * de String passee en parametre.
	 * @param date_
	 * @return
	 */
	public Date parse(String date_){
		
		Date date = null;
		
		try {
			// Si c'est une date
			if (TYPE.equals("DATE")) {
				date = SDF_DATE.parse(date_);
				
			// Sinon c'est une heure
			}else if (TYPE.equals("TIME")) {
				date = SDF_TIME.parse(date_);
			}
			
		} catch (ParseException e) {
			return null;
		}

		
		return date;
	}
}
