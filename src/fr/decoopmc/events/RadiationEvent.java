package fr.decoopmc.events;

/**
 * Cette classe constitue une alarme anti-radiations
 * Puisqu'elle correpond à une alarme, elle derive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyEvent
*/
public class RadiationEvent extends AnomalyEvent {

  /**
   * Niveau de radiation mesure (entre 0 et 100)
   */
  private int radLevel = 0;

  /**
   * CONSTRUCTEUR DE CLASSE RadiationEvent
   *
   * @param _radLevel : niveau de radiation entre 0 et 100
  */
  public RadiationEvent(int _idCaptor, int _criticalLevel, long _creationTime, String _location, int _radLevel) {
    super(_idCaptor, _criticalLevel, _creationTime, _location);
    this.radLevel = _radLevel;
  }


  /**
   * 
   */
  public int getRadLevel()  {return this.radLevel;}

  /**
   * 
   * @param newLevel
   */
  public void setRadLevel(int newLevel) {this.radLevel = newLevel;}
}
