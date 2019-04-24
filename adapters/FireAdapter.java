package adapters;
import interfaces.FireListener;

/**
 * Adapter de l'interface FireListener.
 * <p>
 * Implemente FireListener et definit avec un corps vide ses methodes
 * Un ecouteur n'aura besoin que de redefinir uniqument la methode qu'il utilise, et non toutes
 * </p>
 *
 * @author decoopmc
 * @version 1.0
 * @see FireListener
 */
public abstract class FireAdapter implements FireListener {
  public void criticalLevelChanged(int oldLevel, int newLevel)   {}
  public void criticalLevelRaised(int oldLevel, int newLevel)    {}
  public void criticalLevelDecreased(int oldLevel, int newLevel) {}
}
