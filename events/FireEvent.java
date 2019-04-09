
/**
 * Cette classe constitue une alarme anti-incendies
 * Puisqu'elle correpond à une alarme, elle dérive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
*/
public class FireEvent extends AnomalyEvent {
  /**
   * CONSTRUCTEUR DE CLASSE FireEvent
  */
  public FireEvent(int _criticalLevel, long _creationTime, String _location) {
    super(1, _criticalLevel, _creationTime, _location);   // constructeur de classe mère
  }
}
