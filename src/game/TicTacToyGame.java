package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TicTacToyGame {
    public static final char EMPTY = ' ';
    private Logger logger = LogManager.getLogger(TicTacToyGame.class);
    private char[][] board;
    public static char currentPlayer;

    public TicTacToyGame() {
        currentPlayer = 'X';
        initEmptyBoard();
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
        return board[row][col] == EMPTY;
    }

    public boolean checkWinner() {
        logger.debug("Проверка: есть ли три совпадения");
        return (horizontalCheck() || verticalCheck() || diagonalCheck());
    }


    public void switchPlayer() {
        logger.debug("Смена текущего игрока - {}", currentPlayer);
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        logger.debug("Текущего игрок - {}", currentPlayer);
    }

    public char getCurrentPlayer() {
        logger.debug("Получить текущего игрока {}", currentPlayer);
        return currentPlayer;
    }

    private void initEmptyBoard() {
        logger.debug("Инициализация игровой доски 3X3");
        board = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], EMPTY);
        }
    }

    public void printBoard() {
        logger.debug("Вывести текущую доску на экран");
        for (char[] chars : board) {
            System.out.println("--- ".repeat(board.length));
            for (char aChar : chars) {
                System.out.print(aChar + " | ");
            }
            System.out.println();
        }
        System.out.println("--- ".repeat(board.length));
    }

    private boolean horizontalCheck() {
        logger.debug("Проверка совпадений по горизонтали");
        for (char[] chars : board) {
            char firstElement = chars[0];
            for (int col = 0; col < chars.length; col++) {
                if (firstElement == EMPTY || firstElement != chars[col]) {
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
            char firstElement = board[0][col];
            for (int row = 0; row < board.length; row++) {
                if (firstElement == EMPTY || firstElement != board[row][col]) {
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
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
            logger.debug("Есть три совпадения по главной диагонали");
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] != EMPTY) {
            logger.debug("Есть три совпадения по побочной диагонали");
            return true;
        }
        logger.debug("Нет совпадений по диагонали");
        return false;
    }

    public boolean areAllCellsFilled() {
        logger.debug("Проверка: все ли ячейки заполнены");
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == EMPTY) {
                    logger.debug("Есть пусты ячейки");
                    return false;
                }
            }
        }
        logger.debug("Пустых ячеек нет");
        return true;
    }

    public char[][] getBoard() {
        return this.board;
    }
}
