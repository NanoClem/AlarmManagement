package fr.decoopmc.responders;

import javax.swing.event.EventListenerList;
import fr.decoopmc.events.FireEvent;
import fr.decoopmc.interfaces.*;


/**
 * <b>Monitor est une classe permettant de gerer les differents types d'alarmes</b>
 * <p>
 * Cette classe ecoute les evenements de type Incendie
 * </p>
 *
 * @author decoopmc
 * @version 1.0
 */
public class Monitor implements FireListener {

  /**
   * Le type du moniteur. Permet de savoir quelles type d'alarmes il peut gerer.
   *
   * @see Monitor#Monitor(String)
   * @see Monitor#getType()
   */
  private String type;
                                                           // d'ecouteur

  /**
   * <b>CONSTRUCTEUR DE CLASSE Monitor</b>
   * <p>Capte les differentes alarmes et donne une reponse</p>
   * <p>
   * A la construction d'un objet Monitor, son type est dertermine
   * </p>
   *
   * @param _type : type du moniteur
   * @see Monitor#type
   */
  public Monitor(String _type) {
     this.type = _type;
  }

  /**
   * Retourne le type du moniteur
   * @return type du moniteur en String
   */
  public String getType() {
    return this.type;
  }

  /**
   * Reponse lorsqu'une alarme est declenchee
   * @param fire : evenement message de type incendie
   */
  @Override
  public void criticalLevelChanged(FireEvent fire) 
  {
    System.out.println("FIRE ALARM");
    System.out.println("Level : " + fire.getCriticalLevel());
    System.out.println("Location : " + fire.getLocation());
    System.out.println("Date " + fire.getCreationTime());
  }
}
