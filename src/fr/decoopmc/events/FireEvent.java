package fr.decoopmc.events;

/**
 * Cette classe constitue une alarme anti-incendies
 * Puisqu'elle correpond à une alarme, elle derive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyEvent
*/
public class FireEvent extends AnomalyEvent {

  /**
   * CONSTRUCTEUR DE CLASSE FireEvent
  */
  public FireEvent(int _idCaptor, int _criticalLevel, long _creationTime, String _location) 
  {
    super(_idCaptor, _criticalLevel, _creationTime, _location);
  }
}
