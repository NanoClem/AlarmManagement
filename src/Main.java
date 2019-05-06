import fr.decoopmc.GUI.*;
import fr.decoopmc.responders.*;


/**
 * 
 */
public class Main
{
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        MainWindow w = new MainWindow("AlarmManagement");

        /*================================================
            TEST MONITEURS
        ================================================*/
        Monitor FireStation    = new Monitor("A");
        Monitor EcologyControl = new Monitor("B");
        w.addMonitor(FireStation);
        w.addMonitor(EcologyControl);
    }
}