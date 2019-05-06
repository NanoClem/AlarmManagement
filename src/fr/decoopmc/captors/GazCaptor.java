package fr.decoopmc.captors;

import fr.decoopmc.interfaces.GazListener;
import fr.decoopmc.events.GazEvent;

/**
 * 
 */
public class GazCaptor extends Captor
{
    /**
     * <b>CONSTRUCTEUR DE CLASSE GazCaptor</b>
     * <p>Un capteur de gaz</p>
     * @param _location
     */
    public GazCaptor(String _location)
    {
        super(_location);
    }

    /**
     * Genere une alarme gaz de type FirEvent
     * @param criticalLevel : niveau d'importance de l'alarme
     * @param creationTime : date de creation de l'alarme
     */
    //@Override
    public void generateAnomalyEvent(int criticalLevel, String creationTime, String gazType)
    {
        GazEvent gaz = new GazEvent(id, criticalLevel, creationTime, this.location, gazType);

        for(GazListener gazListener : this.getGazListeners()) {
            gazListener.criticalLevelChanged(gaz);
        }
    }

    
    /*==========================================================================
          AJOUT ET SUPRESSION DANS LA LISTE DES LISTENERS
    ========================================================================== */

    /**
     * Ajoute un listener de type GazListener (gaz) dans la liste
     *
     * @param listener : listener a inserer
     * @see GazListener
     */
    //@Override
    public void addListener(GazListener listener) {
        this.listeners.add(GazListener.class, listener);
    }

    /**
     * Supprime un listener de type GazListener (radiations) dans la liste
     *
     * @param listener : listener a supprimer
     * @see GazListener
     */
    //@Override
    public void removeListener(GazListener listener) {
        this.listeners.remove(GazListener.class, listener);
    }


    /*==========================================================================
          GETTERS & SETTERS
    ========================================================================== */

    /**
     * Retourne une liste des listener de type gaz
     *
     * @return tableau de listeners de type GazListener
     */
   public GazListener[] getGazListeners() {
    return this.listeners.getListeners(GazListener.class);
  }
}