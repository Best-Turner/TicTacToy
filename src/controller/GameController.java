package controller;

import GUI.TicTacToyGUI;
import game.AI.TicTacToyAI;
import game.TicTacToyGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class GameController {
    private final TicTacToyGame game;
    private final TicTacToyGUI gui;
    private final TicTacToyAI toyAI;
    private Logger logger = LogManager.getLogger(GameController.class);

    public GameController(TicTacToyGame game, TicTacToyGUI gui, TicTacToyAI toyAI) {
        this.game = game;
        this.gui = gui;
        this.toyAI = toyAI;
    }

    public void initializeGame() {
        game.initEmptyBoard();
        gui.initStartPage();
    }


    public boolean handlePlayerMove(int row, int col) {
        logger.debug("Игрок {} делает ход", game.getCurrentPlayer());
        game.makeMove(row, col);
        gui.updateBoard(game.getBoard());
        if (game.checkWinner()) {
            JOptionPane.showMessageDialog(gui.getFrame(), "Игрок " + game.getCurrentPlayer() + " выйграл");
            game.restart();
            gui.restart();
            return false;
        } else {
            logger.debug("Смена игрока {}", game.getCurrentPlayer());
            game.switchPlayer();
            logger.debug("Текущий игрок {}", game.getCurrentPlayer());
            return true;
        }
    }
}
