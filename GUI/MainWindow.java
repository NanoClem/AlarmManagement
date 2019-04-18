package GUI;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MainWindow extends JFrame
                        implements ActionListener, MouseListener
{
  public MainWindow(String title) throws Exception
  {
    super(title);
    setSize(700,700);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setAlwaysOnTop(true);

    // BARRE DE MENU
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    // ELEMENTS DU MENU
    JMenu simu = new JMenu("Simu Alarm");
    menuBar.add(simu);

    // SOUS MENU
    JMenuItem launch = new JMenuItem("launch");
    simu.add(launch);
    simu.add(new JSeparator());                          // ligne séparatrice
    JMenuItem quit = new JMenuItem("Quitter");
    simu.add(quit);

    // AJOUTS DES ACTION DE CLIC SUR LES ITEMS
    quit.setActionCommand("quit");                       // Clic sur "Quitter"
    quit.addActionListener(this);

    setVisible(true);
  }


  // SURCHARGE DES METHODES DE MOUSELISTENER
  public void mousePressed(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}


  // ACTIONS DU MENU
 public void actionPerformed(ActionEvent event)
  {
    // ACTION LORSQUE L'ON CLIQUE SUR "Quitter"
    if ( event.getActionCommand().equals("quit") )                 // si on sélectionne "Quitter"
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
