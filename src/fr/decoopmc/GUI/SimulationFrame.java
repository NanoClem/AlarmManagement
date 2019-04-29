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
    private JTextField building = new JTextField();

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
        String[] types = {"Fire", "Gaz", "Radiation"};

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
        launcher.setActionCommand("FireAlarm");
        launcher.addActionListener(this);
    }

    /**
     * 
     */
    public void actionPerformed(ActionEvent event)
    {
        if (event.getActionCommand().equals("FireAlarm"))
        {
            String type = this.alarmType.getSelectedItem().toString();
            int lvl     = this.critLevel.getSelectedIndex()+1;
            String loc  = this.building.getText();
            long date   = 45; //temp, recup date actuelle

            this.parent.alarmLaunched(type, lvl, loc, date);
            //creer nouveau AnomalyEvent
            
        }
    }
}