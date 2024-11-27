package GUI;

import controller.GameController;
import game.AI.TicTacToyAI;
import game.TicTacToyGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToyGUI {
    private Logger logger = LogManager.getLogger(TicTacToyGUI.class);
    private JFrame frame;
    private JButton[][] buttons;
    private JPanel panel;
    private GameController controller;
    private TicTacToyAI toyAI;


    public TicTacToyGUI(GameController controller, TicTacToyAI toyAI) {
        this.controller = controller;
        this.toyAI = toyAI;

        frame = new JFrame("Крестики нолики");
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        panel = new JPanel(new BorderLayout());
        createButtons();
    }

    public void initStartPage() {
        logger.info("Стартовая страница GUI, с выбором за кого играть");
        final int[] player = {1};
        JLabel label = new JLabel();
        label.setText("За кого будешь играть?");
        panel.add(label, BorderLayout.BEFORE_FIRST_LINE);
        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButtonX = new JRadioButton("Крестик");
        JRadioButton radioButtonO = new JRadioButton("Нолик");
        radioButtonX.setSelected(true);
        radioButtonX.addActionListener(e -> player[0] = 1);
        radioButtonO.addActionListener(e -> player[0] = 0);

        JButton button = new JButton("ОК");
        button.addActionListener(listener -> {
            logger.info("Игрок выбрал играть за {}", (player[0] == 1) ? "крестик" : "нолик");
            addButtonsToPanel();
            if (player[0] == 0) {
                logger.debug("Выполняем логику работы AI");
                int[] nextMove = toyAI.getNextMove();
                controller.handlePlayerMove(nextMove[0], nextMove[1]);
            }
        });

        group.add(radioButtonX);
        group.add(radioButtonO);
        group.add(button);

        panel.add(radioButtonX, BorderLayout.WEST);
        panel.add(radioButtonO, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.debug("Окошко отрисованно");
    }

    public void restart() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.repaint();
        createButtons();
        initStartPage();
    }

    private void addButtonsToPanel() {
        panel.removeAll();
        frame.setSize(300, 300);
        panel.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                panel.add(buttons[row][col]);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void createButtons() {
        logger.debug("Создать массив из кнопок");
        buttons = new JButton[3][3];
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].addActionListener(new ButtonListener(row, col));
            }
        }
    }

    public void paintWinningCells(int[][] array) {
        int temp1, temp2;
        Color color = getColor();
        for (int row = 0; row < array.length; row++) {
            temp1 = array[row][0];
            temp2 = array[row][1];
            buttons[temp1][temp2].setBackground(color);
        }
        panel.repaint();
    }

    public void paintAllCellsRandomColor() {
        for (JButton[] row : buttons) {
            for (JButton col : row) {
                col.setBackground(getColor());
            }
        }
        panel.repaint();
    }


    public void updateBoard(String[][] board) {
        logger.debug("Сверить отображаемые кнопки с массивом игры");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (!(board[row][col].equals(TicTacToyGame.EMPTY)))
                    buttons[row][col].setFont(new Font("Area", Font.PLAIN, 75));
                buttons[row][col].setText(board[row][col]);
            }
        }
        panel.revalidate();
        panel.repaint();
        logger.debug("Обновить панель");
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    private Color getColor() {
        Random random = new Random();
        int c1, c2, c3;
        c1 = random.nextInt(255);
        c2 = random.nextInt(255);
        c3 = random.nextInt(255);
        return new Color(c1, c2, c3);
    }

    public JFrame getFrame() {
        return frame;
    }

    private class ButtonListener implements ActionListener {
        private int row, col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.debug("Нажатие кнопки");
            if (controller.handlePlayerMove(row, col)) {
                int[] nextMove = toyAI.getNextMove();
                controller.handlePlayerMove(nextMove[0], nextMove[1]);
            }
        }
    }
}
