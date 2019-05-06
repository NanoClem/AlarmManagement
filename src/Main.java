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
        //WARNING : COUPLAGE FORT ENTRE LES FENÊTRES
        //une alarme générée depuis une fenêtre doit pouvoir être affichée sur les deux fenêtres
        MainWindow w = new MainWindow("AlarmManagement");
        MainWindow w2 = new MainWindow("AlarmManagement");
        /*================================================
            TEST MONITEURS
        ================================================*/
        Monitor FireStation    = new Monitor("A");
        Monitor EcologyControl = new Monitor("B");
        w.addMonitor(FireStation);
        w2.addMonitor(FireStation);
        w2.addMonitor(EcologyControl);
    }
}