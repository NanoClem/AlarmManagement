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
  public FireEvent(int _idCaptor, int _criticalLevel, String _creationTime, String _location) 
  {
    super(_idCaptor, _criticalLevel, _creationTime, _location);
  }

  /**
   * 
   */
  public String getInformations()
  {
    String ret = "<html>"
                 + "<b>ID Captor :</b> "         + this.idCaptor + "<br/>"
                 + "<b>Critical level</b> : "    + this.criticalLevel + "<br/>"
                 + "<b>Location :</b> "          + this.location + "<br/>"
                 + "<b>Date :</b> "              + this.creationTime + "<br/>"
                 + "</html>";
                 
    return ret;
  }
}
