package fr.decoopmc.adapters;
import fr.decoopmc.interfaces.RadiationListener;

/**
 * Adapter de l'interface RadiationListener.
 * <p>
 * Implemente RadiationListener et definit avec un corps vide ses methodes
 * Un ecouteur n'aura besoin que de redefinir uniqument la methode qu'il utilise, et non toutes
 * </p>
 *
 * @author decoopmc
 * @version 1.0
 * @see RadiationListener
 */
public abstract class RadiationAdapter implements RadiationListener {
  public void criticalLevelChanged(int oldLevel, int newLevel)   {}
  public void criticalLevelRaised(int oldLevel, int newLevel)    {}
  public void criticalLevelDecreased(int oldLevel, int newLevel) {}
}
