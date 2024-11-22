package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.Arrays;

public class TicTacToyGame extends JFrame {
    public static final String EMPTY = " ";
    private Logger logger = LogManager.getLogger(TicTacToyGame.class);
    private JButton[][] board;
    public static String currentPlayer;

    public TicTacToyGame() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        currentPlayer = "X";
        initEmptyBoard();
        logger.debug("Класс {} создан", TicTacToyGame.class);
    }

    private void initEmptyBoard() {
        logger.debug("Инициализация игровой доски 3X3");
        board = new JButton[3][3];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], new JButton(EMPTY));
        }
    }

    public boolean makeMove(int row, int col) {
        logger.debug("Игрок сделал ход {}, {}", row, col);
        if (ifEmptyCells(row, col)) {
            board[row][col].setText(currentPlayer);
            logger.debug("Ячейка свободна");
            return true;
        }
        logger.warn("Данная ячейка занята");
        return false;
    }


    private boolean ifEmptyCells(int row, int col) {
        logger.debug("Проверка ячейки {}, {}", row, col);
        return board[row][col].getText().equals(EMPTY);
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



//    public void printBoard() {
//        logger.debug("Вывести текущую доску на экран");
//        for (char[] chars : board) {
//            System.out.println("--- ".repeat(board.length));
//            for (char aChar : chars) {
//                System.out.print(aChar + " | ");
//            }
//            System.out.println();
//        }
//        System.out.println("--- ".repeat(board.length));
//    }

    private boolean horizontalCheck() {
        logger.debug("Проверка совпадений по горизонтали");
        for (JButton[] chars : board) {
            JButton firstElement = chars[0];
            for (int col = 0; col < chars.length; col++) {
                if (firstElement.getText().equals(EMPTY) || firstElement != chars[col]) {
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
            JButton firstElement = board[0][col];
            for (int row = 0; row < board.length; row++) {
                if (firstElement.getText().equals(EMPTY) || firstElement != board[row][col]) {
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
        if (board[0][0].getText().equals(board[1][1].getText())
            && board[1][1].getText().equals(board[2][2].getText())
            && !(board[0][0].getText().equals(EMPTY))) {
            logger.debug("Есть три совпадения по главной диагонали");
            return true;
        }
        if (board[0][2].getText().equals(board[1][1].getText())
            && board[1][1].getText().equals(board[2][0])
            && !(board[2][0].getText().equals(EMPTY))) {
            logger.debug("Есть три совпадения по побочной диагонали");
            return true;
        }
        logger.debug("Нет совпадений по диагонали");
        return false;
    }

    public boolean areAllCellsFilled() {
        logger.debug("Проверка: все ли ячейки заполнены");
        for (JButton[] chars : board) {
            for (JButton aChar : chars) {
                if (aChar.getText().equals(EMPTY)) {
                    logger.debug("Есть пусты ячейки");
                    return false;
                }
            }
        }
        logger.debug("Пустых ячеек нет");
        return true;
    }

    public JButton[][] getBoard() {
        return this.board;
    }
}
