package controller;

import GUI.TicTacToyGUI;
import game.TicTacToyGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class GameController {
    private final TicTacToyGame game;
    private final TicTacToyGUI gui;

    private Logger logger = LogManager.getLogger(GameController.class);

    public GameController(TicTacToyGame game, TicTacToyGUI gui) {
        this.game = game;
        this.gui = gui;
    }

    public void initializeGame() {
        game.initEmptyBoard();
        gui.initStartPage();
    }


    public boolean handlePlayerMove(int row, int col) {
        logger.debug("Игрок {} делает ход", game.getCurrentPlayer());
        if (!game.ifEmptyCells(row, col)) {
            return false;
        }
        game.makeMove(row, col);
        gui.updateBoard(game.getBoard());

        if (game.checkWinner()) {
            gui.paintWinningCells(game.getWinnerCells());
            JOptionPane.showMessageDialog(gui.getFrame(), "Игрок " + game.getCurrentPlayer() + " выйграл");
            restartGame();
            return false;
        } else if (game.areAllCellsFilled()) {
            gui.paintAllCellsRandomColor();
            JOptionPane.showMessageDialog(gui.getFrame(), "Ничья");
            restartGame();
            return false;
        } else {
            logger.debug("Смена игрока {}", game.getCurrentPlayer());
            game.switchPlayer();
            logger.debug("Текущий игрок {}", game.getCurrentPlayer());
            return true;
        }
    }

    private void restartGame() {
        game.restart();
        gui.restart();
    }
}
