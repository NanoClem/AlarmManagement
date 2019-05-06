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
  public RadiationEvent(int _idCaptor, int _criticalLevel, String _creationTime, String _location, int _radLevel) {
    super(_idCaptor, _criticalLevel, _creationTime, _location);
    this.radLevel = _radLevel;
  }

  /**
   * 
   * @return 
   */
  @Override
  public String getInformations()
  {
    String ret = "ID Captor : "         + this.idCaptor + "\n"
                 + "Critical level : "  + this.criticalLevel + "\n"
                 + "Radiation level : " + this.radLevel + "\n"
                 + "Location : "        + this.location + "\n"
                 + "Date : "            + this.creationTime;
    return ret;
  }

  /**
   * Retourne le niveau de radiation
   * 
   * @return niveau de radiation de l'event
   */
  public int getRadLevel()  {return this.radLevel;}

  /**
   * Modifie le niveau de radiation
   * 
   * @param newLevel : nouveau niveau
   */
  public void setRadLevel(int newLevel) {this.radLevel = newLevel;}
}
