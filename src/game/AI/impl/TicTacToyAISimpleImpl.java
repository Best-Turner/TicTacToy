package game.AI.impl;

import game.AI.TicTacToyAI;
import game.TicTacToyGame;

import java.util.Objects;
import java.util.Random;

public class TicTacToyAISimpleImpl implements TicTacToyAI {

    private final TicTacToyGame game;
    private Random random;

    public TicTacToyAISimpleImpl(TicTacToyGame game) {
        this.game = game;
        random = new Random();
    }

    @Override
    public int[] getNextMove() {
        boolean isFirstMove = true;
        int col, row;
        for (String[] chars : game.getBoard()) {
            for (String aChar : chars) {
                if (aChar.equals(TicTacToyGame.EMPTY)) {
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

            if (Objects.equals(game.getBoard()[row][col], TicTacToyGame.EMPTY)) {
                return new int[]{row, col};
            }
        }
    }
}
