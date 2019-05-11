package fr.decoopmc.interfaces;

import fr.decoopmc.events.RadiationEvent;

/**
 * Cette interface correspond à l'écouteur des alarmes anti-radiations
 * Puisqu'elle constitue un écouteur d'une anomalie, elle dérive de la classe AnomalyListener
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyListener
*/
public interface RadiationListener extends AnomalyListener {

	/**
	 * Alarme déclenchée
	 * @param e : événement "radiation"
	 */
	void alarmLaunched(RadiationEvent radiation);
}
