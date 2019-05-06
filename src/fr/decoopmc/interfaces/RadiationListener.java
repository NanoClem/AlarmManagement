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

	void criticalLevelChanged(RadiationEvent radiation);
}
