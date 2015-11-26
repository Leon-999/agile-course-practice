package ru.unn.agile.LongArithmetic.view;

import javax.swing.*;

public class MatrixMultiply {
    private JPanel mainPanel;
    private JScrollPane panel2;
    private JPanel panel1;
    private JButton testButton;

    private MatrixMultiply() {}

    private static void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");

        MatrixMultiply form = new MatrixMultiply();
        frame.setContentPane(form.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //createUIComponents();
        form.testButton = new JButton("test");
        form.testButton.setVisible(true);
        form.testButton.setSize(100,100);
        form.panel1.add(form.testButton);
    }
}
