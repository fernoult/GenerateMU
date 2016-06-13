package fre.genmu.utils;

import java.util.ResourceBundle;

public class UtilsManager {

	/** Instance unique de UtilsManager */
	private static UtilsManager UTILS_INSTANCE;
	
	/** Chemin du package */
	private static String UTILS_PACKAGE = UtilsManager.class.getPackage().getName();
	
	/** Chemin du properties utils */
	private static String DATES_UTILS_PROPERTIES = UTILS_PACKAGE.replace("/", ".") + ".DateUtil";
	
	/** Bundle des dates. */
	private ResourceBundle DATE_BUNDLE = ResourceBundle.getBundle(DATES_UTILS_PROPERTIES);
	
	/**
	 * Constructeur prive.
	 */
	private UtilsManager(){
		
	}
	
	/**
	 * Retourne l'instance de UtilsManager.
	 * @return
	 */
	public static UtilsManager getInstance(){
		
		// Le singleton qui va bien.
		if (UTILS_INSTANCE == null) {
			UTILS_INSTANCE = new UtilsManager();
		}
		
		return UTILS_INSTANCE;
	}
	
	/**
	 * Retourne le label correspondant a la cle.
	 * @param key_
	 * @return
	 */
	public String getDateLabel(String key_){
		
		return DATE_BUNDLE.getString(key_);
	}
}
