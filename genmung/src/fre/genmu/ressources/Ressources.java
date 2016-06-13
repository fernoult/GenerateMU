package fre.genmu.ressources;

import java.util.ResourceBundle;

import javax.swing.ImageIcon;

public class Ressources {

	/** Instance unique de Ressources */
	private static Ressources RESSOURCES_INSTANCE;
	
	private static String RESSOURCES_PACKAGE = Ressources.class.getPackage().getName();
	
	private static String RESSOURCES_PROPERTIES = RESSOURCES_PACKAGE.replace("/", ".") + ".Ressources";
	
	private static String IMG_PATH = Ressources.class.getResource("./images").getFile();
	
	private ResourceBundle RESSOURCES_BUNDLE = ResourceBundle.getBundle(RESSOURCES_PROPERTIES);
	
	/***
	 * Constructeur
	 */
	private Ressources(){
		
	}
	
	/**
	 * Retourne l'instance unique les Ressources.
	 * @return
	 */
	public static Ressources getInstance(){
		// Le singleton qui va bien.
		if (RESSOURCES_INSTANCE == null) {
			RESSOURCES_INSTANCE = new Ressources();
		}
		return RESSOURCES_INSTANCE;
	}
	
	/**
	 * Retourne le libelle en fonction de sa cle.
	 * @param key_
	 * @return
	 */
	public String getRessourcesLabel(String key_){
		return RESSOURCES_BUNDLE.getString(key_);
	}
	
	/**
	 * Retourne une image en fonction de sa cle.
	 * @param key_
	 * @return
	 */
	public ImageIcon getAppliImage(String key_){
		ImageIcon image = new ImageIcon(IMG_PATH + "/" + key_);
		return image;
	}
}
