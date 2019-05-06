package fr.decoopmc.responders;

import fr.decoopmc.events.*;
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
public class Monitor implements FireListener, GazListener, RadiationListener {

  /**
   * Le type du moniteur. Permet de savoir quelles type d'alarmes il peut gerer.
   *
   * @see Monitor#Monitor(String)
   * @see Monitor#getType()
   */
  private String type;

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
   * Reponse lorsqu'une alarme incendie est declenchee
   * @param fire : evenement message de type incendie
   */
  @Override
  public void criticalLevelChanged(FireEvent fire) 
  {
    System.out.println("FIRE ALARM");
    System.out.println("Critical level : " + fire.getCriticalLevel());
    System.out.println("Location : " + fire.getLocation());
    System.out.println("Date " + fire.getCreationTime() + "\n");
  }

  /**
   * Reponse lorsqu'une alarme est declenchee
   * @param gaz : evenement message de type gaz
   */
  @Override
  public void criticalLevelChanged(GazEvent gaz) 
  {
    System.out.println("GAZ ALARM");
    System.out.println("Critical level : " + gaz.getCriticalLevel());
    System.out.println("Type : " + gaz.getType());
    System.out.println("Location : " + gaz.getLocation());
    System.out.println("Date " + gaz.getCreationTime() + "\n");
  }

  /**
   * Reponse lorsqu'une alarme est declenchee
   * @param gaz : evenement message de type gaz
   */
  @Override
  public void criticalLevelChanged(RadiationEvent rad) 
  {
    System.out.println("RADIATION ALARM");
    System.out.println("Critical level : " + rad.getCriticalLevel());
    System.out.println("Rad Level : " + rad.getRadLevel());
    System.out.println("Location : " + rad.getLocation());
    System.out.println("Date " + rad.getCreationTime() + "\n");
  }
}
