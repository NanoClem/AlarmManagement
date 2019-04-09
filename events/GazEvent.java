
/**
 * Cette classe constitue une alarme anti-gaz toxiques
 * Puisqu'elle correpond à une alarme, elle dérive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
*/
public class GazEvent extends AnomalyEvent {
  private String type;  // type de gaz émis (CO2, hydrogène, hélium, ...)

  /**
   * CONSTRUCTEUR DE CLASSE GazEvent
  */
  public GazEvent(int _criticalLevel, long _creationTime, String _location, String _type) {
    super(2, _criticalLevel, _creationTime, _location);   //constructeur de classe mère
    this.type = _type;
  }
}
