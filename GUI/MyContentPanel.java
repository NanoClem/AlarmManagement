package GUI;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    JList eventList;

    /**
     * 
     */
    public MyContentPanel(Dimension size)
    {
        this.setPreferredSize(size);
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());

        // LISTE DES EVENTS
        String[] data = {"Incendie", "Gaz", "Radiation"};
        eventList = new JList(data);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //JScrollPane scroller = new JScrollPane(eventList);

        // TEST GRIDBAGLAYOUT (creer classe)
        GridBagConstraints gbc = new GridBagConstraints();
            // liste event
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipady = 80;
            gbc.ipadx = 80;
            gbc.weightx = 1.0;
            gbc.gridheight = 2;
            gbc.gridwidth  = 1;
            gbc.fill = GridBagConstraints.VERTICAL;
            this.add(eventList, gbc);
            // boutons
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.ipady = 0;
            gbc.weightx = 1.0;
            this.add(details, gbc);
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.ipady = 0;
            gbc.ipadx = 0;
            gbc.weightx = 1.0;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            this.add(archive, gbc);
    }
}