import GUI.MyContentPanel;
import java.util.*;
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
  private MyContentPanel content = new MyContentPanel(frameSize);


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
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setAlwaysOnTop(false);
    setLayout(new GridLayout());

    /*==========================================================
        CONTENT PANEL
    ==========================================================*/
    this.setContentPane(content);

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

    // AJOUTS DES ACTION DE CLIC SUR LES ITEMS SOUS MENU
    launch.setActionCommand("launch");
    launch.addActionListener(this);
    quit.setActionCommand("quit");
    quit.addActionListener(this);
    

    pack();
    setVisible(true);
  }


/**
 * Indique les actions a effectuer au clic d'un element du menu
 */
 public void actionPerformed(ActionEvent event)
  {
    // CLIC SUR "launch"
    if(event.getActionCommand().equals("launch"))
    {
      new SimulationFrame(frameSize, "Alarm Simulator");
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
