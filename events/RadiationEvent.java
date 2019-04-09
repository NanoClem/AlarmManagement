
/**
 * Cette classe constitue une alarme anti-radiations
 * Puisqu'elle correpond à une alarme, elle derive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyEvent
*/
public class RadiationEvent extends AnomalyEvent {

  private int radLevel = 0;   // niveau de radiation (entre 0 et 100)

  /**
   * CONSTRUCTEUR DE CLASSE RadiationEvent
   *
   * @param _radLevel : niveau de radiation entre 0 et 100
  */
  public RadiationEvent(int _criticalLevel, long _creationTime, String _location, int _radLevel) {
    super(3, _criticalLevel, _creationTime, _location);
    this.radLevel = _radLevel;
  }


  // GETTERS
  public int getRadLevel()  {return this.radLevel;}

  // SETTERS
  public void setRadLevel(int newLevel) {this.radLevel = newLevel;}
}
