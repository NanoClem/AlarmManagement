package fr.decoopmc.interfaces;

import fr.decoopmc.events.FireEvent;


/**
 * Cette interface correspond à l'ecouteur des alarmes anti-incendies
 * Puisqu'elle constitue un ecouteur d'une anomalie, elle derive de la classe AnomalyListener
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyListener
 */
public interface FireListener extends AnomalyListener {

  /**
   * Alarme déclenchée
   * @param e : événement "incendie"
   */
  void alarmLaunched(FireEvent e);
}
