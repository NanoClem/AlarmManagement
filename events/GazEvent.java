package events;

/**
 * Cette classe constitue une alarme anti-gaz toxiques
 * Puisqu'elle correpond à une alarme, elle derive de la classe AnomalyEvent
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyEvent
*/
public class GazEvent extends AnomalyEvent {

  /**
   * Type de gaz concernant l'event.
   * Ex : CO2, hydrogene, helium, ...
   */
  private String type;

  /**
   * CONSTRUCTEUR DE CLASSE GazEvent
   *
   * @param _type : type de gaz emis (CO2, hydrogène, helium, ...)
  */
  public GazEvent(int _criticalLevel, long _creationTime, String _location, String _type) {
    super(2, _criticalLevel, _creationTime, _location);   //constructeur de classe mère
    this.type = _type;
  }


  //GETTERS
  public String getType() {return this.type;}

  // SETTERS
  public void setType(String newType) {this.type = newType;}
}
