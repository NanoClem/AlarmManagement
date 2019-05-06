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
  public String getInformations()
  {
    String ret = "<html>"
                 + "<b>ID Captor :</b> "         + this.idCaptor + "<br/>"
                 + "<b>Critical level</b> : "    + this.criticalLevel + "<br/>"
                 + "<b>Radiation level</b> : "   + this.radLevel + "<br/>"
                 + "<b>Location :</b> "          + this.location + "<br/>"
                 + "<b>Date :</b> "              + this.creationTime + "<br/>"
                 + "</html>";

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
