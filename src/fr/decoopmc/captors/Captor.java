package fr.decoopmc.captors;

import javax.swing.event.EventListenerList;


/**
 * <b>CLASSE MERE DE TOUS LES DIFFERENTS TYPES DE CAPTEURS</b>
 * <p>
 * Constitue un objet source qui genere des evenements de type AnomalyEvent
 * </p>
 * @author decoopmc
 * @version 1.0
 */
 abstract class Captor {

   /**
    * Identifiant du capteur.
    * Unique pour chaque capteur.
    */
   protected static int id = 0;

   /*
    * Lieu ou se trouve le capteur.
    */
   protected String location;

  /**
   * Liste des ecouteurs.
   * Un seul objet conteneur pour tous les types d'ecouteur
   */
   protected final EventListenerList listeners = new EventListenerList();
   
   
  /**
   * <b>CONSTRUCTEUR DE LA CLASSE Captor</b>
   * <p>Un capteur est identifie automatiquement et prends au minimum une localisation</p>
   * @param _location : localisation geographique du capteur
   */
   Captor(String _location) 
   {
     id++;
     this.location = _location;
   }

   /**
     * Retourne la localisation du capteur
     */
    public String getLocation() {
      return this.location;
  }
 }
