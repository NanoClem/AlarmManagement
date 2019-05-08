import java.awt.Dimension;

import fr.decoopmc.GUI.*;
import fr.decoopmc.responders.*;



/**
 * 
 */
public class Main
{
    /**
     * Fonction main de l'application
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        tests();
    }


    /**
     * Test des fonctionnalités de l'application
     */
    public static void tests() throws Exception
    {
        /*------------------------------------------------
            MONITEURS
        ------------------------------------------------*/
        Monitor FireStation    = new Monitor("A");
        Monitor EcologyControl = new Monitor("B");

        /*------------------------------------------------
            FENETRE GRAPHIQUE DES MONITEURS
        ------------------------------------------------*/
        MainWindow w1 = new MainWindow("First");
        MainWindow w2 = new MainWindow("Second");

        /*------------------------------------------------
            FENETRE DE SIMULATION DES ALARMES
        ------------------------------------------------*/
        SimulationFrame simu = new SimulationFrame(new Dimension(300,400), "Simulation des alarmes");

        /*------------------------------------------------
            TESTS
        ------------------------------------------------*/
        //AJOUT DES MONITEURS
        w1.addMonitor(FireStation);
        w2.addMonitor(EcologyControl);
        //AJOUT DES MONITEURS GRAPHIQUES A LA SIMULATION
        simu.addParent(w1);
        simu.addParent(w2);
    }
}