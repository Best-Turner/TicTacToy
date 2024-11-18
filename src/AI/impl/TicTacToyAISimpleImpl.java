package AI.impl;

import AI.TicTacToyAI;
import game.TicTacToyGame;

import java.util.Random;

public class TicTacToyAISimpleImpl implements TicTacToyAI {

    private Random random;

    public TicTacToyAISimpleImpl() {
        random = new Random();
    }

    @Override
    public int[] getNextMove(char[][] board) {
        boolean isFirstMove = true;
        int col, row;
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar != TicTacToyGame.EMPTY) {
                    isFirstMove = false;
                    break;
                }
            }
            if (!isFirstMove) {
                break;
            }
        }
        if (isFirstMove) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            return new int[]{row, col};
        }

        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (board[row][col] == TicTacToyGame.EMPTY) {
                return new int[]{row, col};
            }
        }
    }
}
