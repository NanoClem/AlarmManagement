package fr.decoopmc.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import fr.decoopmc.captors.*;
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
                        implements ActionListener {
  /**
   * Taile de l'écran
   */
  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  /**
   * Taille de la fenêtre
   */
  private Dimension frameSize = new Dimension(screenSize.width*2/3, screenSize.height*2/3);

  /**
   * Liste des alarmes déclenchées
   */
  private JList<String> eventList;

  /**
   * Panneau d'affichage des détails d'une alarme déclenchée
   */
  private JButton details = new JButton("Details");

  /**
   * Archive une alarme dans la liste
   */
  private JButton archive = new JButton("Archiver");

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
    String[] data = {"Incendie", "Gaz", "Radiation"};
    eventList = new JList<>(data);
    eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    eventList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    //JScrollPane scroller = new JScrollPane(eventList);

    /*----------------------------------
        BOUTONS
    ----------------------------------*/
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(details);
    buttonPanel.add(archive);

    /*----------------------------------
        PANNEAU AFFICHAGE DES INFOS
    ----------------------------------*/
    JPanel infosDisplayer = new JPanel();
    JLabel temp = new JLabel("Display alarm's data here");
    infosDisplayer.add(temp);

    /*----------------------------------
        LEFT PANEL
    ----------------------------------*/
    left.add(eventList);
    left.add(buttonPanel);

    /*----------------------------------
        RIGHT PANEL
    ----------------------------------*/
    right.add(infosDisplayer);

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

  /**
   * Met à jour le système d'écoute des alarmes.
   * 
   * @param m : Moniteur 
   */
  public void updateMonitorListening(Monitor m) 
  {}


/*========================================================
                  EVENTS ET ALARMES
  ========================================================*/

  /**
   * Actions effectuées lorsqu'une alarme incendie est déclenchée
   * 
   * @param fire
   * @param critLevel
   * @param date
   */
  public void alarmLaunched(FireCaptor fire, int critLevel, String date) 
  {
    Iterator<Monitor> it = this.monitorList.iterator();
    while(it.hasNext()) {
      Monitor tmp = it.next();
      if(tmp.getType() == "A") {
        fire.addListener(tmp);
      }
    }
    fire.generateAnomalyEvent(critLevel, date);
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
    gaz.generateAnomalyEvent(critLevel, date, type);
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

    rad.generateAnomalyEvent(critLevel, date, radLevel);
  }

/**
 * Indique les actions a effectuer au clic d'un element du menu
 * 
 * @param event : événement déclencheur d'une action
 */
  public void actionPerformed(ActionEvent event)
  {
    // CLIC SUR "launch"
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
