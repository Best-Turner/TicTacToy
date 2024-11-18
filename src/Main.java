import AI.TicTacToyAI;
import AI.impl.TicTacToyAISimpleImpl;
import game.TicTacToyGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    private static Scanner scanner;

    public static void main(String[] args) {
        logger.info("Приложение запущено!");
        scanner = new Scanner(System.in);

        TicTacToyGame game = new TicTacToyGame();
        TicTacToyAI toyAI = new TicTacToyAISimpleImpl();
        int countSteps = 0;
        boolean winner = false;
        while (true) {

            game.printBoard();
            int[] inputPlayer = getInputPlayer();
            if (inputPlayer[0] == -1) {
                break;
            }
            game.makeMove(inputPlayer[0] - 1, inputPlayer[1] - 1);
            countSteps++;
            game.printBoard();
            game.switchPlayer();
            int[] nextMove = toyAI.getNextMove(game.getBoard());
            game.makeMove(nextMove[0], nextMove[1]);
            countSteps++;
            game.switchPlayer();
            if (countSteps >= 5) {
                winner = game.checkWinner();
            }
            if (winner) {
                System.out.println("Победил игрок - " + game.getCurrentPlayer());
                game = new TicTacToyGame();
                countSteps = 0;
                winner = false;
                continue;
            }
            if (game.areAllCellsFilled()) {
                System.out.println("У нас братская ничья");
                game = new TicTacToyGame();
                countSteps = 0;
            }
        }
    }


    public static int[] getInputPlayer() {
        System.out.println("Введите первое значение");
        int row = scanner.nextInt();
        System.out.println("Введите второе значение");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
}