package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TicTacToyGame {
    public static final String EMPTY = "";
    public static String currentPlayer;
    private Logger logger = LogManager.getLogger(TicTacToyGame.class);
    private String[][] board;

    public TicTacToyGame() {
        currentPlayer = "X";
        logger.debug("Класс {} создан", TicTacToyGame.class);
    }

    public boolean makeMove(int row, int col) {
        logger.debug("Игрок сделал ход {}, {}", row, col);
        if (ifEmptyCells(row, col)) {
            board[row][col] = currentPlayer;
            logger.debug("Ячейка свободна");
            return true;
        }
        logger.warn("Данная ячейка занята");
        return false;
    }


    private boolean ifEmptyCells(int row, int col) {
        logger.debug("Проверка ячейки {}, {}", row, col);
        return board[row][col].equals(EMPTY);
    }

    public boolean checkWinner() {
        logger.debug("Проверка: есть ли три совпадения");
        return (horizontalCheck() || verticalCheck() || diagonalCheck());
    }


    public void switchPlayer() {
        logger.debug("Смена текущего игрока - {}", currentPlayer);
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        logger.debug("Текущего игрок - {}", currentPlayer);
    }

    public String getCurrentPlayer() {
        logger.debug("Получить текущего игрока {}", currentPlayer);
        return currentPlayer;
    }

    public void initEmptyBoard() {
        logger.debug("Инициализация игровой доски 3X3");
        board = new String[3][3];
        for (String[] strings : board) {
            Arrays.fill(strings, EMPTY);
        }
    }

    private boolean horizontalCheck() {
        logger.debug("Проверка совпадений по горизонтали");
        for (String[] chars : board) {
            String firstElement = chars[0];
            for (int col = 0; col < chars.length; col++) {
                if (firstElement.equals(EMPTY) || !(firstElement.equals(chars[col]))) {
                    break;
                }
                if (col == chars.length - 1) {
                    logger.debug("Есть три совпадения по горизонтали");
                    return true;
                }
            }
        }
        logger.debug("Нет совпадений по горизонтали");
        return false;
    }

    private boolean verticalCheck() {
        logger.debug("Проверка совпадений по вертикали");
        for (int col = 0; col < board[0].length; col++) {
            String firstElement = board[0][col];
            for (int row = 0; row < board.length; row++) {
                if (firstElement.equals(EMPTY) || !(firstElement.equals(board[row][col]))) {
                    break;
                }
                if (row == board.length - 1) {
                    logger.debug("Есть три совпадения по вертикали");
                    return true;
                }
            }
        }
        logger.debug("Нет совпадений по вертикали");
        return false;
    }

    private boolean diagonalCheck() {
        logger.debug("Проверка совпадений по диагонали");
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !(board[0][0].equals(EMPTY))) {
            logger.debug("Есть три совпадения по главной диагонали");
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !(board[2][0].equals(EMPTY))) {
            logger.debug("Есть три совпадения по побочной диагонали");
            return true;
        }
        logger.debug("Нет совпадений по диагонали");
        return false;
    }

    public boolean areAllCellsFilled() {
        logger.debug("Проверка: все ли ячейки заполнены");
        for (String[] chars : board) {
            for (String aChar : chars) {
                if (aChar.equals(EMPTY)) {
                    logger.debug("Есть пусты ячейки");
                    return false;
                }
            }
        }
        logger.debug("Пустых ячеек нет");
        return true;
    }

    public String[][] getBoard() {
        return this.board;
    }

    public void restart() {
        currentPlayer = "X";
        initEmptyBoard();
    }
}
