package fr.decoopmc.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Cette classe represente une fenetre dans laquelle il est possible
 * de declencher differents types d'alarmes, en personnalisant leurs
 * informations a l'aide de formulaires
 * 
 * @author decoopmc
 * @version 1.0
 * @see javax.swing.JPanel
 */
public class SimulationFrame extends JFrame
                             implements ActionListener {

    /**
     * Fenêtre parente de la fenêtre de simulation
     */
    private MainWindow parent;

    /**
     * Bouton declencheur de l'alarme.
     */
    private JButton launcher = new JButton("Declencher");

    /**
     * 
     */
    private JTextField building = new JTextField("Main Hall, 1rst floor...");

    /**
     * 
     */
    private JComboBox<Integer> critLevel = new JComboBox<>();

    /**
     * 
     */
    private JComboBox<String> alarmType = new JComboBox<>();


    /**
     * <b>CONSTRUCTEUR DE CLASSE SimulationFrame</b>
     * <p>Il s'agit d'une fenêtre qui permet de simuler une alarme</p>
     */
    public SimulationFrame(MainWindow _parent, Dimension size, String title)
    {
        super(title);
        this.parent = _parent;

        this.setSize(size);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setResizable(false);
        this.setAlwaysOnTop(true);

        this.initForm();
        //this.pack();
        this.setVisible(true);
    }

    /**
     * Construit les différents éléments du formulaire
     * de déclenchement d'alarmes
     */
    public void initForm()
    {
        /* =======================================
                    LOCALISATION
        ======================================= */
        JPanel locPanel = new JPanel();
        JLabel locLabel = new JLabel("Location :");
        building.setBounds(128, 28, 86, 20);
        building.setColumns(10);
        locPanel.add(locLabel);
        locPanel.add(building);

        /* =======================================
                    NIVEAU D'IMPORTANCE
        ======================================= */
        JPanel critPanel = new JPanel();
        JLabel critLabel = new JLabel("Critical level :");
        Integer[] lvls = {1,2,3};

        for(int i = 0; i < 3; i++)
            critLevel.addItem(lvls[i]);

        critPanel.add(critLabel);
        critPanel.add(critLevel);

        /* =======================================
                     TYPE D'ALARME
        ======================================= */
        JPanel alarmPanel = new JPanel();
        JLabel alarmLabel = new JLabel("Alarm Type :");
        String[] types = {"Fire", "Gaz", "Radiation"};      // UPGRADE : lire les types depuis XML ?

        for(int i = 0; i < 3; i++)
            alarmType.addItem(types[i]);

        alarmPanel.add(alarmLabel);
        alarmPanel.add(alarmType);
        
        /* =======================================
                     DECLENCHER
        ======================================= */
        JPanel buttonLabel = new JPanel();
        buttonLabel.add(launcher);
    

        /* =======================================
                    PANEL PRINCIPAL
        ======================================= */
        this.getContentPane().add(locPanel);
        this.getContentPane().add(critPanel);
        this.getContentPane().add(alarmPanel);
        this.getContentPane().add(buttonLabel);

        /* =======================================
                    EVENEMENTS
        ======================================= */
        launcher.setActionCommand("alarm");             // clic sur le bouton de declenchement de l'alarme
        launcher.addActionListener(this);
        alarmType.setActionCommand("typeSelected");     // selection d'un type d'alarme
        alarmType.addActionListener(this);
    }

    /**
     * Produit une réponse aux actions écoutées 
     * dans la fenêtre 
     * 
     * @param event : évènement déclenché lors d'une action écoutée
     */
    public void actionPerformed(ActionEvent event)
    {
        String typeSelected = "";
        int lvl             = 0;
        String loc          = "";
        long date           = 0;


        /* =======================================
                SELECTION D'UN TYPE D'ALARME
        ======================================= */
        if(event.getActionCommand().equals("typeSelected"))
        {
            typeSelected = this.alarmType.getSelectedItem().toString();

            if(typeSelected == "Gaz")
            {
                System.out.println(typeSelected + '\n');
                // JPanel gazData  = new JPanel();
                // JLabel gazLabel = new JLabel("Gaz type");
                // JTextField gazType = new JTextField("C02, Azote.."); 
                // gazData.add(gazLabel);
                // gazData.add(gazType);
                // this.getContentPane().add(gazData);
            }
        }

        /* =======================================
                DECLENCHEMENT D'UNE ALARME
        ======================================= */
        if (event.getActionCommand().equals("alarm"))
        {
            //creer nouveau AnomalyEvent
            typeSelected = this.alarmType.getSelectedItem().toString();
            lvl          = this.critLevel.getSelectedIndex()+1;
            loc          = this.building.getText();
            date         = 45; //temp, recup date actuelle

            //FIRE ALARM
            if(typeSelected == "Fire")
                this.parent.alarmLaunched(typeSelected, lvl, loc, date);

            //GAZ ALARM
            else if(typeSelected == "Gaz")
            {
                this.parent.alarmLaunched(typeSelected, lvl, loc, date);
            }

            //RADIATION ALARM
            else if(typeSelected == "Radiation")
            {
                this.parent.alarmLaunched(typeSelected, lvl, loc, date);
            }

            
            
        }
    }
}