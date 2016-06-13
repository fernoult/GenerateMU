package fre.genmu;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fre.genmu.view.GenmuScreen;

/**
 * Le point d'entree de l'appli.
 * @author Utilisateur 1
 *
 */
public class GMAccess {

	/**
	 * Le main qui va bien.
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			
			// On positione le LAndFImpl par defaut.
			UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Ouvre l'outil.
		new GenmuScreen();			

	}

}
