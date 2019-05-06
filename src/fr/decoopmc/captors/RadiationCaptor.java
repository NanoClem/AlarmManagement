package fr.decoopmc.captors;

import fr.decoopmc.interfaces.RadiationListener;
import fr.decoopmc.events.RadiationEvent;

/**
 * 
 */
public class RadiationCaptor extends Captor
{
    /**
     * <b>CONSTRUCTEUR DE CLASSE RadiationCaptor</b>
     * <p>Un capteur de radiation</p>
     * @param _location
     */
    public RadiationCaptor(String _location)
    {
        super(_location);
    }

    /**
     * Genere une alarme radiation de type FirEvent
     * @param criticalLevel : niveau d'importance de l'alarme
     * @param creationTime : date de creation de l'alarme
     */
    //@Override
    public void generateAnomalyEvent(int criticalLevel, String creationTime, int radiationLevel)
    {
        RadiationEvent radiation = new RadiationEvent(id, criticalLevel, creationTime, this.location, radiationLevel);

        for(RadiationListener radiationListener : this.getRadiationListeners()){
            radiationListener.criticalLevelChanged(radiation);
        }
    }

    
    /*==========================================================================
          AJOUT ET SUPRESSION DANS LA LISTE DES LISTENERS
    ========================================================================== */

    /**
     * Ajoute un listener de type RadiationListener (radiation) dans la liste
     *
     * @param listener : listener a inserer
     * @see RadiationListener
     */
    //@Override
    public void addListener(RadiationListener listener) {
        this.listeners.add(RadiationListener.class, listener);
    }

    /**
     * Supprime un listener de type RadiationListener (radiations) dans la liste
     *
     * @param listener : listener a supprimer
     * @see RadiationListener
     */
    //@Override
    public void removeListener(RadiationListener listener) {
        this.listeners.remove(RadiationListener.class, listener);
    }


    /*==========================================================================
          GETTERS & SETTERS
    ========================================================================== */

    /**
     * Retourne une liste des listener de type radiation
     *
     * @return tableau de listeners de type RadiationListener
     */
   public RadiationListener[] getRadiationListeners() {
    return this.listeners.getListeners(RadiationListener.class);
  }
}