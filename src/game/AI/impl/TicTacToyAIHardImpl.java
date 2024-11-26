package game.AI.impl;

import java.util.Random;

public class TicTacToyAIHardImpl {

    private Random random;

    public TicTacToyAIHardImpl() {
        random = new Random();
    }
//
//    @Override
//    public int[] getNextMove(char[][] board) {
//        int bestScore = Integer.MIN_VALUE;
//        int[] bestMove = new int[2];
//
//        // Проходим по всем клеткам на доске
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                // Проверяем, пустая ли клетка
//                if (board[row][col] == TicTacToyGame.EMPTY) {
//                    // Ставим свой символ
//                    board[row][col] = TicTacToyGame.currentPlayer; // Предположим, что COMPUTER - это символ ИИ
//
//                    // Оцениваем ход
//                    int score = minimax(board, 0, false);
//
//                    // Возвращаем клетку в исходное состояние
//                    board[row][col] = TicTacToyGame.EMPTY;
//
//                    // Если текущий ход лучше, сохраняем его
//                    if (score > bestScore) {
//                        bestScore = score;
//                        bestMove[0] = row;
//                        bestMove[1] = col;
//                    }
//                }
//            }
//        }
//
//        return bestMove;
//    }
//
//    private int minimax(char[][] board, int depth, boolean isMaximizing) {
//        // Проверяем, есть ли победитель
//        int score = evaluate(board);
//        if (score != 0) {
//            return score; // Возвращаем оценку, если игра закончена
//        }
//
//        // Проверяем, есть ли ничья
//        if (isBoardFull(board)) {
//            return 0; // Ничья
//        }
//
//        if (isMaximizing) {
//            int bestScore = Integer.MIN_VALUE;
//            for (int row = 0; row < 3; row++) {
//                for (int col = 0; col < 3; col++) {
//                    if (board[row][col] == TicTacToyGame.EMPTY) {
//                        board[row][col] = TicTacToyGame.currentPlayer;
//                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false));
//                        board[row][col] = TicTacToyGame.EMPTY;
//                    }
//                }
//            }
//            return bestScore;
//        } else {
//            int bestScore = Integer.MAX_VALUE;
//            for (int row = 0; row < 3; row++) {
//                for (int col = 0; col < 3; col++) {
//                    if (board[row][col] == TicTacToyGame.EMPTY) {
//                        board[row][col] = TicTacToyGame.currentPlayer; // Предположим, что PLAYER - это символ игрока
//                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true));
//                        board[row][col] = TicTacToyGame.EMPTY;
//                    }
//                }
//            }
//            return bestScore;
//        }
//    }
//
//    private int evaluate(char[][] board) {
//        // Проверка всех возможных выигрышных комбинаций
//        // Возвращаем 1, если COMPUTER выигрывает
//        // Возвращаем -1, если PLAYER выигрывает
//        // Возвращаем 0 для ничьей
//        // (Эту часть нужно реализовать)
//        // Пример:
//        // if (winCondition) return 1; // для COMPUTER
//        // if (winCondition) return -1; // для PLAYER
//        return 0; // Если нет победителя
//    }
//
//    private boolean isBoardFull(char[][] board) {
//        for (char[] row : board) {
//            for (char cell : row) {
//                if (cell == TicTacToyGame.EMPTY) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
