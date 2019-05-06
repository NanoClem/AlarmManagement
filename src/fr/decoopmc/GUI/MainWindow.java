package fr.decoopmc.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.decoopmc.captors.*;
import fr.decoopmc.events.*;
import fr.decoopmc.responders.Monitor;


/**
 * Fenêtre principale de l'application.
 * Contient les différents éléments d'affichage de l'information
 * des alarmes déclenchées, et une option de simulation d'alarme
 * 
 * @author decoopmc
 * @version 1.0
 * @see javax.swing.JFrame;
 */
public class MainWindow extends JFrame
                        implements ActionListener, ListSelectionListener {
  /**
   * Taile de l'écran
   */
  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  /**
   * Taille de la fenêtre
   */
  private Dimension frameSize = new Dimension(screenSize.width*2/3, screenSize.height*2/3);

  /**
   * Liste graphique des alarmes déclenchées
   */
  private JList<String> graphicEventList = new JList<String>();

  /**
   * Modèle de la liste des envents.
   * On passe par cet élément pour l'insertion dans la liste graphique
   */
  private DefaultListModel<String> eventListModel = new DefaultListModel<String>();

  /**
   * Liste des alarmes sous forme d'event
   */
  private ArrayList<AnomalyEvent> eventList = new ArrayList<>();

  /**
   * Panneau d'affichage des détails d'une alarme déclenchée
   */
  private JButton details = new JButton("Details");

  /**
   * Archive une alarme dans la liste
   */
  private JButton archive = new JButton("Archiver");

  /**
   * Panneau d'affichage des informations de l'alarme courrante
   */
  private JLabel infosDisplayer = new JLabel();

  /**
   * Liste des moniteurs venant écouter les différentes alarmes déclenchées
   */
  private ArrayList<Monitor> monitorList = new ArrayList<Monitor>();


  /**
   *<b>CONSTRUCTEUR DE CLASSE MainWindow</b>
   * @param title : titre de la fenêtre
   * @throws Exception
   */
  public MainWindow(String title) throws Exception
  {
    super(title);
    setSize(frameSize);
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setAlwaysOnTop(false);
    this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

    /*----------------------------------
        BARRE DE MENU
    ----------------------------------*/
    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    // ELEMENTS DU MENU
    JMenu simu = new JMenu("Simu Alarm");
    menuBar.add(simu);

    // SOUS MENU
    JMenuItem launch = new JMenuItem("Simuler une alarme");
    simu.add(launch);
    simu.add(new JSeparator());                          // ligne séparatrice
    JMenuItem quit = new JMenuItem("Quitter");
    simu.add(quit);

    /*----------------------------------
        CONTENU
    ----------------------------------*/
    this.initContent();

    /*----------------------------------
      ACTIONS DE CLICS
    ----------------------------------*/
    launch.setActionCommand("launch");
    launch.addActionListener(this);
    quit.setActionCommand("quit");
    quit.addActionListener(this);
    

    this.pack();
    setVisible(true);
  }


  /*========================================================
                      CONTENU GRAPHIQUE
  ========================================================*/

  /**
   * Construit le contenu graphique
   */
  public void initContent()
  {
    /*----------------------------------
        SOUS PANELS
    -----------------------------------*/
    JPanel left = new JPanel();
    left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
    JPanel right = new JPanel();

    /*----------------------------------
        LISTE DES EVENTS
    ----------------------------------*/
    JPanel listPanel = new JPanel();
    this.graphicEventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.graphicEventList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    this.graphicEventList.addListSelectionListener(this);
    this.graphicEventList.setModel(eventListModel);
    //JScrollPane scroller = new JScrollPane(graphicEventList);
    listPanel.add(this.graphicEventList);

    /*----------------------------------
        BOUTONS
    ----------------------------------*/
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(details);
    buttonPanel.add(archive);
    

    /*----------------------------------
        PANNEAU AFFICHAGE DES INFOS
    ----------------------------------*/
    JPanel displayPanel = new JPanel();
    displayPanel.add(this.infosDisplayer);

    /*----------------------------------
        LEFT PANEL
    ----------------------------------*/
    left.add(listPanel);
    left.add(buttonPanel);

    /*----------------------------------
        RIGHT PANEL
    ----------------------------------*/
    right.add(displayPanel);

    /*----------------------------------
        CONTENEUR PRINCIPAL
    ----------------------------------*/
    this.getContentPane().add(left);
    this.getContentPane().add(right);
  }


  /*========================================================
            OPERATIONS SUR LES MONITEURS
  ========================================================*/

  /**
   * Ajout d'un moniteur à la liste
   * 
   * @param m : Moniteur à ajouter
   * @see Monitor
   */
  public void addMonitor(Monitor m) {
    this.monitorList.add(m);
  }

  /**
   * Suppression d'un moniteur de la liste
   * 
   * @param m : Moniteur à supprimer
   */
  public void removeMonitor(Monitor m) {
    this.monitorList.remove(m);
  }


/*========================================================
                  EVENTS ET ALARMES
  ========================================================*/

  /**
   * Indique les action à effectuer lorsqu'un élément
   * est sélectionné dans la liste
   * 
   * @param e : objet event de sélection dans la liste
   */
  public void valueChanged(ListSelectionEvent e)
  {
    if(e.getValueIsAdjusting() == true) 
    {
      //AUCUN INDEX SELECTIONNNE
      if(graphicEventList.getSelectedIndex() == -1 || this.eventListModel.size() == 0) 
      {
        this.infosDisplayer.setText("");   // nettoyage du panneau d'affichage
        this.details.setEnabled(false);    // desactivation des boutons
        this.archive.setEnabled(false);
        //this.archive.setDisabledIcon(disabledIcon);   // icone indiquant l'etat desactive
      }
      //AFFICHAGE DE SES INFORMATIONS DANS LE PANNEAU
      else {
        String message = this.eventList.get(graphicEventList.getSelectedIndex()).getInformations();   // informations de l'evenement
        this.infosDisplayer.setText("");
        this.infosDisplayer.setText(message);   // affichage des informations
        this.details.setEnabled(true);          // activation des boutons
        this.details.setEnabled(true);
      }
    }
  }


  /**
   * Actions effectuées lorsqu'une alarme incendie est déclenchée
   * depuis la fenêtre de simulation
   * 
   * @param fire
   * @param critLevel
   * @param date
   */
  public void alarmLaunched(FireCaptor fire, int critLevel, String date) 
  {
    Iterator<Monitor> it = this.monitorList.iterator();    // iterateur de la liste des moniteurs
    while(it.hasNext()) {
      Monitor tmp = it.next();
      if(tmp.getType() == "A") {      // si le type du moniteur correspond
        fire.addListener(tmp);        // on l'ajoute en ecoute du capteur de simulation
      }
    }
    String message = new String("Fire alarm Level " + critLevel + ", " +  fire.getLocation());      // message a afficher dans la liste graphique
    this.eventListModel.addElement(message);                                                        // ajout de l'élément au model
    this.eventList.add(fire.generateAnomalyEvent(critLevel, date));                                 // generation de l'alarme et ajout à la liste des events
  }


  /**
   * Actions effectuées lorsqu'une alarme anti-gaz est déclenchée
   * 
   * @param gaz
   * @param critLevel
   * @param type
   * @param date
   */
  public void alarmLaunched(GazCaptor gaz, int critLevel, String type, String date) 
  {
    Iterator<Monitor> it = this.monitorList.iterator();
    while(it.hasNext()) {
      Monitor tmp = it.next();
      if(tmp.getType() == "A" || tmp.getType() == "B") {
        gaz.addListener(tmp);
      }
    }
    String message = new String("Gaz alarm Level " + critLevel + ", " +  gaz.getLocation());
    this.eventListModel.addElement(message);
    this.eventList.add(gaz.generateAnomalyEvent(critLevel, date, type));   
  }


  /**
   * Actions effectuées lorsqu'une alarme anti-radiation est déclenchée
   * 
   * @param rad
   * @param critLevel
   * @param radLevel
   * @param date
   */
  public void alarmLaunched(RadiationCaptor rad, int critLevel, int radLevel, String date) 
  {
    Iterator<Monitor> it = this.monitorList.iterator();   
    while(it.hasNext()) {
      Monitor tmp = it.next();
      if(tmp.getType() == "B") {                          
        rad.addListener(tmp);
      }
    }
    String message = new String("Radiation alarm Level " + critLevel + ", " + rad.getLocation());
    this.eventListModel.addElement(message);
    this.eventList.add(rad.generateAnomalyEvent(critLevel, date, radLevel));
  }


/**
 * Indique les actions a effectuer au clic d'un element du menu
 * 
 * @param event : événement déclencheur d'une action
 */
  public void actionPerformed(ActionEvent event)
  {
    // CLIC SUR SOUS MENU "launch"
    if(event.getActionCommand().equals("launch"))
    {
      new SimulationFrame(this, new Dimension(frameSize.width/4, frameSize.height/2), "Alarm Simulator");
    }

    // CLIC SUR "Quitter"
    if (event.getActionCommand().equals("quit"))
    {
      JOptionPane pane = new JOptionPane();
      if ( pane.showConfirmDialog(this,                            // on demande une confirmation en rapport
                "Voulez vous vraiment quitter ?",                  // avec la fenêtre qui a déclenché l'action (this)
                "Attention",
                pane.YES_NO_OPTION,                                // option YES / NO
                pane.WARNING_MESSAGE) == JOptionPane.YES_OPTION )  // si YES
        System.exit(0);                                            // on quitte le programme
    }
  }
}
