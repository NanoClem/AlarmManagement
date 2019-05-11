package fr.decoopmc.interfaces;

import fr.decoopmc.events.GazEvent;


/**
 * Cette interface correspond à l'écouteur des alarmes anti-gaz toxiques
 * Puisqu'elle constitue un écouteur d'une anomalie, elle dérive de la classe AnomalyListener
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyListener
*/
public interface GazListener extends AnomalyListener {

    /**
     * Alarme déclenchée
     * @param e : événement "gaz"
     */
    void alarmLaunched(GazEvent radiation);
}
