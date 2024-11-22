package GUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class HelloWorldSwing {

    private static Logger logger = LogManager.getLogger(HelloWorldSwing.class);

    private static void crateAnShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Крестики нолики");


        JPanel panel = new JPanel();
        //GroupLayout layout = new GroupLayout(panel);
        //layout.setAutoCreateGaps(true);
        //panel.setLayout(layout);

        ButtonGroup group = new ButtonGroup();

        JLabel label = new JLabel("За кого будешь играть?");

        panel.add(label);

        JRadioButton radioButtonX = new JRadioButton("Крестик");
        radioButtonX.setSelected(true);
        radioButtonX.setActionCommand("Hello");
        JRadioButton radioButtonO = new JRadioButton("Нолик");
        radioButtonO.setActionCommand("World");


        JButton button = new JButton("Ок");

        button.addActionListener(lisner -> {
            frame.setSize(300, 300);
            panel.removeAll();
            GridLayout layout = new GridLayout(3, 3);
            panel.setLayout(layout);
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    panel.add(row + " " + col, new Button());
                }
            }
            panel.revalidate();
            panel.repaint();
        });


        radioButtonX.addActionListener(e -> logger.debug("Нажал на X"));
        radioButtonO.addActionListener(e -> logger.debug("Нажал на O"));
        group.add(radioButtonX);
        group.add(radioButtonO);


        panel.add(radioButtonX);
        panel.add(radioButtonO);
        panel.add(button);


        frame.add(panel);


        frame.setSize(150, 150);
        frame.setLocationRelativeTo(null);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        logger.debug("Старт программы");
        SwingUtilities.invokeLater(HelloWorldSwing::crateAnShowGUI);
        logger.debug("Выход из программы");
    }
}
