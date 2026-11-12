import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpringEffect implements SeasonEffect {
    private final Random random;
    private final SimulationPanel simulationPanel;

    public SpringEffect(Random random, SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
        this.random = random;
    }

    public void applyEffect(List<Cell> cells) {
        List<Cell> newCells = new ArrayList<>();

        cells.stream()
            // Only neutral cells can reproduce. 10% chance for this cell to reproduce.
            .filter(cell -> cell.getState().equals(NeutralState.INSTANCE) && random.nextDouble() < 0.10)
            .forEach(cell -> {
                double newX = cell.getX() + (random.nextDouble() * 10 - 5);
                double newY = cell.getY() + (random.nextDouble() * 10 - 5);

                newCells.add(
                    new Cell(
                        newX,
                        newY,
                        cells.size() + newCells.size(),
                        NeutralState.INSTANCE
                    )
                );

                simulationPanel.incrementDuplicateCellCount();
            }
        );

        // Add all new cells after the loop has finished.
        cells.addAll(newCells);
    }
}
