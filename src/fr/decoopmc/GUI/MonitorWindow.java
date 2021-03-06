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
public class MonitorWindow extends JFrame
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
   * Liste graphique des alarmes déclenchées.
   */
  private JList<String> graphicEventList = new JList<String>();

  /**
   * Modèle de la liste des events.
   * On passe par cet élément pour l'insertion dans la liste graphique
   */
  private DefaultListModel<String> eventListModel = new DefaultListModel<String>();

  /**
   * Liste des alarmes sous forme d'objet Event
   */
  private ArrayList<AnomalyEvent> eventList = new ArrayList<>();

  /**
   * Archive une alarme dans la liste
   */
  private JButton archive = new JButton("Archiver");

  /**
   * Panneau d'affichage des informations de l'alarme courrante
   */
  private JTextPane infosDisplayer = new JTextPane();

  /**
   * Liste des moniteurs venant écouter les différentes alarmes déclenchées
   */
  private ArrayList<Monitor> monitorList = new ArrayList<Monitor>();


  /**
   *<b>CONSTRUCTEUR DE CLASSE MainWindow</b>
   * @param title : titre de la fenêtre
   * @throws Exception
   */
  public MonitorWindow(String title) throws Exception
  {
    super(title);
    setSize(frameSize);
    setResizable(false);
    setLocationRelativeTo(null);
    setAlwaysOnTop(false);
    this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

    /*----------------------------------
        CONTENU
    ----------------------------------*/
    this.initContent();


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
    //LISTE GRAPHIQUE
    this.graphicEventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.graphicEventList.setLayoutOrientation(JList.VERTICAL);
    this.graphicEventList.addListSelectionListener(this);
    this.graphicEventList.setModel(eventListModel);
    //SCROLLER
    JScrollPane scroller = new JScrollPane(graphicEventList);
    scroller.setPreferredSize(new Dimension(350, 180));

    listPanel.add(scroller);

    /*----------------------------------
        BOUTONS
    ----------------------------------*/
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(archive);
    this.archive.setEnabled(false);
    //ACTION ARCHIVAGE
    this.archive.setActionCommand("archive");
    this.archive.addActionListener(this);


    /*----------------------------------
        PANNEAU AFFICHAGE DES INFOS
    ----------------------------------*/
    JPanel displayPanel = new JPanel();
    this.infosDisplayer.setPreferredSize(new Dimension(350, 180));
    this.infosDisplayer.setContentType("text/html");
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
    if(e.getValueIsAdjusting() == false) 
    {
      //AUCUN INDEX SELECTIONNNE
      if(graphicEventList.getSelectedIndex() == -1 || this.eventListModel.size() == 0) 
      {
        this.infosDisplayer.setText("");   // nettoyage du panneau d'affichage
        this.archive.setEnabled(false);    // desactivation des boutons
      }
      //AFFICHAGE DE SES INFORMATIONS DANS LE PANNEAU
      else {
        String message = this.eventList.get(graphicEventList.getSelectedIndex()).getInformations();   // informations de l'evenement
        this.infosDisplayer.setText("");
        this.infosDisplayer.setText(message);   // affichage des informations       
        this.archive.setEnabled(true);          // activation des boutons
      }
    }
  }


  /**
   * <b>Actions effectuées lorsqu'une alarme incendie est déclenchée</b>
   * 
   * @param fire
   * @param critLevel
   * @param date
   */
  public void alarmLaunched(FireCaptor fire, int critLevel, String date) 
  {
    Iterator<Monitor> it = this.monitorList.iterator();    // iterateur de la liste des moniteurs
    while(it.hasNext()) 
    {
      Monitor tmp = it.next();
      if(tmp.getType() == "A") {                                                                        // si le type du moniteur correspond
        fire.addListener(tmp);                                                                          // on l'ajoute en ecoute du capteur de simulation
        String message = new String("Fire alarm Level " + critLevel + ", " +  fire.getLocation());      // message a afficher dans la liste graphique
        this.eventListModel.addElement(message);                                                        // ajout de l'élément au model
        this.eventList.add(fire.generateAnomalyEvent(critLevel, date));                                 // generation de l'alarme et ajout à la liste des events
      }
    }
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
    while(it.hasNext()) 
    {
      Monitor tmp = it.next();
      if(tmp.getType() == "A" || tmp.getType() == "B") {
        String message = new String("Gaz alarm Level " + critLevel + ", " +  gaz.getLocation());
        this.eventListModel.addElement(message);
        this.eventList.add(gaz.generateAnomalyEvent(critLevel, date, type));
        gaz.addListener(tmp);
      }
    }
       
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
    while(it.hasNext()) 
    {
      Monitor tmp = it.next();
      if(tmp.getType() == "B") {   
        String message = new String("Radiation alarm Level " + critLevel + ", " + rad.getLocation());
        this.eventListModel.addElement(message);
        this.eventList.add(rad.generateAnomalyEvent(critLevel, date, radLevel));                       
        rad.addListener(tmp);
      }
    }
  }


/**
 * Indique les actions a effectuer au clic d'un element du menu
 * 
 * @param event : événement déclencheur d'une action
 */
  public void actionPerformed(ActionEvent event)
  {
    /*----------------------------------
        CLIC BOUTON "archiver"
    ----------------------------------*/
    if(event.getActionCommand().equals("archive")) 
    {
      this.eventListModel.remove(graphicEventList.getSelectedIndex());    // Suppression de l'event selectionne
    }
  }
}
