package fr.decoopmc.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import java.awt.Dimension;
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
public class SimulationFrame extends JFrame {

    /**
     * Fenêtre parente de la fenêtre de simulation
     */
    private JFrame parent;

    /**
     * Conteneur des onglets d'alarme
     */
    private JTabbedPane tabs = new JTabbedPane();

    /**
     * <b>CONSTRUCTEUR DE CLASSE SimulationFrame</b>
     * <p>La classe</p>
     */
    public SimulationFrame(JFrame _parent, Dimension size, String title)
    {
        super(title);
        this.parent = _parent;

        this.setSize(size);
        this.setAlwaysOnTop(true);
        this.initTabs();
        this.setContentPane(this.tabs);
        this.setVisible(true);
    }

    /**
     * 
     * @param nbTabs
     */
    public void initTabs()
    {
        this.tabs.addTab("Incendie", new MyTabContent(this));
        this.tabs.addTab("Gaz", new JPanel());
        this.tabs.addTab("Radiations", new JPanel());

    }
}