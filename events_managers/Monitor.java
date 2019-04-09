import javax.swing.event.EventListenerlist;

/**
 * <b>Monitor est une classe permettant de gerer les differents types d'alarmes</b>
 * <p>
 * Il existe deux types de moniteurs :
 * <ul>
 * <li>type A : gere les alarmes anti-gaz toxiques et anti-incendies</li>
 * <li>type B : gere les alarmes anti-gaz-toxiques et anti-radiations</li>
 * </ul>
 * </p>
 *
 * @author decoopmc
 * @version 1.0
 */
public class Monitor {

  /**
   * Le type du moniteur. Permet de savoir quelles type d'alarmes il peut gerer.
   *
   * @see Monitor#Monitor(String)
   * @see Monitor#getType()
   */
  private String type;

  /**
   * Liste des ecouteur enregistres.
   * @see Monitor#getFireListeners()
   * @see Monitor#getGazListeners()
   * @see Monitor#getRadiationisteners()
   * @see Monitor#addFireListener(FireListener)
   * @see Monitor#addGazListener(GazListener)
   * @see Monitor#addRadiationListener(RadiationListener)
   * @see Monitor#removeFireListener(FireListener)
   * @see Monitor#removeGazListener(GazListener)
   * @see Monitor#removeRadiationListener(RadiationListener)
   */
  private final EventListenerlist listeners = new EventListenerList();    // un seul objet conteneur pour tous les types d'ecouteur

  /**
   * <b>CONSTRUCTEUR DE CLASSE Monitor</b>
   * <p>
   * A la construction d'un objet Monitor, son type est de A ou B
   * </p>
   *
   * @param _type : type du moniteur (A ou B)
   * @see Monitor#type
   */
   public Monitor(String _type) {
     this.type = _type;
   }


   /* ==========================================================================
          GETTERS & SETTERS
   ========================================================================== */

   /**
    * Retourne le type du moniteur
    *
    * @return type du moniteur (A ou B)
    */
   public String getType() {
     return this.type;
   }

   /**
    * Retourne une liste des ecouteurs de type incendie
    *
    * @return listeners de type FireListener
    */
   public FireListener[] getFireListeners() {
     return this.listeners.getListeners(FireListener.class);
   }

   /**
    * Retourne une liste des ecouteurs de type gaz-toxiques
    *
    * @return listeners de type GazListener
    */
   public GazListener[] getGazListeners() {
     return this.listeners.getListeners(GazListener.class);
   }

   /**
    * Retourne une liste des ecouteurs de type radiation
    *
    * @return listeners de type RadiationListener
    */
   public RadiationListener[] getRadiationisteners() {
     return this.listeners.getListeners(RadiationListener.class);
   }


   /* ==========================================================================
          AJOUT ET SUPRESSION DANS LA LISTE DES ECOUTEURS
   ========================================================================== */

   /**
    * Ajoute un ecouteur de type FireListener (incendie) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a inserer
    * @see FireListener
    */
   public void addFireListener(FireListener listener) {
     this.listeners.add(FireListener.class, listener);
   }

   /**
    * Ajoute un ecouteur de type GazListener (gaz toxiques) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a inserer
    * @see GazListener
    */
   public void addGazListener(GazListener listener) {
     this.listeners.add(GazListener.class, listener);
   }


   /**
    * Ajoute un ecouteur de type RadiationListener (radiations) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a inserer
    * @see RadiationListener
    */
   public void addRadiationListener(RadiationListener listener) {
     this.listeners.add(RadiationListener.class, listener);
   }

   /**
    * Supprime un ecouteur de type FireListener (radiations) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a supprimer
    * @see FireListener
    */
   public void removeFireListener(FireListener listener) {
     this.listeners.remove(FireListener.class, listener);
   }

   /**
    * Supprime un ecouteur de type GazListener (radiations) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a supprimer
    * @see GazListener
    */
   public void removeGazListener(GazListener listener) {
     this.listeners.remove(GazListener.class, listener);
   }

   /**
    * Supprime un ecouteur de type RadiationListener (radiations) dans la liste des ecouteurs
    *
    * @param listener : ecouteur a supprimer
    * @see RadiationListener
    */
   public void removeRadiationListener(RadiationListener listener) {
     this.listeners.remove(RadiationListener.class, listener);
   }
}
