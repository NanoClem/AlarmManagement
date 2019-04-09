

/**
 * Cette interface correspond à l'ecouteur des alarmes anti-incendies
 * Puisqu'elle constitue un ecouteur d'une anomalie, elle derive de la classe AnomalyListener
 *
 * @author decoopmc
 * @version 1.0
 * @see AnomalyListener
*/
public interface FireListener extends AnomalyListener {
  void criticalLevelChanged(int oldLevel, int newLevel);
  void criticalLevelRaised(int oldLevel, int newLevel);
  void criticalLevelDecreased(int oldLevel, int newLevel);
}
