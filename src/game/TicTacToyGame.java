package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TicTacToyGame {
    public static final String EMPTY = "";
    public static String currentPlayer;
    private Logger logger = LogManager.getLogger(TicTacToyGame.class);
    private String[][] board;
    private int[][] winnerCells;

    public TicTacToyGame() {
        winnerCells = new int[3][2];
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


    public boolean ifEmptyCells(int row, int col) {
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
        for (int row = 0; row < board.length; row++) {
            String firstElement = board[row][0];
            if (firstElement.equals(EMPTY)) {
                continue; // Пропустить пустую строку
            }

            boolean isWinningRow = true;
            for (int col = 1; col < board[row].length; col++) {
                if (!firstElement.equals(board[row][col])) {
                    isWinningRow = false;
                    break;
                }
            }

            if (isWinningRow) {
                logger.debug("Есть три совпадения по горизонтали");
                setWinnerCells(row, 0, row, 1, row, 2); // Установка координат выигрышных ячеек
                return true;
            }
        }
        logger.debug("Нет совпадений по горизонтали");
        return false;
    }

    private boolean verticalCheck() {
        logger.debug("Проверка совпадений по вертикали");
        for (int col = 0; col < board[0].length; col++) {
            String firstElement = board[0][col];
            if (firstElement.equals(EMPTY)) {
                continue; // Пропустить пустой столбец
            }

            boolean isWinningColumn = true;
            for (int row = 1; row < board.length; row++) {
                if (!firstElement.equals(board[row][col])) {
                    isWinningColumn = false;
                    break;
                }
            }

            if (isWinningColumn) {
                logger.debug("Есть три совпадения по вертикали");
                setWinnerCells(0, col, 1, col, 2, col); // Установка координат выигрышных ячеек
                return true;
            }
        }
        logger.debug("Нет совпадений по вертикали");
        return false;
    }

    private boolean diagonalCheck() {
        logger.debug("Проверка совпадений по диагонали");

        // Проверка главной диагонали
        if (!board[0][0].equals(EMPTY) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            setWinnerCells(0, 0, 1, 1, 2, 2);
            logger.debug("Есть три совпадения по главной диагонали");
            return true;
        }

        // Проверка побочной диагонали
        if (!board[0][2].equals(EMPTY) && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            setWinnerCells(0, 2, 1, 1, 2, 0);
            logger.debug("Есть три совпадения по побочной диагонали");
            return true;
        }

        return false;
    }

    private void setWinnerCells(int row1, int col1, int row2, int col2, int row3, int col3) {
        winnerCells[0][0] = row1;
        winnerCells[0][1] = col1;
        winnerCells[1][0] = row2;
        winnerCells[1][1] = col2;
        winnerCells[2][0] = row3;
        winnerCells[2][1] = col3;
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

    public int[][] getWinnerCells() {
        return winnerCells;
    }

    public void restart() {
        currentPlayer = "X";
        initEmptyBoard();
    }
}
