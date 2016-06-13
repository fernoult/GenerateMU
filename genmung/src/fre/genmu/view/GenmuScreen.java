package fre.genmu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import fre.genmu.handler.runnables.MUHandlerAction;
import fre.genmu.ressources.Ressources;

/**
 * La vue principale de l'appli
 * @author Utilisateur 1
 *
 */
public class GenmuScreen extends JFrame implements GenmuScreenObservateur{


	// Declaration des attributs
	private JLabel launchAll;
	private JButton startAll;
	private JButton refresh;
	private JTextField tfsearch;
	private JButton search;
	private JLabel lblSearch;
	private JLabel lblicone;
	private JLabel lblpath;
	private JPanel zePanel;
	private GenmuProgressPanel jhenPane;
	private GenmuProgressPanel jhfrPane;
	private GenmuProgressPanel muenPane;
	private GenmuProgressPanel mufrPane;
	private JPanel northPane;
	private  JPanel eastPane;
	private String path;
	
	/**
	 * Constructeur prive.
	 */
	public GenmuScreen(){
		
		// init de la fenetre.
		initGenmuScreen();
	}
	
	/**
	 * Initialisation de la fenetre.
	 */
	private void initGenmuScreen(){
		
		// On positionne le Layout principal.
		setLayout(new BorderLayout());
		
		// On init les composants.
		initComposants();
		initPanels();
		enabledGenePane(true);
		
		// Ajout des composants a la vue.
		add(zePanel, BorderLayout.CENTER);
		add(northPane, BorderLayout.NORTH);
		add(eastPane, BorderLayout.SOUTH);
		
		// Config de la vue.
		setSize(450, 230);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Init des composants.
	 */
	private void initComposants(){
		
		// Les labels qui vont bien
		lblicone = new JLabel(Ressources.getInstance().getAppliImage("ant.png"));
		lblSearch = new JLabel("Cible ant : ");
		lblpath = new JLabel();
		tfsearch = new JTextField();
		tfsearch.setMinimumSize(new Dimension(200, 20));
		tfsearch.setPreferredSize(new Dimension(200, 20));
		tfsearch.setFont(new Font("Tahoma", Font.BOLD, 10));
		tfsearch.setForeground(Color.BLUE);

		// On declare le thread de traitement.
		final Thread thread = new Thread(new MUHandlerAction(this));
		
		// Les boutons.
		startAll = new JButton(Ressources.getInstance().getAppliImage("generate.png"));
		startAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startAll.setEnabled(false);
				if (thread.isAlive()) {
					thread.interrupt();
				}
				thread.start();					
			}
		});
		refresh = new JButton(Ressources.getInstance().getAppliImage("refresh.png"));
		refresh.setEnabled(false);
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jhenPane.setEnabledPanel(true);
				jhenPane.resetPanel();
				
				jhfrPane.setEnabledPanel(true);
				jhfrPane.resetPanel();
				
				muenPane.setEnabledPanel(true);
				muenPane.resetPanel();
				
				mufrPane.setEnabledPanel(true);
				mufrPane.resetPanel();
				
				refresh.setEnabled(false);
				startAll.setEnabled(true);
			}
		});
		
		search = new JButton(Ressources.getInstance().getAppliImage("search.png"));
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initFileChooser();
				
			}
		});
		
	}
	
	/**
	 * Init des panneaux
	 */
	private void initPanels(){
		
		// Le panneau principal
		zePanel = new JPanel(new GridLayout(4, 1));
		
		// Les sous-panneaux.
		/* ===== JavaHelp Anglais ===== */
		jhenPane = new GenmuProgressPanel();
		jhenPane.initProgressPane(new JLabel(Ressources.getInstance().getAppliImage("java.png")), 
				new JProgressBar(0, 50));
		jhenPane.getJh().setToolTipText(Ressources.getInstance().getRessourcesLabel("generation.EN.javahelp.icone.tooltype"));
		jhenPane.getProgress().setString(Ressources.getInstance().getRessourcesLabel("generation.EN.javahelp.progress.text"));
		jhenPane.setTarget(Ressources.getInstance().getRessourcesLabel("BuildXML.target.JavaHelpEN.text"));
		
		/* ===== JavaHelp Francais ===== */
		jhfrPane = new GenmuProgressPanel();
		jhfrPane.initProgressPane(new JLabel(Ressources.getInstance().getAppliImage("java.png")), 
				new JProgressBar(0, 50));
		jhfrPane.getJh().setToolTipText(Ressources.getInstance().getRessourcesLabel("generation.FR.javahelp.icone.tooltype"));
		jhfrPane.getProgress().setString(Ressources.getInstance().getRessourcesLabel("generation.FR.javahelp.progress.text"));
		jhfrPane.setTarget(Ressources.getInstance().getRessourcesLabel("BuildXML.target.JavaHelpFR.text"));
		
		/* ===== MU Anglais ===== */
		muenPane = new GenmuProgressPanel();
		muenPane.initProgressPane(new JLabel(Ressources.getInstance().getAppliImage("mu.png")), 
				new JProgressBar(0, 50));
		muenPane.getJh().setToolTipText(Ressources.getInstance().getRessourcesLabel("generation.EN.ManuelUtilisateur.icone.tooltype"));
		muenPane.getProgress().setString(Ressources.getInstance().getRessourcesLabel("generation.EN.ManuelUtilisateur.progress.text"));
		muenPane.setTarget(Ressources.getInstance().getRessourcesLabel("BuildXML.target.PdfEN.text"));
		
		/* ===== MU Francais ===== */
		mufrPane = new GenmuProgressPanel();
		mufrPane.initProgressPane(new JLabel(Ressources.getInstance().getAppliImage("mu.png")), 
				new JProgressBar(0, 50));
		mufrPane.getJh().setToolTipText(Ressources.getInstance().getRessourcesLabel("generation.FR.ManuelUtilisateur.icone.tooltype"));
		mufrPane.getProgress().setString(Ressources.getInstance().getRessourcesLabel("generation.FR.ManuelUtilisateur.progress.text"));
		mufrPane.setTarget(Ressources.getInstance().getRessourcesLabel("BuildXML.target.PdfFR.text"));
		
		// Config des panneaux
		zePanel.add(jhenPane);
		zePanel.add(jhfrPane);
		zePanel.add(muenPane);
		zePanel.add(mufrPane);

		northPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPane.setBackground(Color.WHITE);
		northPane.add(lblicone);
		northPane.add(lblSearch);
		northPane.add(tfsearch);
		northPane.add(search);
		
		eastPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		eastPane.setBackground(Color.WHITE);
		eastPane.add(startAll);
		eastPane.add(refresh);
	}

	private void initFileChooser(){
		
		// On declenche le FileChooser.
		String fileChooserPath = "";
		if (fileChooserPath.equals("")) {
			fileChooserPath = System.getProperty("user.dir");
		}
		JFileChooser jfc = new JFileChooser(fileChooserPath);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setFileFilter(filter);
		
		if (jfc.showOpenDialog(jfc) == 0) {
			// On recupere le path d'installation.
			path = jfc.getSelectedFile().getAbsolutePath();
			tfsearch.setText(path);
			
		}
	}
	
	public JLabel getLaunchAll() {
		return launchAll;
	}

	public void setLaunchAll(JLabel launchAll) {
		this.launchAll = launchAll;
	}

	public JButton getStartAll() {
		return startAll;
	}

	public void setStartAll(JButton startAll) {
		this.startAll = startAll;
	}

	public JPanel getZePanel() {
		return zePanel;
	}

	public void setZePanel(JPanel zePanel) {
		this.zePanel = zePanel;
	}

	public GenmuProgressPanel getJhenPane() {
		return jhenPane;
	}

	public void setJhenPane(GenmuProgressPanel jhenPane) {
		this.jhenPane = jhenPane;
	}

	public GenmuProgressPanel getJhfrPane() {
		return jhfrPane;
	}

	public void setJhfrPane(GenmuProgressPanel jhfrPane) {
		this.jhfrPane = jhfrPane;
	}

	public GenmuProgressPanel getMuenPane() {
		return muenPane;
	}

	public void setMuenPane(GenmuProgressPanel muenPane) {
		this.muenPane = muenPane;
	}

	public GenmuProgressPanel getMufrPane() {
		return mufrPane;
	}

	public void setMufrPane(GenmuProgressPanel mufrPane) {
		this.mufrPane = mufrPane;
	}

	public JPanel getNorthPane() {
		return northPane;
	}

	public void setNorthPane(JPanel northPane) {
		this.northPane = northPane;
	}
	
	public JButton getRefresh() {
		return refresh;
	}
	
	public void setRefresh(JButton refresh) {
		this.refresh = refresh;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	private void enabledGenePane(boolean enabled_){
		
	}


	@Override
	public void actualiser() {
		
	}
	
	
	
}
