package fr.decoopmc.events;



/**
 * AnomalyEvent modelise une alarme. Cette classe constitue une base parente
 * pour differents types d'alarmes
 *
 * @author decoopmc
 * @version 1.0
 * @param <abstract>
 */
public abstract class AnomalyEvent {

  /**
   * identifiant de l'event
   * @see AnomalyEvent#getIdEvent()
   */
  protected static int idEvent;

  /**
   * identifiant du capteur declencheur de l'alarme
   * @see AnomalyEvent#getIdCaptor()
   */
  protected int idCaptor;
  
  /**
   * niveau d'importance (entre 1 et 3)
   * @see AnomalyEvent#getCriticalLevel()
   */
  protected int criticalLevel;
  
  /**
   * date de creation
   * @see AnomalyEvent#getCreationTime()
   */
  protected String creationTime;  
  
  /**
   * lieu d'emission de l'alarme
   * @see AnomalyEvent#getLocation()
   */
  protected String location; 


  /**
   * <b>CONSTRUCTEUR DE CLASSE AnomalyEvent</b>
   *
   * @param _idCaptor : identifiant du capteur
   * @param _criticalLevel : niveau d'importance (entre 1 et 3)
   * @param _creationTime : date de creation (en ms)
   * @param _location : lieu d'emission
   */
  public AnomalyEvent(int _idCaptor, int _criticalLevel, String _creationTime, String _location) {
    idEvent++;
    this.idCaptor      = _idCaptor;
    this.criticalLevel = _criticalLevel;
    this.creationTime  = _creationTime;
    this.location      = _location;
  }

  /**
   * <b>METHODE ABSTRAITE</b>
   * <p>Retourne sous forme de texte les informations de l'event</p>
   */
  public abstract String getInformations();
    

  //GETTERS
  public int getIdCaptor()        {return idCaptor;}
  public int getIdEvent()         {return idEvent;}
  public int getCriticalLevel()   {return this.criticalLevel;}
  public String getCreationTime() {return this.creationTime;}
  public String getLocation()     {return this.location;}

  //SETTERS
  public void setId(int newId)                  {idEvent = newId;}
  public void setCriticalLevel(int newLevel)    {this.criticalLevel = newLevel;}
  public void setCreationTime(String newTime)   {this.creationTime = newTime;}
  public void setLocation(String newLocation)   {this.location = newLocation;}
}
