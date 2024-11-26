import GUI.TicTacToyGUI;
import controller.GameController;
import game.AI.TicTacToyAI;
import game.AI.impl.TicTacToyAISimpleImpl;
import game.TicTacToyGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено!");

        TicTacToyGame game = new TicTacToyGame();
        TicTacToyAI toyAI = new TicTacToyAISimpleImpl(game);
        TicTacToyGUI gui = new TicTacToyGUI(null, toyAI);

        GameController controller = new GameController(game, gui, toyAI);
        gui.setController(controller);
            controller.initializeGame();
    }
}