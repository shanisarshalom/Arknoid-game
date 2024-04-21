//209583038 Shani Sar Shalom
import biuoop.GUI;
import java.util.List;
import java.util.ArrayList;

/**
 * class with a main method that starts a game.
 */
public class Ass6Game {
    /**
     * starts a game with required levels.
     * @param args - the order of levels.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        // init score to use it in all game consistency.
        Counter score = new Counter(0);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui),
                gui.getKeyboardSensor(), score);
        List<LevelInformation> levels = List.of(new DirectHit(),
                new WideEasy(), new Green3());
        // if no arguments, start game with 3 levels run one after the other.
        if (args.length == 0) {
            gameFlow.runLevels(levels);
        } else {
            List<LevelInformation> levelsOrder = new ArrayList<>();
            for (String arg : args) {
                // catch if got invalid number
                try {
                    int numLevel = Integer.parseInt(arg);
                    if (numLevel <= levels.size() && numLevel >= 1) {
                        levelsOrder.add(levels.get(numLevel - 1));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            gameFlow.runLevels(levelsOrder);
        }
        gui.close();
    }
}


