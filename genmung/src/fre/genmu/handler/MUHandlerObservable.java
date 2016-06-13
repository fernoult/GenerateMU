package fre.genmu.handler;

import fre.genmu.view.GenmuScreenObservateur;

public interface MUHandlerObservable {

	public void addObservateur(GenmuScreenObservateur observateur, String key_);
	
	public void removeObservateur(GenmuScreenObservateur observateur);
	
	public void notifieObservateur(GenmuScreenObservateur o);
}
