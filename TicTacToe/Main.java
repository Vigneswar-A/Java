import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.event.*;
import java.util.*;

class Board implements ActionListener {
    JFrame root;
    JButton board[] = new JButton[9];
    JPanel boardPanel;
    JButton resetButton;
    JButton modeButton;
    AI AI = new AI("O");
    final DimensionUIResource screen = new DimensionUIResource(800, 800);
    final ColorUIResource gray = new ColorUIResource(132, 139, 145);
    final ColorUIResource black = new ColorUIResource(50, 50, 50);
    final ColorUIResource red = new ColorUIResource(225, 0, 0);
    final ColorUIResource green = new ColorUIResource(0, 255, 0);
    final ColorUIResource yellow = new ColorUIResource(255, 145, 0);
    boolean AIPlay = true;
    final int buttonSize = 200;
    boolean gameAlive = true;
    int moves = 0;
    String player = "X";
    HashMap<JButton, Integer> ButtonMap = new HashMap<JButton, Integer>();

    private void switchPlayer() {
        if (player.equals("X")) {
            player = "O";
            if (AIPlay && !AI.isX) {
                AI.getBoard(board);
                int move = AI.getBestMove();
                board[move].doClick();
            }
        } else {
            player = "X";
            if (AIPlay && AI.isX) {
                AI.getBoard(board);
                int move = AI.getBestMove();
                board[move].doClick();
            }
        }
    }

    Board() {
        root = new JFrame();
        root.setSize(screen);
        root.setLayout(null);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setTitle("Tic Tac Toe");
        root.setResizable(false);

        boardPanel = new JPanel();
        boardPanel.setBounds(50, 0, 600, 600);
        boardPanel.setBackground(gray);
        boardPanel.setLayout(null);
        boardPanel.setVisible(false);
        root.add(boardPanel);

        for (int i = 0; i < 9; i++) {
            board[i] = new JButton();
            board[i].setBounds(buttonSize * (i % 3), buttonSize * (int) (i / 3), buttonSize, buttonSize);
            board[i].setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.BOLD, 128));
            board[i].setForeground(black);
            board[i].addActionListener(this);
            boardPanel.add(board[i]);
            ButtonMap.put(board[i], i);
        }

        resetButton = new JButton("Reset");
        resetButton.setBounds(400, 650, 200, 50);
        resetButton.setBackground(ColorUIResource.RED);
        resetButton.addActionListener(this);

        modeButton = new JButton("Mode: AI ( X )");
        modeButton.setBounds(100, 650, 200, 50);
        modeButton.setBackground(ColorUIResource.GREEN);
        modeButton.addActionListener(this);

        root.add(resetButton);
        root.add(modeButton);
        boardPanel.setVisible(true);
        root.setVisible(true);
    }

    private boolean equal(int idx1, int idx2, int idx3) {
        String s1 = board[idx1].getText(), s2 = board[idx2].getText(), s3 = board[idx3].getText();
        if (s1.equals("") || s2.equals("") || s3.equals(""))
            return false;
        return (s1.equals(s2) && s2.equals(s3));
    }

    private void winner(int idx1, int idx2, int idx3) {
        board[idx1].setForeground(green);
        board[idx2].setForeground(green);
        board[idx3].setForeground(green);
        gameAlive = false;
    }

    private void draw() {
        for (int i = 0; i < 9; i++) {
            board[i].setForeground(yellow);
        }
        gameAlive = false;
    }

    private void reset() {
        for (int i = 0; i < 9; i++) {
            board[i].setText("");
            board[i].setForeground(black);
        }
        gameAlive = true;
        if (!AIPlay || !AI.isX)
            player = "X";
        else {
            if (AIPlay)
                switchPlayer();
            player = "O";
        }
        moves = 0;
    }

    private void changeMode() {
        if (AIPlay) {
            if (AI.isX) {
                AIPlay = false;
                reset();
                modeButton.setText("Mode: 2 Players");
            } else {
                AI.isX = true;
                reset();
                switchPlayer();
                modeButton.setText("Mode: AI ( O )");
            }

        } else {
            AIPlay = true;
            AI.isX = false;
            reset();
            modeButton.setText("Mode: AI ( X )");
        }

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(resetButton)) {
            reset();
            return;
        }

        if (event.getSource().equals(modeButton)) {
            changeMode();
            return;
        }

        int idx = ButtonMap.get(event.getSource());

        if (!gameAlive || !board[idx].getText().equals(""))
            return;

        board[idx].setText(player);

        for (int i = 0; i < 3; i++) {
            if (equal(3 * i, 3 * i + 1, 3 * i + 2)) {
                winner(3 * i, 3 * i + 1, 3 * i + 2);
            } else if (equal(i, i + 3, i + 6)) {
                winner(i, i + 3, i + 6);
            }
        }
        if (equal(0, 4, 8)) {
            winner(0, 4, 8);
        }
        if (equal(2, 4, 6)) {
            winner(2, 4, 6);
        }

        if (++moves == 9)
            draw();
        switchPlayer();

    }
}

class Main {
    public static void main(String args[]) {
        new Board();
    }
}