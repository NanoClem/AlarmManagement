package fr.decoopmc.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


/**
 * <p>Cette classe est un formulaire pour la saisie des informations 
 * relatives aux alarmes que l'on veut </p>
 * <p>Les elements contenus dans cette interface graphique sont générés dynamiquement
 * depuis un fichier XML</p>
 * 
 * @author decoopmc
 * @version 1.0
 * @see javax.swing.JPanel
 */
public class MyTabContent extends JPanel 
                          implements ActionListener {

    /**
     * Fenêtre parente de l'onglet
     */
    private JFrame parent;

    /**
     * Bouton declencheur de l'alarme.
     */
    private JButton launcher = new JButton("Declencher");

    /**
     * 
     */
    //JTextField building = new JTextField();

    /**
     * <b>CONSTRUCTEUR DE CLASSE MyTabContent</b>
     */
    public MyTabContent(JFrame _parent)
    {
        this.parent = _parent;
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(launcher);

        //EVENEMENTS
        launcher.setActionCommand("alarm");
        launcher.addActionListener(this);

    }

    /**
     * Permet de commander les actions 
     * des differents composants
     * 
     * @param event
     */
    public void actionPerformed(ActionEvent event)
    {
       if(event.getActionCommand().equals("alarm"))
       {
           System.out.println("AU FEU");
       }

    }
}