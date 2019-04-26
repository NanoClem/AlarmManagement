package fr.decoopmc.GUI;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


/**
 * Cette classe constitue le contenueur principal dans lequel
 * tous les autres conteneurs evoluent. Elle derive de JPanel.
 * 
 * @author decoopmc
 * @version 1.0
 * @see javax.swing.JPanel
 */
public class MyContentPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JButton details = new JButton("Details");
    private JButton archive = new JButton("Archiver");
    JPanel displayDetails   = new JPanel();
    JList<String> eventList;

    /**
     * 
     * @param size
     */
    public MyContentPanel(Dimension size)
    {
        this.setPreferredSize(size);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        // LISTE DES EVENTS (TESTS)
        String[] data = {"Incendie", "Gaz", "Radiation"};
        eventList = new JList<String>(data);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane scroller = new JScrollPane(eventList);

        //BOUTONS

        // LAYOUT
        this.add(eventList, BorderLayout.CENTER);
    }
}