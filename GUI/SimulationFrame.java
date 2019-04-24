import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Dimension;


/**
 * 
 */
public class SimulationFrame extends JFrame
{
    /**
     * 
     */
    private JTabbedPane tabs = new JTabbedPane();

    /**
     * 
     */
    public SimulationFrame(Dimension size, String title)
    {
        super(title);
        this.setSize(size);
        this.setAlwaysOnTop(true);
        this.initTabs();
        this.setContentPane(this.tabs);
        this.setVisible(true);
    }

    /**
     * 
     */
    public void initTabs()
    {
        this.tabs.addTab("Incendie", new JPanel());
        this.tabs.addTab("Gaz", new JPanel());
        this.tabs.addTab("Radiations", new JPanel());
    }
}