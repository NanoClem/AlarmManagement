package fr.decoopmc.captors;

import fr.decoopmc.interfaces.FireListener;
import fr.decoopmc.events.FireEvent;


/**
 * Capteur de type incendie, derivant de la classe Captor
 * 
 * @author decoopmc
 * @version 1.0
 * @see Captor
 */
public class FireCaptor extends Captor 
{
    /**
     * <b>CONSTRUCTEUR DE CLASSE FireCaptor</b>
     * <p>Un capteur d'incendie</p>
     * @param _location
     */
    public FireCaptor(String _location)
    {
        super(_location);
    }

    /**
     * Genere une alarme incendie de type FirEvent
     * @param criticalLevel : niveau d'importance de l'alarme
     * @param creationTime : date de creation de l'alarme
     */
    public FireEvent generateAnomalyEvent(int criticalLevel, String creationTime)
    {
        FireEvent fire = new FireEvent(id, criticalLevel, creationTime, this.location);

        for(FireListener fireListener : this.getFireListeners()) {
            fireListener.criticalLevelChanged(fire);
        }

        return fire;
    }

    
    /*==========================================================================
          AJOUT ET SUPRESSION DANS LA LISTE DES LISTENERS
    ========================================================================== */

    /**
     * Ajoute un listener de type FireListener (incendie) dans la liste
     *
     * @param listener : listener a inserer
     * @see FireListener
     */
    //@Override
    public void addListener(FireListener listener) {
        this.listeners.add(FireListener.class, listener);
    }

    /**
     * Supprime un listener de type FireListener (radiations) dans la liste
     *
     * @param listener : listener a supprimer
     * @see FireListener
     */
    //@Override
    public void removeListener(FireListener listener) {
        this.listeners.remove(FireListener.class, listener);
    }


    /*==========================================================================
          GETTERS & SETTERS
    ========================================================================== */

    /**
     * Retourne une liste des listener de type incendie
     *
     * @return tableau de listeners de type FireListener
     */
    public FireListener[] getFireListeners() {
        return this.listeners.getListeners(FireListener.class);
    }
}