package fr.decoopmc.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.decoopmc.captors.*;

import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;



/**
 * Cette classe represente une fenetre dans laquelle il est possible
 * de declencher differents types d'alarmes, en personnalisant leurs
 * informations a l'aide de formulaires
 * 
 * @author decoopmc
 * @version 1.0
 * @see javax.swing.JFrame;
 */
public class SimulationFrame extends JFrame
                             implements ActionListener {

    /**
     * <b>Liste des enfants concernés par la simulation.</b>
     * <p>
     * Permet de diffuser les signaux d'alarmes entre les fenêtres enfants présentes dans la liste.
     * </p>
     */
    private ArrayList<MonitorWindow> childs = new ArrayList<MonitorWindow>();

    /**
     * Bouton declencheur de l'alarme.
     */
    private JButton launcher = new JButton("Declencher");

    /**
     * Champ texte de la localisation
     */
    private JTextField building = new JTextField("Main Hall, 1rst floor...");

    /**
     * Liste déroulante du niveau critique
     */
    private JComboBox<Integer> critLevel = new JComboBox<>();

    /**
     * Liste déroulante du type d'alarme
     */
    private JComboBox<String> alarmType = new JComboBox<>();

    /**
     *  Panneau d'affichage de données supplémentaires selon le type d'alarme
     */
    JPanel moreData  = new JPanel();

    /**
     * champ texte du type de gaz
     */
    private JTextField gazType = new JTextField("C02, Azote..");

    /**
     * Liste déroulante du niveau de radiation
     */
    private JTextField radiationLevel = new JTextField("0");


    /**
     * <b>CONSTRUCTEUR DE CLASSE SimulationFrame</b>
     * <p>Il s'agit d'une fenêtre qui permet de simuler une alarme</p>
     * 
     * @param _parent : fenêtre parente
     * @param size : taille de la fenêtre
     * @param title
     */
    public SimulationFrame(Dimension size, String title)
    {
        super(title);

        this.setSize(size);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.initMenu();
        this.initForm();
        this.setVisible(true);
    }


    /**
     * Construction de la barre de menu et de ses éléments
     */
    public void initMenu()
    {
        /*----------------------------------
            BARRE DE MENU
        ----------------------------------*/
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        /*----------------------------------
            MENU
        ----------------------------------*/
        JMenu menu = new JMenu("Utils");
        menuBar.add(menu);

        /*----------------------------------
            SOUS-MENU
        ----------------------------------*/
        JMenuItem quit = new JMenuItem("Quitter");
        menu.add(quit);

        /*----------------------------------
            ACTIONS
        ----------------------------------*/
        quit.setActionCommand("quit");
        quit.addActionListener(this);
    }

    
    /**
     * Construit les différents éléments du formulaire
     * de déclenchement d'alarmes
     */
    public void initForm()
    {
        /* ---------------------------------------
                    LOCALISATION
        --------------------------------------- */
        JPanel locPanel = new JPanel();
        JLabel locLabel = new JLabel("Location :");
        building.setBounds(128, 28, 86, 20);
        building.setColumns(10);
        locPanel.add(locLabel);
        locPanel.add(building);

        /* ---------------------------------------
                    NIVEAU D'IMPORTANCE
        --------------------------------------- */
        JPanel critPanel = new JPanel();
        JLabel critLabel = new JLabel("Critical level :");
        Integer[] lvls = {1,2,3};

        for(int i = 0; i < 3; i++)
            critLevel.addItem(lvls[i]);

        critPanel.add(critLabel);
        critPanel.add(critLevel);

        /* ---------------------------------------
                     TYPE D'ALARME
        --------------------------------------- */
        JPanel alarmPanel = new JPanel();
        JLabel alarmLabel = new JLabel("Alarm Type :");
        String[] types = {"Fire", "Gaz", "Radiation"};      // UPGRADE : lire les types depuis XML ?

        for(int i = 0; i < 3; i++)
            alarmType.addItem(types[i]);

        alarmPanel.add(alarmLabel);
        alarmPanel.add(alarmType);
        
        /* ---------------------------------------
                BOUTON DECLENCHEUR
        --------------------------------------- */
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(launcher);
    
        /* ---------------------------------------
                    PANEL PRINCIPAL
        --------------------------------------- */
        this.getContentPane().add(locPanel);
        this.getContentPane().add(critPanel);
        this.getContentPane().add(alarmPanel);
        this.getContentPane().add(moreData);
        this.getContentPane().add(buttonPanel);

        /* ---------------------------------------
                    EVENEMENTS
        --------------------------------------- */
        launcher.setActionCommand("alarm");             // clic sur le bouton de declenchement de l'alarme
        launcher.addActionListener(this);
        alarmType.setActionCommand("typeSelected");     // selection d'un type d'alarme
        alarmType.addActionListener(this);
    }


    /*=======================================
        TRAITEMENT LISTE DES ENFANTS
    =======================================*/

    /**
     * Ajoute une nouvelle fenêtre de moniteur dans 
     * la liste des enfants.
     * 
     * @param p : fenêtre enfant à ajouter
     */
    public void addChild(MonitorWindow child) {
        this.childs.add(child);
    }

    /**
     * Retire un élément de la liste des enfants
     * 
     * @param p
     */
    public void removeChild(MonitorWindow child) {
        this.childs.remove(child);
    }


    /*=======================================
        TRAITEMENT DES ACTIONS
    =======================================*/

    /**
     * Produit une réponse aux actions écoutées 
     * dans la fenêtre 
     * 
     * @param event : évènement déclenché lors d'une action écoutée
     */
    public void actionPerformed(ActionEvent event)
    {
        String typeSelected = "";                                               // type d'alarme selectionne
        int lvl             = 0;                                                // niveau critique
        String loc          = "";                                               // localisation
        DateFormat format   = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");      // format de la date de declenchement

        /* ----------------------------------
            SELECTION D'UN TYPE D'ALARME
        ----------------------------------- */
        if(event.getActionCommand().equals("typeSelected"))
        {
            typeSelected = this.alarmType.getSelectedItem().toString();

            // INCENDIE
            if(typeSelected == "Fire")
            {
                this.setVisible(false);
                moreData.removeAll();
                this.setVisible(true);
            }
            
            // GAZ
            if(typeSelected == "Gaz")
            {
                this.setVisible(false);
                moreData.removeAll();       

                gazType.setBounds(128, 28, 86, 20);
                gazType.setColumns(10);
                JLabel gazLabel = new JLabel("Gaz type");
                moreData.add(gazLabel);
                moreData.add(gazType);

                this.setVisible(true);
            }

            // RADIATION
            if(typeSelected == "Radiation")
            {
                this.setVisible(false);
                moreData.removeAll();

                radiationLevel.setBounds(128, 28, 86, 20);
                radiationLevel.setColumns(10);
                JLabel radLabel = new JLabel("Radiation level");
                moreData.add(radLabel);
                moreData.add(radiationLevel);

                this.setVisible(true);
            }
        }

        /* ----------------------------------
            DECLENCHEMENT D'UNE ALARME
        ----------------------------------*/
        if (event.getActionCommand().equals("alarm"))
        {
            typeSelected = this.alarmType.getSelectedItem().toString();
            lvl          = this.critLevel.getSelectedIndex()+1;
            loc          = this.building.getText();
            
            //date de declenchement
            Date current = new Date();

            //FIRE ALARM
            if(typeSelected == "Fire") 
            {
                FireCaptor f = new FireCaptor(loc);
                Iterator<MonitorWindow> it = this.childs.iterator();
                while(it.hasNext()) {
                    it.next().alarmLaunched(f, lvl, format.format(current));
                }
            }
                
            //GAZ ALARM
            else if(typeSelected == "Gaz") 
            {
                GazCaptor g  = new GazCaptor(loc);
                String gType = this.gazType.getText();
                Iterator<MonitorWindow> it = this.childs.iterator();
                while(it.hasNext()) {
                    it.next().alarmLaunched(g, lvl, gType, format.format(current));
                }
            }

            //RADIATION ALARM
            else if(typeSelected == "Radiation") 
            {
                int radLvl = Integer.parseInt(this.radiationLevel.getText());
                if(radLvl < 0 || radLvl > 100) {
                    int option = JOptionPane.showConfirmDialog(this,
                                                               "Le niveau de radiation doit être compris entre 0 et 100",
                                                               "Attention !",
                                                               JOptionPane.WARNING_MESSAGE);
                }
                else {
                    RadiationCaptor r = new RadiationCaptor(loc);
                    Iterator<MonitorWindow> it = this.childs.iterator();
                    while(it.hasNext()) {
                        it.next().alarmLaunched(r, lvl, radLvl, format.format(current));
                    }
                }
            }
        }

        /* ----------------------------------
            ACTION LORSQUE L'ON CLIQUE SUR "Quitter"
        ----------------------------------*/
        if ( event.getActionCommand().equals("quit") )                     // si on sélectionne "Quitter"
        {
            JOptionPane pane = new JOptionPane();
            if ( pane.showConfirmDialog(this,                              // on demande une confirmation en rapport
                        "Voulez vous vraiment quitter ?",                  // avec la fenêtre qui a déclenché l'action (this)
                        "Attention",
                        pane.YES_NO_OPTION,
                        pane.WARNING_MESSAGE) == JOptionPane.YES_OPTION )  // si on a confirmé
                System.exit(0);                                            // on quitte le programme
        }
    }
}