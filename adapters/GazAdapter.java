package adapters;
import interfaces.GazListener;

/**
 * Adapter de l'interface GazListener.
 * <p>
 * Implemente GazListener et definit avec un corps vide ses methodes
 * Un ecouteur n'aura besoin que de redefinir uniqument la methode qu'il utilise, et non toutes
 * </p>
 *
 * @author decoopmc
 * @version 1.0
 * @see GazListener
 */
public abstract class GazAdapter implements GazListener {
  public void criticalLevelChanged(int oldLevel, int newLevel)   {}
  public void criticalLevelRaised(int oldLevel, int newLevel)    {}
  public void criticalLevelDecreased(int oldLevel, int newLevel) {}
}
