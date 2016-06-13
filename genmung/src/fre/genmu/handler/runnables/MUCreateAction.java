package fre.genmu.handler.runnables;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import fre.genmu.ressources.Ressources;
import fre.genmu.view.GenmuProgressPanel;

public class MUCreateAction implements Runnable {

	private String ZeBuildPath;
	private GenmuProgressPanel _panel;
	private String _message;
	
	/**
	 * Constructeur.
	 */
	public MUCreateAction(GenmuProgressPanel panel_, String buildPath_){
		_panel = panel_;
		ZeBuildPath = buildPath_;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		File buildFile = new File(ZeBuildPath);
		
		if (buildFile.exists()) {
			
			try {
				
				runProgressBar(0, _panel.getProgress(), true);
				Project projet = new Project();
				projet.setUserProperty("ant.file", buildFile.getAbsolutePath());
				projet.init();
				ProjectHelper projetHelper = ProjectHelper.getProjectHelper();
				
				projet.addReference("ant.projectHelper", projetHelper);
				projetHelper.parse(projet, buildFile);
				projet.executeTarget(_panel.getTarget());
				
				upadatePanel(_panel, "OK", true);
				runProgressBar(100, _panel.getProgress(), false);
				// OK Action

				
			} catch (Exception e) {
				e.printStackTrace();
				_message = "Erreur execution du \"build.xml\" " + e.getMessage();
				upadatePanel(_panel, "KO", true);
			}

			
		}else {
			_message = "Le repertoire n'existe pas!";
			upadatePanel(_panel, "KO", true);
		}
		
	}
	
	private void runProgressBar(final int prog, final JProgressBar progress, final boolean interm){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if (prog == 0) {
					progress.setIndeterminate(interm);					
				}else if (prog == 100) {
					progress.setIndeterminate(interm);	
					progress.setValue(prog);
					
				}
				
			}
		});
	}
	
	private void upadatePanel(final GenmuProgressPanel panel, String status, final boolean enabled){
		
		
		if (status != null) {
			if (status.equals("OK")) {
				panel.updateOKStatus();				
			}else if (status.equals("KO")) {
				panel.getProgress().setIndeterminate(false);
				panel.getProgress().setValue(100);
				panel.getProgress().setString(_message);
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

}
