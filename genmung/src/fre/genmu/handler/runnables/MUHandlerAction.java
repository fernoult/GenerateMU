package fre.genmu.handler.runnables;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import fre.genmu.view.GenmuProgressPanel;
import fre.genmu.view.GenmuScreen;

public class MUHandlerAction implements Runnable {

	private GenmuScreen _screen;
	
	
	/** Le thread pdf en */
	private Thread _threadPdfEN;
	
	/** Le thread pdf fr */
	private Thread _threadPdfFR;
	
	/** Le thread JavaHelp en */
	private Thread _threadJavaHelpEN;
	
	/** Le thread JavaHelp fr */
	private Thread _threadJavaHelpFR;
	
	public MUHandlerAction(GenmuScreen screen_){
		
		_screen = screen_;
	}
	
	@Override
	public void run(){

		// Declaration de tous les threads.
		_threadPdfFR = new Thread(new MUCreateAction(_screen.getMufrPane(), _screen.getPath()));
		
		_threadPdfEN = new Thread(new MUCreateAction(_screen.getMuenPane(), _screen.getPath()));
		
		_threadJavaHelpEN = new Thread(new MUCreateAction(_screen.getJhenPane(), _screen.getPath()));
		
		_threadJavaHelpFR = new Thread(new MUCreateAction(_screen.getJhfrPane(), _screen.getPath()));		
		
		ArrayList<GenmuProgressPanel> liste = new ArrayList<GenmuProgressPanel>();
		
		// Declenchement des threads les uns apres les autres.
		if (!_screen.getJhenPane().getCheckbox().isSelected()) {
			upadatePanel(_screen.getJhenPane(), null, false);
			liste.add(_screen.getJhenPane());
		}

		if (!_screen.getJhfrPane().getCheckbox().isSelected()) {
			upadatePanel(_screen.getJhfrPane(), null, false);
			liste.add(_screen.getJhfrPane());
		}

		if (!_screen.getMuenPane().getCheckbox().isSelected()) {
			upadatePanel(_screen.getMuenPane(), null, false);
			liste.add(_screen.getMuenPane());
		}

		if (!_screen.getMufrPane().getCheckbox().isSelected()) {
			upadatePanel(_screen.getMufrPane(), null, false);
			liste.add(_screen.getMufrPane());

		}
		
		if (_screen.getJhenPane().getCheckbox().isSelected()) {
			runJhenGene();
		}
		
		if (_screen.getJhfrPane().getCheckbox().isSelected()) {
			runJhfrGene();
		}
		
		if (_screen.getMuenPane().getCheckbox().isSelected()) {
			runMuenGene();
		}
		
		if (_screen.getMufrPane().getCheckbox().isSelected()) {
			runMufrGene();
		}
		
		activeRefreshPanes();
	}
	
	private void runJhenGene(){
		try {
			_threadJavaHelpEN.start();
			_threadJavaHelpEN.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void runJhfrGene(){
		try {
			_threadJavaHelpFR.start();
			_threadJavaHelpFR.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void runMuenGene(){
		try {
			_threadPdfEN.start();
			_threadPdfEN.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void runMufrGene(){
		try {
			_threadPdfFR.start();
			_threadPdfFR.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void upadatePanel(final GenmuProgressPanel panel, String status, final boolean enabled){
		
		
		if (status != null) {
			if (status.equals("OK")) {
				panel.updateOKStatus();				
			}else if (status.equals("KO")) {
				panel.getProgress().setIndeterminate(false);
				panel.getProgress().setValue(100);
				panel.updateKOStatus();
			}
		}else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					panel.setEnabledPanel(enabled);					
				}
			});
		}
	}
	
	private void activeRefreshPanes(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_screen.getRefresh().setEnabled(true);
				
			}
		});
	}
	

	


}
