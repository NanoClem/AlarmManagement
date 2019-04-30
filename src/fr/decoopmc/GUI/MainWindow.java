package fr.decoopmc.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * 
 */
public class MainWindow extends JFrame
                        implements ActionListener
{
  /**
   * 
   */
  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  /**
   * 
   */
  private Dimension frameSize = new Dimension(screenSize.width*2/3, screenSize.height*2/3);

  /**
   * 
   */
  private JList<String> eventList;

  /**
   * 
   */
  private JButton details = new JButton("Details");

  /**
   * 
   */
  private JButton archive = new JButton("Archiver");


  /**
   * Cette classe represente la fenetre principale de l'application,
   * dans laquelle tous les elements sont contenus.
   * 
   * @param title
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

    /*==========================================================
        BARRE DE MENU
    ==========================================================*/
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

    /*==========================================================
        CONTENU
    ==========================================================*/
    this.initContent();

    /*==========================================================
        AJOUTS DES ACTION DE CLIC SUR LES ITEMS SOUS MENU
    ==========================================================*/
    launch.setActionCommand("launch");
    launch.addActionListener(this);
    quit.setActionCommand("quit");
    quit.addActionListener(this);
    

    this.pack();
    setVisible(true);
  }

  /**
   * 
   */
  public void initContent()
  {
    /*==================================
        SOUS PANELS
    ==================================*/
    JPanel left = new JPanel();
    left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
    JPanel right = new JPanel();

    /*==================================
        LISTE DES EVENTS
    ==================================*/
    String[] data = {"Incendie", "Gaz", "Radiation"};
    eventList = new JList<>(data);
    eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    eventList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    //JScrollPane scroller = new JScrollPane(eventList);

    /*==================================
        BOUTONS
    ==================================*/
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(details);
    buttonPanel.add(archive);

    /*==================================
        PANNEAU AFFICHAGE DES INFOS
    ==================================*/
    JPanel infosDisplayer = new JPanel();
    JLabel temp = new JLabel("Display alarm's data here");
    infosDisplayer.add(temp);

    /*==================================
        LEFT PANEL
    ==================================*/
    left.add(eventList);
    left.add(buttonPanel);

    /*==================================
        RIGHT PANEL
    ==================================*/
    right.add(infosDisplayer);

    /*==================================
        CONTENEUR PRINCIPAL
    ==================================*/
    this.getContentPane().add(left);
    this.getContentPane().add(right);
  }

  /**
   * TODO : passer un event en parametre
   */
  public void alarmLaunched(String alarmType, int critLevel, String location, long date)
  {
    System.out.println(alarmType);
    System.out.println("Critical level " + critLevel);
    System.out.println("Location " + location);
    System.out.println("Date " + date + '\n');
  }

/**
 * Indique les actions a effectuer au clic d'un element du menu
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
