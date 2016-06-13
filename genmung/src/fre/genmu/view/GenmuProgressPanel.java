package fre.genmu.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import fre.genmu.ressources.Ressources;

public class GenmuProgressPanel extends JPanel implements
		GenmuScreenObservateur {

	private JLabel jh;
	private JLabel endLine = new JLabel(Ressources.getInstance().getAppliImage("penguin.png"));
	private JProgressBar progress;
	private JCheckBox checkbox;
	int value = 0;
	private String target;
	
	/**
	 * Constructeur
	 */
	public GenmuProgressPanel() {
		super();
	}

	/**
	 * Constructeur
	 */
	public GenmuProgressPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	/**
	 * Constructeur
	 */
	public GenmuProgressPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	/**
	 * Constructeur
	 */
	public GenmuProgressPanel(LayoutManager layout) {
		super(layout);
	}
	
	/**
	 * Init du panneau
	 * @param type_
	 * @param progress_
	 */
	public void initProgressPane(JLabel type_, JProgressBar progress_){
		
		jh = type_;
		progress = progress_;
		
		progress.setValue(0);
		progress.setMaximum(100);
		progress.setStringPainted(true);
		progress.setPreferredSize(new Dimension(350, 20));
		progress.setMinimumSize(new Dimension(150, 20));
		progress.setFont(new Font("Arial", Font.PLAIN, 10));
		
		checkbox = new JCheckBox();
		checkbox.setBackground(Color.WHITE);
		checkbox.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		setLayout(new GridBagLayout());
		GridBagConstraints jhenGbc = new GridBagConstraints();
		
		jhenGbc.gridy = 0;
		jhenGbc.gridx = 0;
		jhenGbc.insets = new Insets(1, 0, 1, 5);
		this.add(checkbox, jhenGbc);
		jhenGbc.gridy = 0;
		jhenGbc.gridx = 1;
		jhenGbc.insets = new Insets(1, 0, 1, 5);
		this.add(jh, jhenGbc);
		jhenGbc.gridy = 0;
		jhenGbc.gridx = 2;
		jhenGbc.insets = new Insets(1, 5, 1, 5);
		this.add(progress, jhenGbc);
		jhenGbc.gridy = 0;
		jhenGbc.gridx = 3;
		jhenGbc.insets = new Insets(1, 5, 1, 0);
		this.add(endLine, jhenGbc);
		
		setBackground(Color.WHITE);
		
		
		
	}


	public JLabel getJh() {
		return jh;
	}


	public void setJh(JLabel jh) {
		this.jh = jh;
	}


	public JProgressBar getProgress() {
		return progress;
	}


	public void setProgress(JProgressBar progress) {
		this.progress = progress;
	}
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}
	
	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target_) {
		target = target_;
	}
	
	public void startProgressBar(){
	}


	@Override
	public void actualiser() {
		
	}
	
	/**
	 * Rend tout non editable.
	 * @param bool_
	 */
	public void setEnabledPanel(boolean bool_){
		checkbox.setSelected(false);
		checkbox.setEnabled(bool_);
		jh.setEnabled(bool_);
		progress.setEnabled(bool_);
		endLine.setEnabled(bool_);
	}
	
	/**
	 * Remet les panneaux dans leur conf d'origine.
	 */
	public void resetPanel(){
		
		checkbox.setSelected(false);
		checkbox.setEnabled(true);
		
		jh.setEnabled(true);
		
		progress.setValue(0);
		progress.setMaximum(100);
		progress.setStringPainted(true);
		progress.setPreferredSize(new Dimension(350, 20));
		progress.setMinimumSize(new Dimension(150, 20));
		progress.setFont(new Font("Arial", Font.PLAIN, 10));
		progress.setEnabled(true);
		
		endLine.setIcon(Ressources.getInstance().getAppliImage("penguin.png"));
		endLine.setEnabled(true);
		
	}
	
	/**
	 * Modifie le panneau en mode OK
	 */
	public void updateOKStatus(){
		endLine.setIcon(Ressources.getInstance().getAppliImage("ok.png"));
		progress.setBorder(BorderFactory.createLineBorder(Color.decode("#298A08")));
		progress.setForeground(Color.decode("#298A08"));
		repaint();
	}
	
	/**
	 * Modifie le panneau en mode KO
	 */
	public void updateKOStatus(){
		endLine.setIcon(Ressources.getInstance().getAppliImage("ko.png"));
		progress.setBorder(BorderFactory.createLineBorder(Color.decode("#B40431")));
		progress.setForeground(Color.decode("#B40431"));
		repaint();
	}

}
