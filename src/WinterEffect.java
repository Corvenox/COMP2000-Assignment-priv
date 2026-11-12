import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WinterEffect implements SeasonEffect {
    private final Random random;
    private final SimulationPanel simulationPanel;

    public WinterEffect(Random random, SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
        this.random = random;
    }

    public void applyEffect(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();

        while (iterator.hasNext()) {

            iterator.next();

            // 2% chance for this cell to die.
            if (random.nextDouble() < 0.03) {

                iterator.remove();

                simulationPanel.incrementDeadCellCount();
            }
        }
    }
}
