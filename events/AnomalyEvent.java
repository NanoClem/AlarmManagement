import java.util.EventObject;

/**
 * AnomalyEvent modelise une alarme
 * Cette classe constitue une base parente pour differents types d'alarmes
 *
 * @author decoopmc
 * @version 1.0
*/
abstract class AnomalyEvent extends EventObject {

  protected int idEvent;        // identifiant de l'event
  protected int criticalLevel;  // niveau d'importance (entre 1 et 3)
  protected long creationTime;  // date de creation (en ms)
  protected String location;    // lieu d'emission

  /**
  * CONSTRUCTEUR DE CLASSE AnomalyEvent
  *
  * @param source : reference à l'objet declencheur de l'evènement
  * @param _idEvent : identifiant de l'event (selon le type d'alarme)
  * @param _criticalLevel : niveau d'importance (entre 1 et 3)
  * @param _creationTime : date de creation (en ms)
  * @param _location : lieu d'emission
  */
  public AnomalyEvent(int _idEvent, int _criticalLevel, long _creationTime, String _location) {
    super();
    this.idEvent       = _idEvent;
    this.criticalLevel = _criticalLevel;
    this.creationTime  = _creationTime;
    this.location      = _location;
  }

  //GETTERS
  public int getId()            {return this.idEvent;}
  public int getCriticalLevel() {return this.criticalLevel;}
  public long getCreationTime() {return this.creationTime;}
  public String getLocation()   {return this.location;}

  //SETTERS
  public void setId(int newId)                {this.idEvent = newId;}
  public void setCriticalLevel(int newLevel)  {this.criticalLevel = newLevel;}
  public void setCreationTime(long newTime)   {this.creationTime = newTime;}
  public void setLocation(String newLocation) {this.location = newLocation;}
}
