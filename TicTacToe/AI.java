import javax.swing.*;
import java.util.*;

public class AI {
    String board[] = new String[9];
    boolean isX;
    Random Choice = new Random();

    AI(String player) {
        if (player.equals("X"))
            isX = true;
        else
            isX = false;
    }

    public void getBoard(JButton board[]) {
        for (int i = 0; i < 9; i++) {
            this.board[i] = board[i].getText();
        }
    }

    private boolean equal(int idx1, int idx2, int idx3) {
        String s1 = board[idx1], s2 = board[idx2], s3 = board[idx3];
        if (s1.equals("") || s2.equals("") || s3.equals(""))
            return false;
        return (s1.equals(s2) && s2.equals(s3));
    }

    public boolean isOver() {
        for (int i = 0; i < 3; i++) {
            if (equal(3 * i, 3 * i + 1, 3 * i + 2)) {
                return true;
            } else if (equal(i, i + 3, i + 6)) {
                return true;
            }
        }
        if (equal(0, 4, 8) || equal(2, 4, 6))
            return true;

        return false;
    }

    public int getBestMove() {
        return minimax(isX, 0)[2];
    }

    public int[] minimax(boolean ismax, int best) {
        int depth = 0;
        for (int i = 0; i < 9; i++) {
            if (!board[i].equals(""))
                depth++;
        }
        if (isOver()) {
            if (ismax)
                return new int[] { -1, depth, best };
            else
                return new int[] { 1, depth, best };
        }

        if (depth == 9) {
            return new int[] { 0, depth, best };
        }

        if (ismax) {
            int bestScore = -2;
            int temp[] = new int[2];
            int score;
            int minDepth = 10;
            int bestMove = 0;

            for (int i = 0; i < 9; i++) {
                if (!board[i].equals(""))
                    continue;

                board[i] = "X";

                temp = minimax(false, i);
                score = temp[0];

                if (score > bestScore || (score == bestScore && temp[1] < minDepth) || (score == bestScore
                        && temp[1] == minDepth && Choice.nextBoolean())) {
                    bestScore = score;
                    minDepth = temp[1];
                    bestMove = i;
                }

                board[i] = "";
            }

            return new int[] { bestScore, minDepth, bestMove };
        }

        else {
            int bestScore = 2;
            int temp[] = new int[2];
            int score;
            int minDepth = depth;
            int bestMove = 0;

            for (int i = 0; i < 9; i++) {
                if (!board[i].equals(""))
                    continue;

                board[i] = "O";

                temp = minimax(true, best);
                score = temp[0];

                if (score < bestScore || (score == bestScore && temp[1] < minDepth) || (score == bestScore
                        && temp[1] == minDepth && Choice.nextBoolean())) {
                    bestScore = score;
                    minDepth = temp[1];
                    bestMove = i;
                }

                board[i] = "";
            }
            return new int[] { bestScore, minDepth, bestMove };
        }
    }
}
